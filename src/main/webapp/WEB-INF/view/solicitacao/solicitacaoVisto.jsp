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
		
		<form:form methodParam="post" action="solicitacaoVisto" modelAttribute="solicitacao" cssClass="form form-solicit-docto rounded-lg" enctype="multipart/form-data" novalidate="true">
			<%@include file="../../fragment/form-solicitacao-visto.jspf"%>
		</form:form>
	</div>
	
	<div id="footer"></div>
</body>
</html>