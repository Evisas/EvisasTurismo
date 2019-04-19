package br.com.evisas.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class Funcionario implements Autenticador {

	private long id;
	
	@Size(min=4, max=4)
	private String matricula;
	
	@NotBlank
	@Size(min=2, max=80)
	private String nome;

	@Email
	@NotBlank
	@Size(max=40)
	private String email;
	
	@NotBlank
	@Size(min=4, max=15)
	private String senha;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public boolean isFuncionario() {
		return true;
	}
}
