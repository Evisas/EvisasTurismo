package br.com.evisas.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.evisas.config.businessError.HandleBusinessError;
import br.com.evisas.entity.SolicitacaoVisto;
import br.com.evisas.entity.Usuario;
import br.com.evisas.service.SolicitacaoVistoService;
import br.com.evisas.util.Const;

@Controller
public class SolicitacaoVistoController {

	@Autowired
	private SolicitacaoVistoService solicitacaoVistoService;
	
	@GetMapping("/solicitacaoVisto")
	public String mostrarTelaSolicitarVisto(SolicitacaoVisto solicitacao) {
		return "solicitacao/solicitacaoVisto";
	}

	@PostMapping("/solicitacaoVisto")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoVisto")
	public String solicitarVisto(@Valid SolicitacaoVisto solicitacao, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasErrors()) {
			return "solicitacao/solicitacaoVisto";
		}
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		solicitacao.setIdUsuario(usuario.getId());

		solicitacaoVistoService.criar(solicitacao);

		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.solicitar.visto");
		return "redirect:acompanhamentoSolicitacoes";
	}
}
