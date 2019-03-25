<html lang="pt-br">
	<%@include file="../fragment/header.jspf"%>
 
	<body>
		<div class="container">
			<h1>Ocorreu um erro!</h1>
			<p class="lead">Error code: ${status}</p>
			<p class="lead">
				<c:choose>
					<c:when test="${status eq 400}"><spring:message code='msg.erro.400' /></c:when>
					<c:when test="${status eq 401}"><spring:message code='msg.erro.401' /></c:when>
					<c:when test="${status eq 404}"><spring:message code='msg.erro.404' /></c:when>
					<c:when test="${status eq 500}"><spring:message code='msg.erro.500' /></c:when>
					<c:otherwise>					<spring:message code='msg.erro.generico' /></c:otherwise>
				</c:choose>
			</p>
			<p class="lead">Código do erro: <fmt:formatDate value="${data_atual}" pattern="yyyyMMddhhmmss" /></p>
		</div>
	</body>
</html>