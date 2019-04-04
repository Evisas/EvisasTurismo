package br.com.evisas.config.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileExtensionValidator.class})
public @interface FileExtension {

	public enum Type { ONLY, EXCEPT }
	
	String message() default "{msg.erro.validacao.fileExtension}";

	/**
	 * @return extensões permitidas (ou não) separadas por vírgula (,) ou barra vertical (|)
	 */
	String extensions();

	/**
	 * @return Tipo da operação:
	 * <ul>
	 * 		<li>ONLY: Arquivo deverá ser de uma das extensões listadas</li>
	 * 		<li>EXCEPT: Arquivo não poderá ser de nenhuma das extensões listadas</li>
	 * </ul>
	 */
	Type type() default Type.ONLY;
	
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
