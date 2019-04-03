package br.com.evisas.service;

import br.com.evisas.entity.SolicitacaoPassaporte;
import br.com.evisas.entity.SolicitacaoVisto;

public interface SolicitacaoService {
	void criar(SolicitacaoPassaporte solicitacaoPassaporte);
	void criar(SolicitacaoVisto solicitacaoVisto);
}
