package br.com.evisas.service;

import java.util.List;

import br.com.evisas.entity.Autenticador;
import br.com.evisas.entity.SolicitacaoPassaporte;

public interface SolicitacaoPassaporteService {
	void criar(SolicitacaoPassaporte solicitacaoPassaporte);
	void editar(SolicitacaoPassaporte solicitacaoPassaporte);
	List<SolicitacaoPassaporte> buscarPorUsuario(long idUsuario);
	SolicitacaoPassaporte buscarPorId(long id, Autenticador autenticador);
	void alterarStatus(SolicitacaoPassaporte solicitacaoPassaporte, Autenticador autenticador);
	void alterarStatusEMotivoRecusa(SolicitacaoPassaporte solicitacaoPassaporte, Autenticador autenticador);
	List<SolicitacaoPassaporte> listar();
}
