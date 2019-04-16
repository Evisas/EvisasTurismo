package br.com.evisas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.evisas.config.businessError.BusinessException;
import br.com.evisas.dao.SolicitacaoVistoDao;
import br.com.evisas.entity.Autenticador;
import br.com.evisas.entity.SolicitacaoDeDocumento;
import br.com.evisas.entity.SolicitacaoVisto;
import br.com.evisas.entity.Usuario;
import br.com.evisas.entity.SolicitacaoDeDocumento.Status;
import br.com.evisas.util.FileUtil;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SolicitacaoVistoServiceImpl implements SolicitacaoVistoService {

	@Value("${path.documento.solicitacao.visto}")
	private String PATH_DOCTO_SOLICITACAO_VISTO;

	@Value("${path.busca.documento.solicitacao.visto}")
	private String PATH_BUSCA_DOCTO_SOLICITACAO_VISTO;

	@Value("${nome.documento.solicitacao.visto}")
	private String NOME_DOCTO_SOLICITACAO_VISTO;

	@Autowired
	private SolicitacaoVistoDao solicitacaoVistoDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void criar(SolicitacaoVisto solicitacaoVisto) {
		solicitacaoVisto.setStatus(Status.PENDENTE);
		solicitacaoVisto.setDataSolicitacao(LocalDateTime.now());

		try {
			long id = solicitacaoVistoDao.criar(solicitacaoVisto);
			solicitacaoVisto.setId(id);
			FileUtil.gravarArquivo(montarPathDoctoSolicitacaoVisto(solicitacaoVisto), solicitacaoVisto.getDocumento());
		} catch (BusinessException e) {
			solicitacaoVisto.setStatus(null);
			solicitacaoVisto.setDataSolicitacao(null);
			throw e;
		}
	}

	@Override
	public List<SolicitacaoVisto> buscarPorUsuario(long idUsuario) {
		return solicitacaoVistoDao.buscarPorUsuario(idUsuario);
	}

	@Override
	public SolicitacaoVisto buscarPorId(long id, Autenticador autenticador) {
		SolicitacaoVisto solicitacaoVisto = solicitacaoVistoDao.buscarPorId(id);
		if (temPermissaoAcesso(autenticador, solicitacaoVisto)) {
			solicitacaoVisto.setDocumento(FileUtil.buscarArquivoQualquerExtensao(montarPathBuscaDoctoSolicitacaoVisto(solicitacaoVisto), NOME_DOCTO_SOLICITACAO_VISTO));
			return solicitacaoVisto;
		} else {
			throw new BusinessException("msg.erro.permissao.acesso.solicitacao");
		}
	}

	@Override
	public MultipartFile buscarDocumentoSolicitacaoVisto(Long id, Usuario usuario) {
		SolicitacaoVisto solicitacaoVisto = new SolicitacaoVisto();
		solicitacaoVisto.setId(id);
		solicitacaoVisto.setIdUsuario(usuario.getId());
		return FileUtil.buscarArquivoQualquerExtensao(montarPathBuscaDoctoSolicitacaoVisto(solicitacaoVisto), NOME_DOCTO_SOLICITACAO_VISTO);
	}

	private String montarPathDoctoSolicitacaoVisto(SolicitacaoVisto solicitacaoVisto) {
		return String.format(PATH_DOCTO_SOLICITACAO_VISTO,
							 solicitacaoVisto.getIdUsuarioFormatado(),
							 solicitacaoVisto.getIdFormatado(),
							 StringUtils.getFilenameExtension(solicitacaoVisto.getDocumento().getOriginalFilename()));
	}

	private String montarPathBuscaDoctoSolicitacaoVisto(SolicitacaoVisto solicitacaoVisto) {
		return String.format(PATH_BUSCA_DOCTO_SOLICITACAO_VISTO,
							 solicitacaoVisto.getIdUsuarioFormatado(),
							 solicitacaoVisto.getIdFormatado());
	}

	private boolean temPermissaoAcesso(Autenticador autenticador, SolicitacaoDeDocumento solicitacao) {
		return autenticador.isFuncionario() || autenticador.getId() == solicitacao.getIdUsuario();
	}
}
