<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>
<body>
	<div id="header" class="text-center">
		<img src="/images/logo.png" alt="logotipo">
	</div>
	<div id="content" class="container text-center">
		<h2>Reenvio de Senha</h2>
		<%@include file="../../fragment/alert-messages.jspf"%>

		<form:form methodParam="post" action="reenvioDeSenha" modelAttribute="usuario" class="form form-reenvio-senha rounded-lg" novalidate="true">
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
	
	<script type="text/javascript">
//	TODO: Arrumar, quando não passa na validação do jquery tbm está chamando o método
//	$(document).ready(function(){
//		// ao submeter formulário, desabilita botão "submit" para usuário não clicar várias vezes enquanto processa
//		$("form").submit(function(){
//			$(this).find("input[type='submit']").prop("disabled", true);
//		});
//	});
	</script>
</body>
</html>