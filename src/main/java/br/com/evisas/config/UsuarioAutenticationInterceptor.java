package br.com.evisas.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.evisas.entity.Autenticador;
import br.com.evisas.util.Const;

public class UsuarioAutenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();
		if(uri.endsWith("login") || 
		   uri.endsWith("cadastro") || 
		   uri.endsWith("reenvioDeSenha")) {
			return true;	// páginas que não precisa-se estar logado, pode continuar...
		}
		
		Autenticador autenticador = (Autenticador) request.getSession().getAttribute(Const.AUTENTICADOR);
		if(autenticador != null && !autenticador.isFuncionario()) {	// usuário está logado, pode continuar...
			return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/login");  // senão -> redireciona para a página de login
		return false;
	}
}
