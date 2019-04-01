package br.com.evisas.dao;

import java.util.List;

import br.com.evisas.entity.SolicitacaoPassaporte;

public interface SolicitacaoPassaporteDao {
	public long criar(SolicitacaoPassaporte solicitacaoPassaporte);
	public List<SolicitacaoPassaporte> listar();
	public SolicitacaoPassaporte buscarPorId(long id);
	public void alterarStatus(SolicitacaoPassaporte solicitacaoPassaporte);
	public void recusar(SolicitacaoPassaporte solicitacaoPassaporte);
}
