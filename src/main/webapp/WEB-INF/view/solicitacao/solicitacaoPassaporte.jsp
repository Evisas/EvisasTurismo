<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>
<body>
	<div id="header">
		<jsp:include page="../../fragment/usuario-navegacao.jsp">
			<jsp:param name="TELA" value="SOLIC_PASS"/>
		</jsp:include>
	</div>

	<div id="content" class="container text-center">
		<h1 class="titulo"><strong>Solicitação de Passaporte</strong></h1>
		<%@include file="../../fragment/alert-messages.jspf"%>
		
		<form:form methodParam="post" action="solicitacaoPassaporte" modelAttribute="solicitacaoPassaporte" cssClass="form form-solicit-docto rounded-lg" novalidate="true">
			<div class="text-left">
				<div class="form-group">
				<spring:bind path="nomeSolicitante">
					<label for="nomeSolicitante">* Nome do Solicitante:</label><br />
					<form:input type="text" id="nomeSolicitante" path="nomeSolicitante" placeholder="Digite o nome completo do solicitante"
					minlength="2" maxlength="80" cssClass="form-control ${status.error ? 'is-invalid':''}" required="required"/>
					<div class="invalid-feedback"><form:errors path="nomeSolicitante" /></div>
				</spring:bind>
				</div>
				
				<div class="form-group">
				<spring:bind path="cpfSolicitante">
					<label for="cpfSolicitante">* CPF do Solicitante:</label><br />
					<form:input type="text" id="cpfSolicitante" path="cpfSolicitanteFormatado" placeholder="Digite o CPF do solicitante" pattern="^[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}$"
					cssClass="cpf form-control ${status.error ? 'is-invalid':''}" required="required"/>
					<small class="form-text text-muted" target="cpfSolicitante">Formato: 123.456.789-01</small>
					<div class="invalid-feedback"><form:errors path="cpfSolicitante" /></div>
				</spring:bind>
				</div>
				
				<div class="form-group">
				<spring:bind path="rgSolicitante">
					<label for="rgSolicitante">* RG do Solicitante:</label><br />
					<form:input type="number" id="rgSolicitante" path="rgSolicitante" placeholder="Digite o RG do solicitante" pattern="^[0-9]{5,20}$"
					minlength="5" maxlength="20" cssClass="form-control ${status.error ? 'is-invalid':''}" required="required"/>
					<small class="form-text text-muted" target="rgSolicitante">Somente números</small>
					<div class="invalid-feedback"><form:errors path="rgSolicitante" /></div>
				</spring:bind>
				</div>

				<div class="form-group">
				<spring:bind path="previsaoSaida">
					<label for="previsaoSaida">* Previsão de saída do Brasil:</label><br />
					<form:input type="date" id="previsaoSaida" path="previsaoSaida" pattern="^[0-9]{4}-[0-9]{2}-[0-9]{2}$"
					cssClass="form-control data ${status.error ? 'is-invalid':''}" required="required"/>
					<small class="form-text text-muted" target="previsaoSaida">Formato: dd/mm/aaaa</small>
					<div class="invalid-feedback"><form:errors path="previsaoSaida" /></div>
				</spring:bind>
				</div>
				
				<div class="form-group">
				<spring:bind path="observacao">
					<label for="observacao">Observação:</label><br />
					<form:textarea id="observacao" path="observacao" 
					 maxlength="100" cssClass="form-control ${status.error ? 'is-invalid':''}"/>
					<small class="form-text text-muted" target="observacao">Máximo de 100 caracteres.</small>
					<div class="invalid-feedback"><form:errors path="observacao" /></div>
				</spring:bind>
				</div>
				
			</div>
			
			<input type="submit" value="Entrar" class="btn btn-primary" />

		</form:form>
	</div>
	
	<div id="footer"></div>
	
	<script type="text/javascript">
	$(document).ready(function(){
		var dataMinimaPrevisaoSaida = new Date();
		var dataMaximaPrevisaoSaida = new Date();
		dataMaximaPrevisaoSaida.setFullYear(dataMaximaPrevisaoSaida.getFullYear() + 3);
		
		$("input#previsaoSaida").attr("min", formatarDateToAAAAMMDD(dataMinimaPrevisaoSaida));
		$("input#previsaoSaida").attr("max", formatarDateToAAAAMMDD(dataMaximaPrevisaoSaida));
	});
	</script>
</body>
</html>