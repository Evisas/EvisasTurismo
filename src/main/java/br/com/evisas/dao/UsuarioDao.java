package br.com.evisas.dao;

import br.com.evisas.entity.Usuario;

public interface UsuarioDao {

	public long criar(Usuario usuario);
	public Usuario buscarPeloLogin(Usuario usuario);
	public Usuario buscarPeloEmail(String email);
}
