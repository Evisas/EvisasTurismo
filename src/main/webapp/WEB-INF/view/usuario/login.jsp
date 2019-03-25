<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>

<body>
	<div id="header" class="text-center">
		<img src="/images/logo.png" alt="logotipo">
		<div class="messages d-flex align-items-center">
			<c:if test="${not empty codigoMsgErro}">
				<div class="alert alert-danger text-center"><spring:message code='${codigoMsgErro}' text="" /></div>
			</c:if>
			<c:if test="${not empty codigoMsgSucesso}">
				<div class="alert alert-success text-center"><spring:message code='${codigoMsgSucesso}' text="" /></div>
			</c:if>
		</div>
	</div>
	<div id="content" class="container text-center">
		
		<form:form methodParam="post" action="logar" modelAttribute="usuario" cssClass="form form-login rounded-lg" novalidate="true">
			<div class="text-left">
				<div class="form-group">
				<spring:bind path="email">
					<label for="email">Email:</label><br />
					<form:input type="email" id="email" path="email" placeholder="Digite seu email"
					maxlength="40" cssClass="form-control ${status.error ? 'is-invalid':''}" required="required"/>
					<div class="invalid-feedback"><form:errors path="email" /></div>
				</spring:bind>
				</div>
				
				<div class="form-group">
				<spring:bind path="senha">
					<label for="senha">Senha:</label><br />
					<form:input type="password" id="senha" path="senha" placeholder="Digite sua Senha"
					minlength="4" maxlength="15" cssClass="form-control ${status.error ? 'is-invalid':''}" required="required"/>
					<div class="invalid-feedback"><form:errors path="senha" /></div>
					<a href="reenvioDeSenha">Esqueceu sua senha?</a>
				</spring:bind>
				</div>
				
			</div>
			
			<input type="submit" value="Entrar" class="btn btn-primary" />

		</form:form>
		<br />

		<span class="lead">
			Ainda não tem cadastro? <br />
			<a href="cadastro">Cadastre-se Já!</a>
		</span><br />

	</div>
	<div id="footer"></div>
</body>
</html>