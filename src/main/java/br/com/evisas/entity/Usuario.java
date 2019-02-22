package br.com.evisas.entity;

import org.springframework.stereotype.Component;

@Component
public class Usuario extends Pessoa {
	
	private String telefone;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
