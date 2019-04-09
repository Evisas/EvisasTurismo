package br.com.evisas.dao;

import java.util.List;

import br.com.evisas.entity.SolicitacaoPassaporte;

public interface SolicitacaoPassaporteDao {
	public long criar(SolicitacaoPassaporte solicitacaoPassaporte);
	public boolean editar(SolicitacaoPassaporte solicitacaoPassaporte);
	public List<SolicitacaoPassaporte> listar();
	public List<SolicitacaoPassaporte> buscarPorUsuario(long idUsuario);
	public SolicitacaoPassaporte buscarPorId(long id);
	public boolean alterarStatus(SolicitacaoPassaporte solicitacaoPassaporte);
	public boolean alterarStatusVerificaUsuario(SolicitacaoPassaporte solicitacaoPassaporte);
	public boolean recusar(SolicitacaoPassaporte solicitacaoPassaporte);
}
