package br.com.evisas.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.evisas.entity.Autenticador;
import br.com.evisas.util.Const;

public class FuncionarioAutenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();
		if(uri.endsWith("login")) {
			return true;	// páginas que não precisa-se estar logado
		}
		
		Autenticador autenticador = (Autenticador) request.getSession().getAttribute(Const.AUTENTICADOR);
		if(autenticador != null && autenticador.isFuncionario()) {	// funcionário está logado
			return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/login");
		return false;
	}
}
