<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>
<body>
	<div id="header">
		<jsp:include page="../../fragment/admin-navegacao.jsp">
			<jsp:param name="TELA" value="HOME"/>
		</jsp:include>
	</div>

	<div id="content" class="container text-center">
		<h1 class="titulo"><strong>EVISAS - Acesso de funcionários</strong></h1>
		<%@include file="../../fragment/alert-messages.jspf"%>
		
	</div>
	
	<div id="footer"></div>
</body>
</html>