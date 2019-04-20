package br.com.evisas.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.evisas.entity.Autenticador;
import br.com.evisas.entity.SolicitacaoVisto;
import br.com.evisas.entity.Usuario;

public interface SolicitacaoVistoService {
	void criar(SolicitacaoVisto solicitacaoVisto);
	void editar(SolicitacaoVisto solicitacaoVisto);
	List<SolicitacaoVisto> buscarPorUsuario(long idUsuario);
	SolicitacaoVisto buscarPorId(long id, Autenticador autenticador);
	MultipartFile buscarDocumentoSolicitacaoVisto(Long id, Usuario usuario);
	void alterarStatus(SolicitacaoVisto solicitacaoVisto, Autenticador autenticador);
	void alterarStatusEMotivoRecusa(SolicitacaoVisto solicitacaoVisto, Autenticador autenticador);
	List<SolicitacaoVisto> listar();
}
