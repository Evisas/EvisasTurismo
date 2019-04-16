package br.com.evisas.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.evisas.entity.Autenticador;
import br.com.evisas.entity.SolicitacaoVisto;
import br.com.evisas.entity.Usuario;

public interface SolicitacaoVistoService {
	void criar(SolicitacaoVisto solicitacaoVisto);
	public List<SolicitacaoVisto> buscarPorUsuario(long idUsuario);
	public SolicitacaoVisto buscarPorId(long id, Autenticador autenticador);
	MultipartFile buscarDocumentoSolicitacaoVisto(Long id, Usuario usuario);
}
