package br.com.evisas.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.evisas.dao.UsuarioDao;
import br.com.evisas.entity.Usuario;
import br.com.evisas.util.Const;
@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@RequestMapping("/")
	public String entrarNoSite() {
		return "redirect:login";
	}

	@RequestMapping("/login")
	public String mostrarTelaLogin(Usuario usuario) {
		return "usuario/login";
	}

	@RequestMapping("/logar")
	public String fazerLogin(@Valid Usuario usuario, BindingResult result, Model model, HttpSession session) {
		if (result.hasFieldErrors("email") || result.hasFieldErrors("senha")) {
			return "usuario/login";
		}
		
		Usuario usuarioBuscado = usuarioDao.buscarPeloLogin(usuario);
		if (usuarioBuscado != null) {
			session.setAttribute("usuario", usuarioBuscado);
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
}
