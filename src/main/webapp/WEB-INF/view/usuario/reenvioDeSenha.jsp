<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>
<body>
	<div id="header" class="text-center">
		<img src="/images/logo.png" alt="logotipo">
	</div>
	<div id="content" class="container text-center">
		<h2 class="titulo">Reenvio de Senha</h2>

		<c:if test="${not empty codigoMsgErro}">
			<div class="alert alert-danger text-center"><spring:message code='${codigoMsgErro}' text="" /></div>
		</c:if>
		<c:if test="${not empty codigoMsgSucesso}">
			<div class="alert alert-success text-center"><spring:message code='${codigoMsgSucesso}' text="" /></div>
		</c:if>

		<form:form methodParam="post" action="reenviarSenha" modelAttribute="usuario" class="form form-reenvio-senha rounded-lg" novalidate="true">
			<div class="text-left">
			
				<div class="form-group">
				<spring:bind path="email">
					<label for="email">Email:</label><br />
					<form:input type="email" id="email" path="email" placeholder="Digite seu email"
					required="required"	maxlength="40" class="form-control ${status.error ? 'is-invalid':''}"/>
					<div class="invalid-feedback"><form:errors path="email" /></div>
				</spring:bind>
				</div>
				
			</div>
			
			<div class="button-content text-right">
				<input type="submit" value="Enviar Senha" class="btn btn-primary" />
				<a href="login" class="btn btn-link">Voltar</a>
			</div>
		</form:form>
	</div>
	
	<div id="footer"></div>
</body>
</html>