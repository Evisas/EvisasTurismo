package br.com.evisas.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class SolicitacaoPassaporte extends SolicitacaoDeDocumento {

	private String rg;
	private LocalDate previsaoSaida;

	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getPrevisaoSaida() {
		return previsaoSaida;
	}

	public void setPrevisaoSaida(LocalDate previsaoSaida) {
		this.previsaoSaida = previsaoSaida;
	}
}
