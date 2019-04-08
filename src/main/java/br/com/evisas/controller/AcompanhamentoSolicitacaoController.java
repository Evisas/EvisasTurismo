package br.com.evisas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.evisas.entity.Usuario;
import br.com.evisas.service.SolicitacaoPassaporteService;
import br.com.evisas.service.SolicitacaoVistoService;
import br.com.evisas.util.Const;

@Controller
public class AcompanhamentoSolicitacaoController {

	@Autowired
	private SolicitacaoPassaporteService solicitacaoPassaporteService;
	
	@Autowired
	private SolicitacaoVistoService solicitacaoVistoService;
	
	@GetMapping("/acompanhamentoSolicitacoes")
	public String mostrarTelaAcompanhamentoDeSolicitacoes(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		model.addAttribute("solicitacoesPassaporte", solicitacaoPassaporteService.buscarPorUsuario(usuario.getId()));
		model.addAttribute("solicitacoesVisto", solicitacaoVistoService.buscarPorUsuario(usuario.getId()));
		return "solicitacao/acompanhamento";
	}
}
