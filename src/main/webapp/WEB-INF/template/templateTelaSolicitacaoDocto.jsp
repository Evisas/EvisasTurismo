<!-- 
	PARÂMETROS:
	- TITULO: Título da página
	- TIPO_SOLICITACAO: Tipo da solicitação ('PASSAPORTE' ou 'VISTO')
	- FORM_ACTION: Action do formulário
	- HAS_FILE: true or false (default)
-->
<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../fragment/header.jspf"%>

<c:set var="ESTAH_CRIANDO_SOLICITACAO" value="${empty solicitacao.status}" />
<c:set var="IS_MODO_CONSULTA" value="${not ESTAH_CRIANDO_SOLICITACAO and not ehEdicao}" />

<body>
	<div id="header">
		<jsp:include page="${autenticador.funcionario ? '../fragment/admin-navegacao.jsp' : '../fragment/usuario-navegacao.jsp'}">
			<jsp:param name="TELA" value="${param.TIPO_SOLICITACAO eq 'PASSAPORTE' ? 'SOLIC_PASS' : 'SOLIC_VISTO'}"/>
		</jsp:include>
	</div>

	<div id="content" class="container text-center">
		<h1 class="titulo"><strong>${param.TITULO}</strong></h1>
		<%@include file="../fragment/alert-messages.jspf"%>
		<form:form methodParam="post" 
				   action="${param.FORM_ACTION}" 
				   modelAttribute="solicitacao" 
				   cssClass="form form-solicit-docto rounded-lg"
				   enctype="${param.HAS_FILE ? 'multipart/form-data' : 'application/x-www-form-urlencoded'}" 
				   novalidate="true">

			<form:hidden id="idSolicitacao" path="id" />

			<c:if test="${not ESTAH_CRIANDO_SOLICITACAO}">
				<div class="badge badge-secondary text-wrap">Data da Solicitação: ${solicitacao.dataSolicitacaoFormatada}</div><br/>
				<strong>Status: ${solicitacao.status}</strong>
				<br />
				<c:if test="${not empty solicitacao.motivoRecusa}">
					<br />
					<div class="alert alert-danger text-left" role="alert">
						<h5 class="alert-heading">Motivo da Recusa:</h5>
						<p>${solicitacao.motivoRecusa}</p>
					</div>
				</c:if>
				<hr />
			</c:if>
			
			<div class="text-left">

				<c:choose>
				<c:when test="${param.TIPO_SOLICITACAO eq 'PASSAPORTE'}"><%@include file="../fragment/form-solicitacao-passaporte.jspf"%></c:when>
				<c:when test="${param.TIPO_SOLICITACAO eq 'VISTO'}"><%@include file="../fragment/form-solicitacao-visto.jspf"%></c:when>
				</c:choose>
			</div>

			<jsp:include page="../fragment/botoes-solicitacao-docto.jsp">
				<jsp:param name="COMPL_TIPO_SOLICITACAO" value="${param.TIPO_SOLICITACAO eq 'PASSAPORTE' ? 'Passaporte' : 'Visto'}"/>
			</jsp:include>
		</form:form>
	</div>
	
	<div id="footer"></div>

	<%@include file="../fragment/modal-confirmacao.jspf"%>
	<%@include file="../fragment/modal-recusa.jspf"%>
</body>
</html>