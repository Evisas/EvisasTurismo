package br.com.evisas.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class SolicitacaoPassaporte extends SolicitacaoDeDocumento {

	private String rg;
	private Date previsaoSaida;

	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public Date getPrevisaoSaida() {
		return previsaoSaida;
	}
	
	public void setPrevisaoSaida(Date previsaoSaida) {
		this.previsaoSaida = previsaoSaida;
	}
}
