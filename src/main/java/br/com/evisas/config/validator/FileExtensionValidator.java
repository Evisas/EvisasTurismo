package br.com.evisas.config.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.evisas.config.validator.FileExtension.Type;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, MultipartFile> {

	private FileExtension fileExtension;
	
	@Override
	public void initialize(FileExtension fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		if (file == null || file.isEmpty() || fileExtension.extensions().isEmpty()) {
			return true;
		}
		String extensaoArquivo = StringUtils.getFilenameExtension(file.getOriginalFilename());

		if (fileExtension.type() == Type.ONLY) {
			return extensaoArquivoEstahNaLista(extensaoArquivo, fileExtension.extensions());
		} else { // Type == Type.EXCEPT
			return !extensaoArquivoEstahNaLista(extensaoArquivo, fileExtension.extensions());
		}
	}

	private boolean extensaoArquivoEstahNaLista(String extensaoArquivo, String extensoes) {
		List<String> extensoesList = Arrays.asList(extensoes.replaceAll(" ", "").split("[|,]"));
		for (String extensao : extensoesList) {
			if (extensao.equalsIgnoreCase(extensaoArquivo)) {
				return true;
			}
		}
		return false;
	}
}
