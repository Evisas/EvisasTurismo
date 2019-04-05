package br.com.evisas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.evisas.dao.SolicitacaoPassaporteDao;
import br.com.evisas.entity.SolicitacaoPassaporte;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class SolicitacaoPassaporteServiceImpl implements SolicitacaoPassaporteService {

	@Autowired
	private SolicitacaoPassaporteDao solicitacaoPassaporteDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void criar(SolicitacaoPassaporte solicitacaoPassaporte) {
		long id = solicitacaoPassaporteDao.criar(solicitacaoPassaporte);
		solicitacaoPassaporte.setId(id);
	}

	@Override
	public List<SolicitacaoPassaporte> buscarPorUsuario(long idUsuario) {
		return solicitacaoPassaporteDao.buscarPorUsuario(idUsuario);
	}

	@Override
	public SolicitacaoPassaporte buscarPorId(long id) {
		return solicitacaoPassaporteDao.buscarPorId(id);
	}
}
