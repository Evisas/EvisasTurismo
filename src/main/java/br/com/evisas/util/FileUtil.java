package br.com.evisas.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
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

	public static MultipartFile buscarArquivoQualquerExtensao(String strPathBusca, String nomeArquivoSaida) {
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
        
		try {
			Path path = arquivos[0].toPath();
			String nomeArquivo = nomeArquivoSaida + "." + StringUtils.getFilenameExtension(path.toString());
			return new MockMultipartFile(nomeArquivo, Files.readAllBytes(path));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("msg.erro.ler.documento");
		}
	}
}
