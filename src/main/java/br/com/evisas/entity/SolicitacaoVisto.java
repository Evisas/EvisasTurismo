package br.com.evisas.entity;

import java.io.File;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class SolicitacaoVisto extends SolicitacaoDeDocumento {

	private String paisDeResidencia;
	private String paisAVisitar;
	private boolean possuiPassaporte;
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

	public boolean isPossuiPassaporte() {
		return possuiPassaporte;
	}

	public void setPossuiPassaporte(boolean possuiPassaporte) {
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
