package br.com.evisas.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.evisas.util.StringFormatUtils;

public class SolicitacaoDeDocumento {
	
	public enum Status {
		PENDENTE, ACEITA, RECUSADA, CANCELADA
	}
	
	private long id;

	private long idUsuario;
	
	@NotBlank
	@Size(min=2, max=80)
	private String nomeSolicitante;

	@NotBlank
	@Size(min=11, max=11)
	@Pattern(regexp="^[0-9]{11}$")
	private String cpfSolicitante;
	
	private Status status;
	
	@Size(max=100)
	private String motivoRecusa;

	@Size(max=100)
	private String observacao;

	private LocalDateTime dataSolicitacao;
	
	public SolicitacaoDeDocumento() {}

	public SolicitacaoDeDocumento(long id, Status status) {
		this.id = id;
		this.status = status;
	}

	public long getId() {
		return id;
	}
	
	public String getIdFormatado() {
		return StringFormatUtils.preencherAEsquerdaComZeros(id, 7);
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getCpfSolicitante() {
		return cpfSolicitante;
	}

	public void setCpfSolicitante(String cpfSolicitante) {
		this.cpfSolicitante = cpfSolicitante;
	}

	public String getCpfSolicitanteFormatado() {
        return StringFormatUtils.formatarCpf(cpfSolicitante);
	}

	public void setCpfSolicitanteFormatado(String cpfSolicitanteFormatado) {
		this.cpfSolicitante = StringFormatUtils.obterSomenteNumeros(cpfSolicitanteFormatado);
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMotivoRecusa() {
		return motivoRecusa;
	}

	public void setMotivoRecusa(String motivoRecusa) {
		this.motivoRecusa = motivoRecusa;
	}

	public String getDataSolicitacaoFormatada() {
		return StringFormatUtils.format(dataSolicitacao);
	}

	public LocalDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public String getIdUsuarioFormatado() {
		return StringFormatUtils.preencherAEsquerdaComZeros(idUsuario, 5);
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
