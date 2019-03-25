package br.com.evisas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SolicitacaoController {

	@RequestMapping("/solicitacaoPassaporte")
	public String mostrarTelaSolicitarPassaporte() {
		return "solicitacao/solicitacaoPassaporte";
	}

	@RequestMapping("/solicitacaoVisto")
	public String mostrarTelaSolicitarVisto() {
		return "solicitacao/solicitacaoVisto";
	}

	@RequestMapping("/acompanharSolicitacoes")
	public String mostrarTelaAcompanhamentoDeSolicitacoes() {
		return "solicitacao/acompanhamento";
	}
}
