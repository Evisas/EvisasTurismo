package br.com.evisas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.evisas.config.businessError.BusinessException;
import br.com.evisas.dao.SolicitacaoPassaporteDao;
import br.com.evisas.entity.Autenticador;
import br.com.evisas.entity.SolicitacaoDeDocumento;
import br.com.evisas.entity.SolicitacaoDeDocumento.Status;
import br.com.evisas.entity.SolicitacaoPassaporte;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SolicitacaoPassaporteServiceImpl implements SolicitacaoPassaporteService {

	@Autowired
	private SolicitacaoPassaporteDao solicitacaoPassaporteDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void criar(SolicitacaoPassaporte solicitacaoPassaporte) {
		solicitacaoPassaporte.setStatus(Status.PENDENTE);
		solicitacaoPassaporte.setDataSolicitacao(LocalDateTime.now());
	
		try {
			long id = solicitacaoPassaporteDao.criar(solicitacaoPassaporte);
			solicitacaoPassaporte.setId(id);
		} catch (BusinessException e) {
			solicitacaoPassaporte.setStatus(null);
			solicitacaoPassaporte.setDataSolicitacao(null);
			throw e;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void editar(SolicitacaoPassaporte solicitacaoPassaporte) {
		solicitacaoPassaporte.setStatus(Status.PENDENTE);
		solicitacaoPassaporte.setDataSolicitacao(LocalDateTime.now());
		boolean editou = solicitacaoPassaporteDao.editar(solicitacaoPassaporte);
		if (!editou) {
			throw new BusinessException("msg.erro.editar.solicitacao");
		}
	}

	@Override
	public List<SolicitacaoPassaporte> buscarPorUsuario(long idUsuario) {
		return solicitacaoPassaporteDao.buscarPorUsuario(idUsuario);
	}

	@Override
	public SolicitacaoPassaporte buscarPorId(long id, Autenticador autenticador) {
		SolicitacaoPassaporte solicitacaoPassaporte = solicitacaoPassaporteDao.buscarPorId(id);
		if (temPermissaoAcesso(autenticador, solicitacaoPassaporte)) {
			return solicitacaoPassaporte;
		} else {
			throw new BusinessException("msg.erro.permissao.acesso.solicitacao");
		}
	}

	private boolean temPermissaoAcesso(Autenticador autenticador, SolicitacaoDeDocumento solicitacao) {
		return autenticador.isFuncionario() || autenticador.getId() == solicitacao.getIdUsuario();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void alterarStatus(SolicitacaoPassaporte solicitacaoPassaporte, Autenticador autenticador) {
		boolean alterou = false;
		if (autenticador.isFuncionario()) {
			alterou = solicitacaoPassaporteDao.alterarStatus(solicitacaoPassaporte);
		} else {
			solicitacaoPassaporte.setIdUsuario(autenticador.getId());
			alterou = solicitacaoPassaporteDao.alterarStatusVerificaUsuario(solicitacaoPassaporte);
		}
		
		if (!alterou) {
			throw new BusinessException("msg.erro.alterar.status.solicitacao");
		}
	}
}
