package br.com.evisas.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.evisas.config.validator.FileExtension;
import br.com.evisas.config.validator.FileExtension.Type;
import br.com.evisas.config.validator.FileSize;
import br.com.evisas.util.StringFormatUtils;

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
	
	@FileSize(max=20*1024*1024) // 20Mb
	@FileExtension(extensions="pdf,doc,docx,zip,rar,jpg,jpeg,png,bmp", type=Type.ONLY)
	private MultipartFile documento;

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

	public String getDataNascimentoSolicitanteFormatada() {
		return StringFormatUtils.format(dataNascimentoSolicitante);
	}

	public LocalDate getDataNascimentoSolicitante() {
		return dataNascimentoSolicitante;
	}

	public void setDataNascimentoSolicitante(LocalDate dataNascimentoSolicitante) {
		this.dataNascimentoSolicitante = dataNascimentoSolicitante;
	}

	public MultipartFile getDocumento() {
		return documento;
	}

	public void setDocumento(MultipartFile documento) {
		this.documento = documento;
	}
}
