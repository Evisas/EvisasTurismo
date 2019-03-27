<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>
<body>
	<div id="header">
		<jsp:include page="../../fragment/usuario-navegacao.jsp">
			<jsp:param name="TELA" value="ACOMPANHAR_SOLIC"/>
		</jsp:include>
	</div>

	<div id="content" class="container text-center">
		<h1 class="titulo"><strong>Acompanhamento de Solicitações</strong></h1>
		<%@include file="../../fragment/alert-messages.jspf"%>
		
	</div>
	
	<div id="footer"></div>
</body>
</html>