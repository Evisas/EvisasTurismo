package br.com.evisas.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	void gravarArquivo(String strPath, MultipartFile arquivo);
	MultipartFile buscarArquivoMultipartQualquerExtensao(String strPathBusca, String nomeArquivoSaida);
	void removerArquivoQualquerExtensao(String strPath);
}
