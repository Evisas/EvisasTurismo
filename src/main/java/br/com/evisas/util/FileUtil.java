package br.com.evisas.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import br.com.evisas.config.businessError.BusinessException;

public class FileUtil {

	private static final Logger logger = LogManager.getLogger(FileUtil.class);

	public static void gravarArquivo(String strPath, MultipartFile arquivo) {
        Path path = Paths.get(strPath);
        try {
        	Files.createDirectories(path.getParent());
			Files.write(path, arquivo.getBytes());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("msg.erro.gravar.documento");
		}
		
	}
}
