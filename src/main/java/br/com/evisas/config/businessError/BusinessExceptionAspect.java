package br.com.evisas.config.businessError;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.com.evisas.util.Const;

/**
 * Classe que captura e trata os 'BusinessException' lançados dentro 
 * de um método anotado com HandleBusinessError.
 * 
 * EXPLICAÇÃO: Comportamento não foi feito com '@ExceptionHandler', pois o mesmo apaga o request
 * e, com isso, os atributos da model. Então formulários voltam para o usuário sem estar preenchidos.
 */
@Aspect
@Component
public class BusinessExceptionAspect {

	private static final Logger logger = LogManager.getLogger(BusinessExceptionAspect.class);

	@Pointcut("@annotation(br.com.evisas.config.businessError.HandleBusinessError)")
	public void metodosAnotadosComHandleBusinessError() {}
	
	@Around("metodosAnotadosComHandleBusinessError()")
	public Object aroundMetodosHandleBusinessError(ProceedingJoinPoint call) throws Throwable {
		try {
			return call.proceed();
			
		} catch (BusinessException ex) {
			inserirMensagemErroRetorno(call, ex);

			String paginaRetorno = obterErrorPageFromAnnotation(call);
			return isRetornoString(call) ? paginaRetorno : new ModelAndView(paginaRetorno);
		}
	}

	private void inserirMensagemErroRetorno(ProceedingJoinPoint call, BusinessException ex) {
		
		if (isErroVinculadoAUmCampoFormulario(ex)) { 
			// Se é erro em um campo, tenta vincular mensagem ao campo com 'BindingResult' do método do Controller
			BindingResult bindingResult = obterObjetoPelaClasse(call.getArgs(), BindingResult.class);
			
			if (bindingResult != null) {
				bindingResult.rejectValue(ex.getCampoErro(), ex.getMessage());
			} else { 
				// Se não tem 'BindingResult' no método (deveria ter), apresentar como erro genérico e loga o erro (para alguém colocar o BindingResult)
				gravarErroGenericoRequest(ex.getMessage());
				logger.error("Método do controller ['"+ call.getSignature() +"'] não tem o parâmetro 'BindingResult', então erro do campo está sendo lançado como erro genérico na tela.");
			}
		} else { 
			gravarErroGenericoRequest(ex.getMessage());
		}
	}

	private boolean isErroVinculadoAUmCampoFormulario(BusinessException ex) {
		return !StringUtils.isEmpty(ex.getCampoErro());
	}

	private void gravarErroGenericoRequest(String mensagemErro) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		request.setAttribute(Const.STR_COD_MSG_ERRO, mensagemErro);
	}

	@SuppressWarnings("unchecked")
	private <A extends Object> A obterObjetoPelaClasse(Object[] objs, Class<A> classe) {
		for (Object obj : objs) {
			if (classe.isInstance(obj))
				return (A) obj;
		}
		return null;
	}

	private String obterErrorPageFromAnnotation(ProceedingJoinPoint call) {
		MethodSignature methodSignature = (MethodSignature) call.getSignature();
		HandleBusinessError handle = AnnotationUtils.findAnnotation(methodSignature.getMethod(), HandleBusinessError.class);
		return handle.errorPage();
	}

	private boolean isRetornoString(ProceedingJoinPoint call) {
		MethodSignature methodSignature = (MethodSignature) call.getSignature();
		return methodSignature.getReturnType().equals(String.class);
	}
}
