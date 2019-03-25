<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>
<body>
	<div id="header" class="text-center">
		<img src="/images/logo.png" alt="logotipo">
	</div>
	<div id="content" class="container text-center">
		<h2 class="titulo">Cadastro</h2>

		<c:if test="${not empty codigoMsgErro}">
			<div class="alert alert-danger text-center"><spring:message code='${codigoMsgErro}' text="" /></div>
		</c:if>
		<c:if test="${not empty codigoMsgSucesso}">
			<div class="alert alert-success text-center"><spring:message code='${codigoMsgSucesso}' text="" /></div>
		</c:if>

		<spring:hasBindErrors name="usuario">
			<c:set var="voltouDoServidorComErrosValidacao">${not empty errors}</c:set>
		</spring:hasBindErrors>

		<form:form methodParam="post" action="cadastrar" modelAttribute="usuario" class="form form-cadastro rounded-lg" novalidate="true">
			<div class="text-left">
			
				<div class="form-group">
				<spring:bind path="nome">
					<label for="nome">* Nome:</label>
					<form:input type="text" id="nome" path="nome" placeholder="Digite seu Nome"
					required="required" minlength="2" maxlength="80" cssClass="form-control ${status.error ? 'is-invalid':''}"/>
					<div class="invalid-feedback"><form:errors path="nome" /></div>
				</spring:bind>
				</div>
			
				<div class="form-group">
				<spring:bind path="email">
					<label for="email">* Email:</label><br />
					<form:input type="email" id="email" path="email" placeholder="Digite seu email"
					required="required"	maxlength="40" cssClass="form-control ${status.error ? 'is-invalid':''}"/>
					<div class="invalid-feedback"><form:errors path="email" /></div>
				</spring:bind>
				</div>
				
				<div class="form-group">
				<spring:bind path="telefone">
					<label for="telefone">Telefone:</label><br />
					<form:input type="text" id="telefone" path="telefoneFormatado" placeholder="Digite seu telefone" pattern="^\([0-9]{2}\) [0-9]{4,5}-[0-9]{4}$"
					maxlength="15" cssClass="form-control telefone ${status.error ? 'is-invalid':''}"/>
					<small class="form-text text-muted" target="telefone">Formato: (XX) 99999-9999</small>
					<div class="invalid-feedback"><form:errors path="telefone" /></div>
				</spring:bind>
				</div>
				
				<div class="form-group">
				<spring:bind path="senha">
					<label for="senha">* Senha:</label><br />
					<form:input type="password" id="senha" path="senha" placeholder="Digite sua Senha"
					required="required" minlength="4" maxlength="15" cssClass="form-control ${status.error ? 'is-invalid':''}"/>
					<small class="form-text text-muted" target="senha">A senha deve ter entre 4-15 caracteres. Pode ter letras, números ou caracteres especiais.</small>
					<div class="invalid-feedback"><form:errors path="senha" /></div>
				</spring:bind>
				</div>
				
				<div class="form-group">
					<label for="senhaConfirmacao">* Repetir Senha:</label><br />
					<input type="password" id="senhaConfirmacao" placeholder="Confirme a Senha"
					required="required"	minlength="4" maxlength="15" equalTo="#senha" class="form-control"/>
				</div>
			</div>
			<div class="button-content text-right">
			<a href="login" class="btn btn-link">Voltar</a>
			<input type="submit" value="Cadastrar" class="btn btn-primary" />
			</div>
				<div class="text-left">
			<small class="form-text text-muted">(*): preenchimento obrigatório.</small>
			</div>
		</form:form>
	</div>
	<div id="footer"></div>
</body>
</html>