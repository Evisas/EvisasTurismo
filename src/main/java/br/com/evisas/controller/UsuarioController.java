package br.com.evisas.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.evisas.dao.UsuarioDao;
import br.com.evisas.entity.Usuario;
import br.com.evisas.service.EmailService;
import br.com.evisas.util.Const;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	EmailService emailService;

	@Autowired
	private MessageSource messageSource;
    
	@RequestMapping("/")
	public String entrarNoSite() {
		return "redirect:login";
	}

	@GetMapping("/login")
	public String mostrarTelaLogin(Usuario usuario) {
		return "usuario/login";
	}

	@PostMapping("/login")
	public String fazerLogin(@Valid Usuario usuario, BindingResult result, Model model, HttpSession session) {
		if (result.hasFieldErrors("email") || result.hasFieldErrors("senha")) {
			return "usuario/login";
		}
		
		Usuario usuarioBuscado = usuarioDao.buscarPeloLogin(usuario);
		if (usuarioBuscado != null) {
			session.setAttribute(Const.USUARIO, usuarioBuscado);
			return "redirect:home";
		} else {
			model.addAttribute(Const.STR_COD_MSG_ERRO, "msg.erro.login.invalido");
			return "usuario/login";
		}
	}

	@RequestMapping("/home")
	public String mostrarTelaHome() {
		return "usuario/home";
	}

	@RequestMapping("/logout")
	public String fazerLogout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

	@GetMapping("/cadastro")
	public String mostrarTelaCadastro(Usuario usuario) {
		return "usuario/cadastro";
	}

	@PostMapping("/cadastro")
	public String fazerCadastro(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasErrors()) {
			return "usuario/cadastro";
		}
		try {
			long id = usuarioDao.criar(usuario);
			usuario.setId(id);
			session.setAttribute(Const.USUARIO, usuario);
			redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.cadastro.usuario");
			
			return "redirect:home";
		} catch (DuplicateKeyException ex) {
			result.rejectValue("email", "msg.erro.email.ja.cadastrado");
			return "usuario/cadastro";
		}
	}

	@GetMapping("/reenvioDeSenha")
	public String mostrarTelaReenvioDeSenha(Usuario usuario) {
		return "usuario/reenvioDeSenha";
	}

	@PostMapping("/reenvioDeSenha")
	public String reenviarSenha(@Valid Usuario usuario, BindingResult result, Model model, Locale locale) { // TODO: Tentar passar esse código para a camada de serviço

		if (result.hasFieldErrors("email")) {
			return "usuario/reenvioDeSenha";
		}
		// TODO: passar este código para camada service
		Usuario usuarioBuscado = usuarioDao.buscarPeloEmail(usuario.getEmail());
		if (usuarioBuscado == null) {
			model.addAttribute(Const.STR_COD_MSG_ERRO, "msg.erro.buscar.email");
			return "usuario/reenvioDeSenha";
		}
		
		boolean enviou = emailService.enviarEmailSimples(usuarioBuscado.getEmail(), 
										messageSource.getMessage("email.reenvio.senha.assunto", null, locale), 
										messageSource.getMessage("email.reenvio.senha.texto", new Object[] {usuarioBuscado.getNome(), usuarioBuscado.getSenha()}, locale));
		
		if (!enviou) {
			model.addAttribute(Const.STR_COD_MSG_ERRO, "msg.erro.envio.email");
			return "usuario/reenvioDeSenha";
		}
		
		model.addAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.reenvio.senha");
		return "usuario/login";
	}
}
