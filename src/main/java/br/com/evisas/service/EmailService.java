package br.com.evisas.service;

public interface EmailService {
	public boolean enviarEmailSimples(String destinatario, String assunto, String texto);
}
