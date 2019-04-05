package br.com.evisas.service;

import java.util.List;

import br.com.evisas.entity.SolicitacaoVisto;

public interface SolicitacaoVistoService {
	void criar(SolicitacaoVisto solicitacaoVisto);
	public List<SolicitacaoVisto> buscarPorUsuario(long idUsuario);
	public SolicitacaoVisto buscarPorId(long id);
}
