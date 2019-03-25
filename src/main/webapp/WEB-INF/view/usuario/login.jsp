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
		
		<spring:hasBindErrors name="usuario">
			<c:set var="voltouDoServidorComErrosValidacao">${not empty errors}</c:set>
		</spring:hasBindErrors>
		
		<form:form methodParam="post" action="logar" modelAttribute="usuario" cssClass="form form-login rounded-lg ${voltouDoServidorComErrosValidacao ? 'was-validated':''}" novalidate="true">
			<div class="text-left">
				<div class="form-group">
					<label for="email">Email:</label><br />
					<form:input type="email" id="email" path="email" placeholder="Digite seu email"
					maxlength="40" cssClass="form-control" required="required"/>
					<div class="invalid-feedback"><form:errors path="email" /></div>
				</div>
				
				<div class="form-group">
					<label for="senha">Senha:</label><br />
					<form:input type="password" id="senha" path="senha" placeholder="Digite sua Senha"
					minlength="4" maxlength="15" cssClass="form-control" required="required"/>
					<div class="invalid-feedback"><form:errors path="senha" /></div>
					<a href="reenvioDeSenha">Esqueceu sua senha?</a>
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