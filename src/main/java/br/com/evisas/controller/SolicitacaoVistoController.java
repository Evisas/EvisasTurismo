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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	public String mostrarTelaSolicitarVisto(@ModelAttribute(Const.SOLICITACAO) SolicitacaoVisto solicitacao) {
		return "solicitacao/solicitacaoVisto";
	}

	@PostMapping("/solicitacaoVisto")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoVisto")
	public String solicitarVisto(@Valid @ModelAttribute(Const.SOLICITACAO) SolicitacaoVisto solicitacao, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasErrors()) {
			return "solicitacao/solicitacaoVisto";
		}
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		solicitacao.setIdUsuario(usuario.getId());

		solicitacaoVistoService.criar(solicitacao);

		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.solicitar.visto");
		return "redirect:acompanhamentoSolicitacoes";
	}

	@GetMapping("/consultaSolicitacaoVisto")
	@HandleBusinessError(errorPage="solicitacao/acompanhamento")
	public String consultarSolicitacaoVisto(@RequestParam Long id, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		model.addAttribute("solicitacao", solicitacaoVistoService.buscarPorId(id, usuario));
		return "solicitacao/solicitacaoVisto";
	}
	
	@GetMapping("/baixarDocumentoSolicitacaoVisto")
	public ResponseEntity<InputStreamResource> baixarDocumentoSolicitacaoVisto(@RequestParam Long id, HttpSession session) throws IOException {
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		MultipartFile file = solicitacaoVistoService.buscarDocumentoSolicitacaoVisto(id, usuario);
        return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentLength(file.getSize())
				.body(new InputStreamResource(file.getInputStream()));
	}
}
