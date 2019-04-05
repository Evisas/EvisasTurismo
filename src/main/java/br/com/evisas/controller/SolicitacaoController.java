package br.com.evisas.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.evisas.config.businessError.HandleBusinessError;
import br.com.evisas.entity.SolicitacaoPassaporte;
import br.com.evisas.entity.SolicitacaoVisto;
import br.com.evisas.entity.Usuario;
import br.com.evisas.service.SolicitacaoPassaporteService;
import br.com.evisas.service.SolicitacaoVistoService;
import br.com.evisas.util.Const;

@Controller
public class SolicitacaoController {

	@Autowired
	private SolicitacaoPassaporteService solicitacaoPassaporteService;
	
	@Autowired
	private SolicitacaoVistoService solicitacaoVistoService;
	
	@GetMapping("/solicitacaoPassaporte")
	public String mostrarTelaSolicitarPassaporte(SolicitacaoPassaporte solicitacaoPassaporte) {
		return "solicitacao/solicitacaoPassaporte";
	}

	@PostMapping("/solicitacaoPassaporte")
	public String solicitarPassaporte(@Valid SolicitacaoPassaporte solicitacaoPassaporte, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasErrors()) {
			return "solicitacao/solicitacaoPassaporte";
		}
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		solicitacaoPassaporte.setIdUsuario(usuario.getId());
		
		solicitacaoPassaporteService.criar(solicitacaoPassaporte);
		
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.solicitar.passaporte");
		return "redirect:acompanhamentoSolicitacoes";
	}

	@GetMapping("/solicitacaoVisto")
	public String mostrarTelaSolicitarVisto(SolicitacaoVisto solicitacaoVisto) {
		return "solicitacao/solicitacaoVisto";
	}

	@PostMapping("/solicitacaoVisto")
	@HandleBusinessError(errorPage="solicitacao/solicitacaoVisto")
	public String solicitarVisto(@Valid SolicitacaoVisto solicitacaoVisto, BindingResult result, RedirectAttributes redirectAttr, HttpSession session) {
		if (result.hasErrors()) {
			return "solicitacao/solicitacaoVisto";
		}
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		solicitacaoVisto.setIdUsuario(usuario.getId());

		solicitacaoVistoService.criar(solicitacaoVisto);

		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.solicitar.visto");
		return "redirect:acompanhamentoSolicitacoes";
	}

	@GetMapping("/acompanhamentoSolicitacoes")
	public ModelAndView mostrarTelaAcompanhamentoDeSolicitacoes(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("solicitacao/acompanhamento");
		Usuario usuario = (Usuario) session.getAttribute(Const.USUARIO);
		modelAndView.addObject("solicitacoesPassaporte", solicitacaoPassaporteService.buscarPorUsuario(usuario.getId()));
		modelAndView.addObject("solicitacoesVisto", solicitacaoVistoService.buscarPorUsuario(usuario.getId()));
		return modelAndView;
	}
}
