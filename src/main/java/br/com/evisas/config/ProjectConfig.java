package br.com.evisas.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
//		messageSource.setDefaultEncoding("UTF-8"); 	// arquivos .properties devem ser lidos normalmente como "ISO-8859-1" (padrão)
		return messageSource;
	}
	
	@Bean
	@Override
	public LocalValidatorFactoryBean getValidator() {	// Busca as mensagens de validação no messages.properties ao invés do ValidationMessages.properties
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UsuarioAutenticationInterceptor())
								.excludePathPatterns("/bootstrap/**", 
													 "/css/**", 
													 "/images/**", 
													 "/js/**",
													 "/admin/**",
													 "/error");

		registry.addInterceptor(new FuncionarioAutenticationInterceptor())
								.addPathPatterns("/admin/**")
								.excludePathPatterns("/admin",
													 "/admin/login");
	}
}
