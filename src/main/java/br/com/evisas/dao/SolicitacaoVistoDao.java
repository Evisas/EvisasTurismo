package br.com.evisas.dao;

import java.util.List;

import br.com.evisas.entity.SolicitacaoVisto;

public interface SolicitacaoVistoDao {

	long criar(SolicitacaoVisto solicitacaoVisto);
	public List<SolicitacaoVisto> listar();
	public List<SolicitacaoVisto> buscarPorUsuario(long idUsuario);
	public SolicitacaoVisto buscarPorId(long id);
	public void alterarStatus(SolicitacaoVisto solicitacaoVisto);
	public void recusar(SolicitacaoVisto solicitacaoVisto);

}
