package br.com.evisas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.evisas.dao.SolicitacaoVistoDao;
import br.com.evisas.entity.SolicitacaoVisto;
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
	public SolicitacaoVisto buscarPorId(long id) {
		return solicitacaoVistoDao.buscarPorId(id);
	}
}
