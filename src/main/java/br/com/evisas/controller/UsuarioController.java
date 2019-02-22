package br.com.evisas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioController {

	@RequestMapping("/welcome")
	public String firstPage() {
		return "welcome";
	}
}
