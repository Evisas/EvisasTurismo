package br.com.evisas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.evisas.dao.SolicitacaoPassaporteDao;
import br.com.evisas.entity.SolicitacaoPassaporte;
import br.com.evisas.entity.SolicitacaoVisto;
import br.com.evisas.util.Const;

@Controller
public class SolicitacaoController {

	@Autowired
	private SolicitacaoPassaporteDao solicitacaoPassaporteDao;
	
	@GetMapping("/solicitacaoPassaporte")
	public String mostrarTelaSolicitarPassaporte(SolicitacaoPassaporte solicitacaoPassaporte) {
		return "solicitacao/solicitacaoPassaporte";
	}

	@PostMapping("/solicitacaoPassaporte")
	public String solicitarPassaporte(@Valid SolicitacaoPassaporte solicitacaoPassaporte, BindingResult result, RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			return "solicitacao/solicitacaoPassaporte";
		}
		long id = solicitacaoPassaporteDao.criar(solicitacaoPassaporte);
		solicitacaoPassaporte.setId(id);
		redirectAttr.addFlashAttribute(Const.STR_COD_MSG_SUCESSO, "msg.sucesso.solicitar.passaporte");
		
		return "redirect:acompanhamentoSolicitacoes";
		
	}

	@GetMapping("/solicitacaoVisto")
	public String mostrarTelaSolicitarVisto(SolicitacaoVisto solicitacaoVisto) {
		return "solicitacao/solicitacaoVisto";
	}

	@GetMapping("/acompanhamentoSolicitacoes")
	public String mostrarTelaAcompanhamentoDeSolicitacoes() {
		return "solicitacao/acompanhamento";
	}
}
