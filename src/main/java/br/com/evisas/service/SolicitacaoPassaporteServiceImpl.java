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
	
		long id = solicitacaoPassaporteDao.criar(solicitacaoPassaporte);
		solicitacaoPassaporte.setId(id);
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
}
