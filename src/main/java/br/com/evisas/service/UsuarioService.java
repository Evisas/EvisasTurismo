package br.com.evisas.service;

import br.com.evisas.entity.Usuario;

public interface UsuarioService {

	Usuario buscarPeloLogin(Usuario usuario);
	void cadastrar(Usuario usuario);
	void enviarEmailSenha(Usuario usuario);
}
