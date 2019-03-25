package br.com.evisas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

		return "welcome";
	}
}
