package br.com.evisas.entity;

import java.io.File;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class SolicitacaoVisto extends SolicitacaoDeDocumento {

	@NotBlank
	@Size(max=30)
	private String paisDeResidencia;

	@NotBlank
	@Size(max=30)
	private String paisAVisitar;

	@NotNull
	private Boolean possuiPassaporte;

	@NotNull
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataNascimentoSolicitante;
	
	private File documento;
	
	public String getPaisDeResidencia() {
		return paisDeResidencia;
	}
	
	public void setPaisDeResidencia(String paisDeResidencia) {
		this.paisDeResidencia = paisDeResidencia;
	}

	public String getPaisAVisitar() {
		return paisAVisitar;
	}

	public void setPaisAVisitar(String paisAVisitar) {
		this.paisAVisitar = paisAVisitar;
	}

	public Boolean getPossuiPassaporte() {
		return possuiPassaporte;
	}

	public void setPossuiPassaporte(Boolean possuiPassaporte) {
		this.possuiPassaporte = possuiPassaporte;
	}

	public LocalDate getDataNascimentoSolicitante() {
		return dataNascimentoSolicitante;
	}

	public void setDataNascimentoSolicitante(LocalDate dataNascimentoSolicitante) {
		this.dataNascimentoSolicitante = dataNascimentoSolicitante;
	}

	public File getDocumento() {
		return documento;
	}

	public void setDocumento(File documento) {
		this.documento = documento;
	}
}
