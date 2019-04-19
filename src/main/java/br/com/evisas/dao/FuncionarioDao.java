package br.com.evisas.dao;

import br.com.evisas.entity.Funcionario;

public interface FuncionarioDao {

	Funcionario buscarPeloLogin(Funcionario funcionario);

}
