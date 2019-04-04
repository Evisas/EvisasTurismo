package br.com.evisas.config.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private FileSize fileSize;
	
	@Override
	public void initialize(FileSize fileSize) {
		this.fileSize = fileSize;
	}
	
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		if (file.isEmpty()) {
			return true;
		}
		return file.getSize() >= fileSize.min() && file.getSize() <= fileSize.max();
	}
}
