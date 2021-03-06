package br.com.evisas.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.evisas.config.businessError.HandleBusinessError;
import br.com.evisas.entity.Funcionario;
import br.com.evisas.entity.SolicitacaoDeDocumento.Status;
import br.com.evisas.entity.SolicitacaoVisto;
import br.com.evisas.entity.Usuario;
import br.com.evisas.service.SolicitacaoVistoService;
import br.com.evisas.util.Const;

@Controller
public class SolicitacaoVistoController {

	@Autowired
	private SolicitacaoVistoService solicitacaoVistoService;
	
	@GetMapping("/solicitacaoVisto")
	public String mostrarTelaSolicitarVisto(@ModelAttribute(Const.SOLICITACAO) SolicitacaoVisto solicitacao) {
		return "solicitacao/solicitacaoVisto";
	}

	@PostMapping("/solicitacaoVisto")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoVisto")
	public String solicitarVisto(@Valid @ModelAttribute(Const.SOLICITACAO) SolicitacaoVisto solicitacao, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasErrors()) {
			return "solicitacao/solicitacaoVisto";
		}
		Usuario usuario = (Usuario) session.getAttribute(Const.AUTENTICADOR);
		solicitacao.setIdUsuario(usuario.getId());

		solicitacaoVistoService.criar(solicitacao);

		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.solicitar.visto");
		return "redirect:acompanhamentoSolicitacoes";
	}

	@GetMapping("/consultaSolicitacaoVisto")
	@HandleBusinessError(errorPage="solicitacao/acompanhamento")
	public String consultarSolicitacaoVisto(@RequestParam Long id, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute(Const.AUTENTICADOR);
		model.addAttribute("solicitacao", solicitacaoVistoService.buscarPorId(id, usuario));
		return "solicitacao/solicitacaoVisto";
	}
	
	@GetMapping("/baixarDocumentoSolicitacaoVisto")
	public ResponseEntity<InputStreamResource> baixarDocumentoSolicitacaoVisto(@RequestParam Long id, HttpSession session) throws IOException {
		Usuario usuario = (Usuario) session.getAttribute(Const.AUTENTICADOR);
		MultipartFile file = solicitacaoVistoService.buscarDocumentoSolicitacaoVisto(id, usuario);
        return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentLength(file.getSize())
				.body(new InputStreamResource(file.getInputStream()));
	}
	
	@GetMapping("/cancelamentoSolicitacaoVisto")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoVisto")
	public String cancelarSolicitacaoVisto(@RequestParam Long id, RedirectAttributes redirectAttr, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute(Const.AUTENTICADOR);
		SolicitacaoVisto solicitacaoVisto = new SolicitacaoVisto();
		solicitacaoVisto.setId(id);
		solicitacaoVisto.setStatus(Status.CANCELADA);
		
		solicitacaoVistoService.alterarStatusEMotivoRecusa(solicitacaoVisto, usuario);

		redirectAttr.addAttribute("id", id);
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.cancelar.solicitacao.visto");
		
		return "redirect:consultaSolicitacaoVisto";
	}

	@PostMapping("/edicaoSolicitacaoVisto")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoVisto")
	public String editarSolicitacaoVisto(@Valid SolicitacaoVisto solicitacao, BindingResult result, Model model, RedirectAttributes redirectAttr, HttpSession session) {
		model.addAttribute(Const.EH_EDICAO, true);
		if (result.hasErrors()) {
			return "solicitacao/solicitacaoVisto";
		}
		Usuario usuario = (Usuario) session.getAttribute(Const.AUTENTICADOR);
		solicitacao.setIdUsuario(usuario.getId());
		
		solicitacaoVistoService.editar(solicitacao);
		
		redirectAttr.addAttribute("id", solicitacao.getId());
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.editar.solicitacao.visto");

		return "redirect:consultaSolicitacaoVisto";
	}
	
//	------------------------------- MÉDOTOS PARA PERMISSÃO ADMIN -------------------------------
	
	@GetMapping("admin/solicitacoesVisto")
	public String mostrarTelaSolicitacoesVisto(Model model) {
		model.addAttribute("solicitacoesVisto", solicitacaoVistoService.listar());
		return "solicitacao/solicitacoesVisto";
	}

	@GetMapping("admin/consultaSolicitacaoVisto")
	@HandleBusinessError(errorPage="admin/solicitacoesVisto")
	public String consultarSolicitacaoVistoAdmin(@RequestParam Long id, Model model, HttpSession session) {
		Funcionario funcionario = (Funcionario) session.getAttribute(Const.AUTENTICADOR);
		model.addAttribute("solicitacao", solicitacaoVistoService.buscarPorId(id, funcionario));
		return "solicitacao/solicitacaoVisto";
	}
	
	@GetMapping("admin/aceitaSolicitacaoVisto")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoVisto")
	public String aceitarSolicitacaoVisto(@RequestParam Long id, RedirectAttributes redirectAttr, HttpSession session) {
		Funcionario funcionario = (Funcionario) session.getAttribute(Const.AUTENTICADOR);
		SolicitacaoVisto solicitacaoVisto = new SolicitacaoVisto(id, Status.ACEITA);
		solicitacaoVistoService.alterarStatusEMotivoRecusa(solicitacaoVisto, funcionario);

		redirectAttr.addAttribute("id", id);
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.aceitar.solicitacao.visto");
		
		return "redirect:consultaSolicitacaoVisto";
	}
	
	@RequestMapping("admin/recusaSolicitacaoVisto")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoVisto")
	public String recusarSolicitacaoVisto(@Valid @ModelAttribute(Const.SOLICITACAO) SolicitacaoVisto solicitacao, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasFieldErrors("motivoRecusa")) {
			return "solicitacao/solicitacaoVisto";
		}
		Funcionario funcionario = (Funcionario) session.getAttribute(Const.AUTENTICADOR);
		solicitacao.setStatus(Status.RECUSADA);
		solicitacaoVistoService.alterarStatusEMotivoRecusa(solicitacao, funcionario);

		redirectAttr.addAttribute("id", solicitacao.getId());
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.recusar.solicitacao.visto");
		
		return "redirect:consultaSolicitacaoVisto";
	}
}
