package br.com.evisas.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.evisas.config.businessError.HandleBusinessError;
import br.com.evisas.entity.Usuario;
import br.com.evisas.service.UsuarioService;
import br.com.evisas.util.Const;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServices;
	
	@RequestMapping("/")
	public String entrarNoSite() {
		return "redirect:login";
	}

	@GetMapping("/login")
	public String mostrarTelaLogin(Usuario usuario) {
		return "usuario/login";
	}

	@PostMapping("/login")
	@HandleBusinessError(errorPage="usuario/login")
	public String fazerLogin(@Valid Usuario usuario, BindingResult result, Model model, HttpSession session) {
		if (result.hasFieldErrors("email") || result.hasFieldErrors("senha")) {
			return "usuario/login";
		}

		Usuario usuarioBuscado = usuarioServices.buscarPeloLogin(usuario);
		session.setAttribute(Const.USUARIO, usuarioBuscado);
		return "redirect:home";
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
	@HandleBusinessError(errorPage="usuario/cadastro")
	public String fazerCadastro(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasErrors()) {
			return "usuario/cadastro";
		}
		
		usuarioServices.cadastrar(usuario);
		session.setAttribute(Const.USUARIO, usuario);
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.cadastro.usuario");
		return "redirect:home";
	}

	@GetMapping("/reenvioDeSenha")
	public String mostrarTelaReenvioDeSenha(Usuario usuario) {
		return "usuario/reenvioDeSenha";
	}

	@PostMapping("/reenvioDeSenha")
	@HandleBusinessError(errorPage = "usuario/reenvioDeSenha")
	public String reenviarSenha(@Valid Usuario usuario, BindingResult result, Model model) {
		if (result.hasFieldErrors("email")) {
			return "usuario/reenvioDeSenha";
		}

		usuarioServices.enviarEmailSenha(usuario);
		
		model.addAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.reenvio.senha");
		return "usuario/login";
	}
}
