package br.com.evisas.config;

import java.util.Date;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalBeansProvider {
	
	@ModelAttribute("data_atual")
	public Date obterDataAtual(){
		return new Date();
	}
}
