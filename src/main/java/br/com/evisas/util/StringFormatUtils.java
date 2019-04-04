package br.com.evisas.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import org.springframework.util.StringUtils;

public class StringFormatUtils {

	private final static String FORMATO_CPF = "###.###.###-##";
	private final static String FORMATO_TELEFONE_8_DIG = "(##) ####-####";
	private final static String FORMATO_TELEFONE_9_DIG = "(##) #####-####";
	
	public static String obterSomenteNumeros(String str) {
		if (str == null) {
			return null;
		}
		return str.replaceAll("\\D", "");
	}

	public static String formatarCpf(String cpf) {
		try {
			MaskFormatter cpfMask = new MaskFormatter(FORMATO_CPF);
			cpfMask.setValueContainsLiteralCharacters(false);
			return StringUtils.isEmpty(cpf) ? null : cpfMask.valueToString(cpf);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Erro ao formatar o número de cpf: [" + cpf + "]", e);
		}
	}

	public static String formatarTelefone8ou9digitos(String telefone) {
        try {
        	if (telefone == null) {
        		return null;
        	} else if (telefone.length() == 9) {
    			return new MaskFormatter(FORMATO_TELEFONE_9_DIG).valueToString(telefone);
        	} else if (telefone.length() == 8) {
        		return new MaskFormatter(FORMATO_TELEFONE_8_DIG).valueToString(telefone);
        	} else {
        		return telefone;
        	}
		} catch (ParseException e) {
			throw new IllegalArgumentException("Erro ao formatar o número de telefone: [" + telefone + "]", e);
		}
	}
	
	public static String preencherAEsquerdaComZeros(long value, int size) {
		return preencherAEsquerdaComZeros(String.valueOf(value), size);
	}

	public static String preencherAEsquerdaComZeros(String value, int size) {
		return preencherAEsquerda(value, "0", size);
	}

	public static String preencherAEsquerda(String value, String filler, int size) {
		return String.format("%"+filler+size+"d", Integer.parseInt(value));
	}

}
