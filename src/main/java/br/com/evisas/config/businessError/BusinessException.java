package br.com.evisas.config.businessError;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -44449382507623581L;

	private String campoErro;

	public BusinessException(String codigoMensagem) {
		super(codigoMensagem);
	}

	public BusinessException(String campoErro, String codigoMensagem) {
		super(codigoMensagem);
		this.campoErro = campoErro;
	}

	public BusinessException(String campoErro, String codigoMensagem, Throwable throwable) {
		super(codigoMensagem, throwable);
		this.campoErro = campoErro;
	}

	public String getCampoErro() {
		return campoErro;
	}
}
