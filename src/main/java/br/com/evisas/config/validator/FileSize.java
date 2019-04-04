package br.com.evisas.config.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileSizeValidator.class})
public @interface FileSize {

	String message() default "{msg.erro.validacao.filesize}";

	/**
	 * @return tamanho mínimo do arquivo (em bytes)
	 */
	long min() default 0L;

	/**
	 * @return tamanho máximo do arquivo (em bytes)
	 */
	long max() default Long.MAX_VALUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};	
}
