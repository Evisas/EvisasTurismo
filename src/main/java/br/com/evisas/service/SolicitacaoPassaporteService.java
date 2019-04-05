package br.com.evisas.service;

import java.util.List;

import br.com.evisas.entity.SolicitacaoPassaporte;

public interface SolicitacaoPassaporteService {
	void criar(SolicitacaoPassaporte solicitacaoPassaporte);
	public List<SolicitacaoPassaporte> buscarPorUsuario(long idUsuario);
	public SolicitacaoPassaporte buscarPorId(long id);
}
