package br.com.evisas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evisas.config.businessError.BusinessException;
import br.com.evisas.dao.FuncionarioDao;
import br.com.evisas.entity.Funcionario;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioDao funcionarioDao;
	
	@Override
	public Funcionario buscarPeloLogin(Funcionario funcionario) {
		Funcionario funcionarioBuscado = funcionarioDao.buscarPeloLogin(funcionario);
		if (funcionarioBuscado != null) {
			return funcionarioBuscado;
		} else {
			throw new BusinessException("msg.erro.login.funcionario.invalido");
		}
	}
}
