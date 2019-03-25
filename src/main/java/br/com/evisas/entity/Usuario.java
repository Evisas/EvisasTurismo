package br.com.evisas.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class Usuario {
	
	private long id;
	
	@NotBlank
	@Size(min=2, max=80)
	private String nome;

	@Email
	@NotBlank
	@Size(max=40)
	private String email;
	
	@Pattern(regexp="[0-9]{10,11}")
	private String telefone;
	
	@NotBlank
	@Size(min=4, max=15)
	private String senha;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
