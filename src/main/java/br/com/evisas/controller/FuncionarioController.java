package br.com.evisas.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.evisas.config.businessError.HandleBusinessError;
import br.com.evisas.entity.Funcionario;
import br.com.evisas.service.FuncionarioService;
import br.com.evisas.util.Const;

@Controller
@RequestMapping("/admin")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioServices;
	
	@GetMapping
	public String entrarNoSite() {
		return "redirect:admin/login";
	}

	@GetMapping("/login")
	public String mostrarTelaLogin(Funcionario funcionario) {
		return "admin/login";
	}

	@PostMapping("/login")
	@HandleBusinessError(errorPage="admin/login")
	public String fazerLogin(@Valid Funcionario funcionario, BindingResult result, HttpSession session) {
		if (result.hasFieldErrors("matricula") || result.hasFieldErrors("senha")) {
			return "admin/login";
		}

		Funcionario funcionarioBuscado = funcionarioServices.buscarPeloLogin(funcionario);
		session.setAttribute(Const.AUTENTICADOR, funcionarioBuscado);
		return "redirect:home";
	}

	@GetMapping("/home")
	public String mostrarTelaHome() {
		return "admin/home";
	}

	@GetMapping("/logout")
	public String fazerLogout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
}
