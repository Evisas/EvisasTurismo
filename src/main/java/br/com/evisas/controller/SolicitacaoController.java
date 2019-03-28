package br.com.evisas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.evisas.entity.SolicitacaoPassaporte;
import br.com.evisas.entity.SolicitacaoVisto;

@Controller
public class SolicitacaoController {

	@RequestMapping("/solicitacaoPassaporte")
	public String mostrarTelaSolicitarPassaporte(SolicitacaoPassaporte solicitacaoPassaporte) {
		return "solicitacao/solicitacaoPassaporte";
	}

	@RequestMapping("/solicitacaoVisto")
	public String mostrarTelaSolicitarVisto(SolicitacaoVisto solicitacaoVisto) {
		return "solicitacao/solicitacaoVisto";
	}

	@RequestMapping("/acompanharSolicitacoes")
	public String mostrarTelaAcompanhamentoDeSolicitacoes() {
		return "solicitacao/acompanhamento";
	}
}
