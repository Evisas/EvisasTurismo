package br.com.evisas.dao;

import java.util.List;

import br.com.evisas.entity.SolicitacaoPassaporte;

public interface SolicitacaoPassaporteDao {
	long criar(SolicitacaoPassaporte solicitacaoPassaporte);
	boolean editar(SolicitacaoPassaporte solicitacaoPassaporte);
	List<SolicitacaoPassaporte> listar();
	List<SolicitacaoPassaporte> buscarPorUsuario(long idUsuario);
	SolicitacaoPassaporte buscarPorId(long id);
	boolean alterarStatus(SolicitacaoPassaporte solicitacaoPassaporte);
	boolean alterarStatusVerificaUsuario(SolicitacaoPassaporte solicitacaoPassaporte);
	boolean alterarStatusEMotivoRecusa(SolicitacaoPassaporte solicitacaoPassaporte);
}
