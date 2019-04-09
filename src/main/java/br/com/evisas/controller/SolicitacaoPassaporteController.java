package br.com.evisas.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.evisas.config.businessError.HandleBusinessError;
import br.com.evisas.entity.SolicitacaoDeDocumento.Status;
import br.com.evisas.entity.SolicitacaoPassaporte;
import br.com.evisas.entity.Usuario;
import br.com.evisas.service.SolicitacaoPassaporteService;
import br.com.evisas.util.Const;

@Controller
public class SolicitacaoPassaporteController {

	@Autowired
	private SolicitacaoPassaporteService solicitacaoPassaporteService;
	
	@GetMapping("/solicitacaoPassaporte")
	public String mostrarTelaSolicitarPassaporte(Model model) {
		model.addAttribute(Const.SOLICITACAO, new SolicitacaoPassaporte());
		return "solicitacao/solicitacaoPassaporte";
	}

	@PostMapping("/solicitacaoPassaporte")
	public String solicitarPassaporte(@Valid SolicitacaoPassaporte solicitacao, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasErrors()) {
			return "solicitacao/solicitacaoPassaporte";
		}
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		solicitacao.setIdUsuario(usuario.getId());
		
		solicitacaoPassaporteService.criar(solicitacao);
		
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.solicitar.passaporte");
		return "redirect:acompanhamentoSolicitacoes";
	}

	@GetMapping("/consultaSolicitacaoPassaporte")
	@HandleBusinessError(errorPage="solicitacao/acompanhamento")
	public String consultarSolicitacaoPassaporte(@RequestParam Long id, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		model.addAttribute("solicitacao", solicitacaoPassaporteService.buscarPorId(id, usuario));
		return "solicitacao/solicitacaoPassaporte";
	}

	@GetMapping("/cancelarSolicitacaoPassaporte")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoPassaporte")
	public String cancelarSolicitacaoPassaporte(@RequestParam Long id, RedirectAttributes redirectAttr, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		SolicitacaoPassaporte solicitacaoPassaporte = new SolicitacaoPassaporte();
		solicitacaoPassaporte.setId(id);
		solicitacaoPassaporte.setStatus(Status.CANCELADA);
		
		solicitacaoPassaporteService.alterarStatus(solicitacaoPassaporte, usuario);

		redirectAttr.addAttribute("id", id);
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.cancelar.solicitacao.passaporte");
		
		return "redirect:consultaSolicitacaoPassaporte";
	}
	
	@PostMapping("/edicaoSolicitacaoPassaporte")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoPassaporte")
	public String editarSolicitacaoPassaporte(@Valid SolicitacaoPassaporte solicitacao, BindingResult result, Model model, RedirectAttributes redirectAttr, HttpSession session) {
		model.addAttribute(Const.EH_EDICAO, true);
		if (result.hasErrors()) {
			return "solicitacao/solicitacaoPassaporte";
		}
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		solicitacao.setIdUsuario(usuario.getId());
		
		solicitacaoPassaporteService.editar(solicitacao);
		
		redirectAttr.addAttribute("id", solicitacao.getId());
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.editar.solicitacao.passaporte");

		return "redirect:consultaSolicitacaoPassaporte";
	}
}
