package br.com.evisas.service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.evisas.config.businessError.BusinessException;

@Service
public class FileServiceImpl implements FileService {

	@Value("${path.fileserver}")
	private String PATH_FILESERVER;

	private static final Logger logger = LogManager.getLogger(FileServiceImpl.class);

	@Override
	public void gravarArquivo(String strPath, MultipartFile arquivo) {
        Path path = Paths.get(PATH_FILESERVER + strPath);
        try {
        	Files.createDirectories(path.getParent());
//			Files.write(path, arquivo.getBytes());
			Files.copy(arquivo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("msg.erro.gravar.documento");
		}
	}

	public MultipartFile buscarArquivoMultipartQualquerExtensao(String strPathBusca, String nomeArquivoSaida) {
        File arquivo = buscarArquivoQualquerExtensao(PATH_FILESERVER + strPathBusca);
        if (arquivo == null) {
        	return null;
        }
        
		try {
			Path path = arquivo.toPath();
			String nomeArquivo = nomeArquivoSaida + "." + StringUtils.getFilenameExtension(path.toString());
			return new MockMultipartFile(nomeArquivo, Files.readAllBytes(path));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("msg.erro.ler.documento");
		}
	}


	public void removerArquivoQualquerExtensao(String strPathBusca) {
        File arquivo = buscarArquivoQualquerExtensao(PATH_FILESERVER + strPathBusca);
        if (arquivo != null) {
        	arquivo.delete();
        }
	}

	private File buscarArquivoQualquerExtensao(String strPathBusca) {
		File diretorio = new File(Paths.get(strPathBusca).getParent().toString());
		File[] arquivos = diretorio.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return StringUtils.stripFilenameExtension(file.getName()).equals(StringUtils.getFilename(strPathBusca));
			}
		});
        if (arquivos == null || arquivos.length == 0) {
        	return null;
        }
		return arquivos[0];
	}
}
