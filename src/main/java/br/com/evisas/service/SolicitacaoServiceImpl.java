package br.com.evisas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evisas.dao.SolicitacaoPassaporteDao;
import br.com.evisas.dao.SolicitacaoVistoDao;
import br.com.evisas.entity.SolicitacaoPassaporte;
import br.com.evisas.entity.SolicitacaoVisto;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

	@Autowired
	private SolicitacaoPassaporteDao solicitacaoPassaporteDao;
	
	@Autowired
	private SolicitacaoVistoDao solicitacaoVistoDao;
	
	@Override
	public void criar(SolicitacaoPassaporte solicitacaoPassaporte) {
		long id = solicitacaoPassaporteDao.criar(solicitacaoPassaporte);
		solicitacaoPassaporte.setId(id);
	}

	@Override
	public void criar(SolicitacaoVisto solicitacaoVisto) {
		long id = solicitacaoVistoDao.criar(solicitacaoVisto);
		solicitacaoVisto.setId(id);
	}
}
