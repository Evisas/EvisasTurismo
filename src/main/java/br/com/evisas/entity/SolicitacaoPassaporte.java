package br.com.evisas.entity;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class SolicitacaoPassaporte extends SolicitacaoDeDocumento {

	@NotBlank
	@Size(min=5, max=20)
	@Pattern(regexp="^[0-9]{5,20}$")
	private String rgSolicitante;

	@NotNull
	@Future
	private LocalDate previsaoSaida;

	public String getRgSolicitante() {
		return rgSolicitante;
	}

	public void setRgSolicitante(String rgSolicitante) {
		this.rgSolicitante = rgSolicitante;
	}

	public LocalDate getPrevisaoSaida() {
		return previsaoSaida;
	}

	public void setPrevisaoSaida(LocalDate previsaoSaida) {
		this.previsaoSaida = previsaoSaida;
	}
}
