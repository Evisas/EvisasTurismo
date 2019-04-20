package br.com.evisas.dao;

import java.util.List;

import br.com.evisas.entity.SolicitacaoVisto;

public interface SolicitacaoVistoDao {

	long criar(SolicitacaoVisto solicitacaoVisto);
	boolean editar(SolicitacaoVisto solicitacaoVisto);
	List<SolicitacaoVisto> listar();
	List<SolicitacaoVisto> buscarPorUsuario(long idUsuario);
	SolicitacaoVisto buscarPorId(long id);
	boolean alterarStatus(SolicitacaoVisto solicitacaoVisto);
	boolean alterarStatusVerificaUsuario(SolicitacaoVisto solicitacaoVisto);
	boolean alterarStatusEMotivoRecusa(SolicitacaoVisto solicitacaoVisto);

}
