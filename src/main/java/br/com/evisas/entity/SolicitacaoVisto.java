package br.com.evisas.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class SolicitacaoVisto extends SolicitacaoDeDocumento {

	private String paisDeResidencia;
	private String paisAVisitar;
	private boolean possuiPassaporte;
	private Date dataNascimentoSolicitante;
	private byte[] documento;
	
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

	public Date getDataNascimentoSolicitante() {
		return dataNascimentoSolicitante;
	}

	public void setDataNascimentoSolicitante(Date dataNascimentoSolicitante) {
		this.dataNascimentoSolicitante = dataNascimentoSolicitante;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}
}
