package br.com.evisas.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UsuarioAutenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("passou autenticação usuario: " + request.getRequestURI());
		String uri = request.getRequestURI();
		if(uri.endsWith("login") || 
		   uri.endsWith("cadastro") || 
		   uri.endsWith("reenvioDeSenha")) {
			return true;	// páginas que não precisa-se estar logado, pode continuar...
		}
		
		if(request.getSession().getAttribute("usuario") != null) {	// usuário está logado, pode continuar...
			return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/login");  // senão -> redireciona para a página de login
		return false;
	}
}
