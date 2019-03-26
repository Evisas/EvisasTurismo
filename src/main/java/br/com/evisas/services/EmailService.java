package br.com.evisas.services;

public interface EmailService {
	public boolean enviarEmailSimples(String destinatario, String assunto, String texto);
}
