<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>
<body>
	<div id="header">
		<jsp:include page="../../fragment/usuario-navegacao.jsp">
			<jsp:param name="TELA" value="SOLIC_VISTO"/>
		</jsp:include>
	</div>

	<div id="content" class="container text-center">
		<h1 class="titulo"><strong>Solicitação de Visto</strong></h1>
		<%@include file="../../fragment/alert-messages.jspf"%>
		
		<form:form methodParam="post" action="solicitacaoVisto" modelAttribute="solicitacaoVisto" cssClass="form form-solicit-docto rounded-lg" enctype="multipart/form-data" novalidate="true">
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
				<spring:bind path="paisDeResidencia">
					<label for="paisDeResidencia">* País de Residência:</label><br />
					<form:input type="text" id="paisDeResidencia" path="paisDeResidencia" placeholder="Digite o país de residência do solicitante"
					minlength="3" maxlength="30" cssClass="form-control ${status.error ? 'is-invalid':''}" required="required"/>
					<div class="invalid-feedback"><form:errors path="paisDeResidencia" /></div>
				</spring:bind>
				</div>

				<div class="form-group">
				<spring:bind path="paisAVisitar">
					<label for="paisAVisitar">* País a Visitar:</label><br />
					<form:input type="text" id="paisAVisitar" path="paisAVisitar" placeholder="Digite o país a visitar"
					minlength="3" maxlength="30" cssClass="form-control ${status.error ? 'is-invalid':''}" required="required"/>
					<div class="invalid-feedback"><form:errors path="paisAVisitar" /></div>
				</spring:bind>
				</div>

				<div class="form-group">
				<spring:bind path="dataNascimentoSolicitante">
					<label for="dataNascimentoSolicitante">* Data de nascimento:</label><br />
					<form:input type="date" id="dataNascimentoSolicitante" path="dataNascimentoSolicitante" pattern="^[0-9]{4}-[0-9]{2}-[0-9]{2}$"
					cssClass="form-control data ${status.error ? 'is-invalid':''}" required="required"/>
					<small class="form-text text-muted" target="dataNascimentoSolicitante">Formato: dd/mm/aaaa</small>
					<div class="invalid-feedback"><form:errors path="dataNascimentoSolicitante" /></div>
				</spring:bind>
				</div>
				
				<div class="form-group">
				<spring:bind path="possuiPassaporte">
					<label>* Possui Passaporte?:</label><br />
					<div class="form-check form-check-inline">
						<label for="possuiPassaporteTrue" class="form-check-label">
						<form:radiobutton id="possuiPassaporteTrue" path="possuiPassaporte" value="true" 
						cssClass="form-check-input ${status.error ? 'is-invalid':''}" required="required"/>
						 Sim</label>
					</div>					
					<div class="form-check form-check-inline">
						<label for="possuiPassaporteFalse" class="form-check-label">
						<form:radiobutton id="possuiPassaporteFalse" path="possuiPassaporte" value="false" 
						cssClass="form-check-input ${status.error ? 'is-invalid':''}" required="required"/>
						 Não</label>
					</div>
					<div class="invalid-feedback"><form:errors path="possuiPassaporte" /></div>
				</spring:bind>
				</div>
				
				<div class="form-group">
				<spring:bind path="documento">
					<label for="documento">Anexar documento:</label><br />
					<form:input type="file" id="documento" path="documento" extension="pdf,doc,docx,zip,rar,jpg,jpeg,png,bmp"
					cssClass="form-control-file ${status.error ? 'is-invalid':''}" maxSize="${20*1024*1024}" />
					<small class="form-text text-muted" target="documento">Tamanho Máximo: 20Mb (Extensões válidas: .pdf, .doc, .docx, .zip, .rar, .jpg, .jpeg, .png, .bmp)</small>
					<div class="invalid-feedback"><form:errors path="documento" /></div>
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
			
			<input type="submit" value="Solicitar" class="btn btn-primary" />

		</form:form>
	</div>
	
	<div id="footer"></div>

	<script type="text/javascript">
	$(document).ready(function(){
		var dataMinimaNascimentoSolicitante = new Date(1900, 00, 01);
		var dataMaximaNascimentoSolicitante = new Date();
		
		$("input#dataNascimentoSolicitante").attr("min", formatarDateToAAAAMMDD(dataMinimaNascimentoSolicitante));
		$("input#dataNascimentoSolicitante").attr("max", formatarDateToAAAAMMDD(dataMaximaNascimentoSolicitante));
	});
	</script>
</body>
</html>