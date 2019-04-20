package br.com.evisas.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.evisas.config.businessError.HandleBusinessError;
import br.com.evisas.entity.Funcionario;
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
	public String mostrarTelaSolicitarPassaporte(@ModelAttribute(Const.SOLICITACAO) SolicitacaoPassaporte solicitacao) {
		return "solicitacao/solicitacaoPassaporte";
	}

	@PostMapping("/solicitacaoPassaporte")
	public String solicitarPassaporte(@Valid @ModelAttribute(Const.SOLICITACAO) SolicitacaoPassaporte solicitacao, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasErrors()) {
			return "solicitacao/solicitacaoPassaporte";
		}
		Usuario usuario = (Usuario) session.getAttribute(Const.AUTENTICADOR);
		solicitacao.setIdUsuario(usuario.getId());
		
		solicitacaoPassaporteService.criar(solicitacao);
		
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.solicitar.passaporte");
		return "redirect:acompanhamentoSolicitacoes";
	}

	@GetMapping("/consultaSolicitacaoPassaporte")
	@HandleBusinessError(errorPage="solicitacao/acompanhamento")
	public String consultarSolicitacaoPassaporte(@RequestParam Long id, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute(Const.AUTENTICADOR);
		model.addAttribute(Const.SOLICITACAO, solicitacaoPassaporteService.buscarPorId(id, usuario));
		return "solicitacao/solicitacaoPassaporte";
	}

	@GetMapping("/cancelamentoSolicitacaoPassaporte")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoPassaporte")
	public String cancelarSolicitacaoPassaporte(@RequestParam Long id, RedirectAttributes redirectAttr, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute(Const.AUTENTICADOR);
		SolicitacaoPassaporte solicitacaoPassaporte = new SolicitacaoPassaporte(id, Status.CANCELADA);
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
		Usuario usuario = (Usuario) session.getAttribute(Const.AUTENTICADOR);
		solicitacao.setIdUsuario(usuario.getId());
		
		solicitacaoPassaporteService.editar(solicitacao);
		
		redirectAttr.addAttribute("id", solicitacao.getId());
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.editar.solicitacao.passaporte");

		return "redirect:consultaSolicitacaoPassaporte";
	}
	
//	------------------------------- MÉDOTOS PARA PERMISSÃO ADMIN -------------------------------
	
	@GetMapping("admin/solicitacoesPassaporte")
	public String mostrarTelaSolicitacoesPassaporte(Model model) {
		model.addAttribute("solicitacoesPassaporte", solicitacaoPassaporteService.listar());
		return "admin/solicitacoesPassaporte";
	}

	@GetMapping("admin/consultaSolicitacaoPassaporte")
	@HandleBusinessError(errorPage="admin/solicitacoesPassaporte")
	public String consultarSolicitacaoPassaporteAdmin(@RequestParam Long id, Model model, HttpSession session) {
		Funcionario funcionario = (Funcionario) session.getAttribute(Const.AUTENTICADOR);
		model.addAttribute(Const.SOLICITACAO, solicitacaoPassaporteService.buscarPorId(id, funcionario));
		return "solicitacao/solicitacaoPassaporte";
	}
	
	@GetMapping("admin/aceitaSolicitacaoPassaporte")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoPassaporte")
	public String aceitarSolicitacaoPassaporte(@RequestParam Long id, RedirectAttributes redirectAttr, HttpSession session) {
		Funcionario funcionario = (Funcionario) session.getAttribute(Const.AUTENTICADOR);
		SolicitacaoPassaporte solicitacaoPassaporte = new SolicitacaoPassaporte(id, Status.ACEITA);
		solicitacaoPassaporteService.alterarStatusEMotivoRecusa(solicitacaoPassaporte, funcionario);

		redirectAttr.addAttribute("id", id);
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.aceitar.solicitacao.passaporte");
		
		return "redirect:consultaSolicitacaoPassaporte";
	}
	
	@RequestMapping("admin/recusaSolicitacaoPassaporte")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoPassaporte")
	public String recusarSolicitacaoPassaporte(@Valid @ModelAttribute(Const.SOLICITACAO) SolicitacaoPassaporte solicitacao, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasFieldErrors("motivoRecusa")) {
			return "solicitacao/solicitacaoPassaporte";
		}
		Funcionario usuario = (Funcionario) session.getAttribute(Const.AUTENTICADOR);
		solicitacao.setStatus(Status.RECUSADA);
		solicitacaoPassaporteService.alterarStatusEMotivoRecusa(solicitacao, usuario);

		redirectAttr.addAttribute("id", solicitacao.getId());
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.recusar.solicitacao.passaporte");
		
		return "redirect:consultaSolicitacaoPassaporte";
	}
}
