package br.com.evisas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.evisas.dao.SolicitacaoPassaporteDao;
import br.com.evisas.dao.SolicitacaoVistoDao;
import br.com.evisas.entity.SolicitacaoPassaporte;
import br.com.evisas.entity.SolicitacaoVisto;
import br.com.evisas.util.FileUtil;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SolicitacaoServiceImpl implements SolicitacaoService {

	@Value("${path.documento.solicitacao.visto}")
	private String PATH_DOCTO_SOLICITACAO_VISTO;

	@Autowired
	private SolicitacaoPassaporteDao solicitacaoPassaporteDao;
	
	@Autowired
	private SolicitacaoVistoDao solicitacaoVistoDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void criar(SolicitacaoPassaporte solicitacaoPassaporte) {
		long id = solicitacaoPassaporteDao.criar(solicitacaoPassaporte);
		solicitacaoPassaporte.setId(id);
	}

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
}
