package br.com.evisas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.evisas.config.businessError.BusinessException;
import br.com.evisas.dao.SolicitacaoVistoDao;
import br.com.evisas.entity.Autenticador;
import br.com.evisas.entity.SolicitacaoDeDocumento;
import br.com.evisas.entity.SolicitacaoVisto;
import br.com.evisas.entity.SolicitacaoDeDocumento.Status;
import br.com.evisas.util.FileUtil;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SolicitacaoVistoServiceImpl implements SolicitacaoVistoService {

	@Value("${path.documento.solicitacao.visto}")
	private String PATH_DOCTO_SOLICITACAO_VISTO;

	@Autowired
	private SolicitacaoVistoDao solicitacaoVistoDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void criar(SolicitacaoVisto solicitacaoVisto) {
		solicitacaoVisto.setStatus(Status.PENDENTE);
		solicitacaoVisto.setDataSolicitacao(LocalDateTime.now());

		long id = solicitacaoVistoDao.criar(solicitacaoVisto);
		solicitacaoVisto.setId(id);
		FileUtil.gravarArquivo(montarPathDoctoSolicitacaoVisto(solicitacaoVisto), solicitacaoVisto.getDocumento());
	}

	private String montarPathDoctoSolicitacaoVisto(SolicitacaoVisto solicitacaoVisto) {
		return String.format(PATH_DOCTO_SOLICITACAO_VISTO,
							 solicitacaoVisto.getIdUsuarioFormatado(),
							 solicitacaoVisto.getIdFormatado(),
							 StringUtils.getFilenameExtension(solicitacaoVisto.getDocumento().getOriginalFilename()));
	}

	@Override
	public List<SolicitacaoVisto> buscarPorUsuario(long idUsuario) {
		return solicitacaoVistoDao.buscarPorUsuario(idUsuario);
	}

	@Override
	public SolicitacaoVisto buscarPorId(long id, Autenticador autenticador) {
		SolicitacaoVisto solicitacaoVisto = solicitacaoVistoDao.buscarPorId(id);
		if (temPermissaoAcesso(autenticador, solicitacaoVisto)) {
			return solicitacaoVisto;
		} else {
			throw new BusinessException("msg.erro.permissao.acesso.solicitacao");
		}
	}

	private boolean temPermissaoAcesso(Autenticador autenticador, SolicitacaoDeDocumento solicitacao) {
		return autenticador.isFuncionario() || autenticador.getId() == solicitacao.getIdUsuario();
	}
}
