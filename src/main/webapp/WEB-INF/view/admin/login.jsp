<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>

<body>
	<div id="header" class="text-center">
		<img src="/images/logo.png" alt="logotipo">
	</div>
	<div id="content" class="container text-center">
		<h2>Acesso de Funcionários</h2>
		<%@include file="../../fragment/alert-messages.jspf"%>
		
		<form:form methodParam="post" action="login" modelAttribute="funcionario" cssClass="form form-login-admin rounded-lg bg-secondary text-white" novalidate="true">
			<div class="text-left">
				<div class="form-group">
				<spring:bind path="matricula">
					<label for="matricula">Matrícula:</label><br />
					<form:input type="number" id="matricula" path="matricula" placeholder="Digite sua matrícula"
					minlength="4" maxlength="4" cssClass="form-control matricula ${status.error ? 'is-invalid':''}" required="required"/>
					<div class="invalid-feedback"><form:errors path="matricula" /></div>
				</spring:bind>
				</div>
				
				<div class="form-group">
				<spring:bind path="senha">
					<label for="senha">Senha:</label><br />
					<form:input type="password" id="senha" path="senha" placeholder="Digite sua Senha"
					minlength="4" maxlength="15" cssClass="form-control ${status.error ? 'is-invalid':''}" required="required"/>
					<div class="invalid-feedback"><form:errors path="senha" /></div>
				</spring:bind>
				</div>
				
			</div>
			
			<input type="submit" value="Entrar" class="btn btn-primary" />

		</form:form>

	</div>
	<div id="footer"></div>
</body>
</html>