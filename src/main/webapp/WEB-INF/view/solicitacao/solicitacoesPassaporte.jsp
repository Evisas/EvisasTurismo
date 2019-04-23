<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>
<body>
	<div id="header">
		<jsp:include page="../../fragment/admin-navegacao.jsp">
			<jsp:param name="TELA" value="SOLIC_PASS"/>
		</jsp:include>
	</div>

	<div id="content" class="container text-center">
		<h1 class="titulo"><strong>Solicitações de Passaporte</strong></h1>
		<%@include file="../../fragment/alert-messages.jspf"%>
		
		<div class="text-left solicitacoes-passaporte">
			<c:choose>
			<c:when test="${not empty solicitacoesPassaporte}">
				<table class="table table-bordered table-hover table-sm table-responsive-md solicitacoes">
					<thead class="thead-dark">
					<tr>
						<th scope="col" class="text-center">Data Solicitação</th>
						<th scope="col" class="text-center">Status</th>
						<th scope="col">Código</th>
						<th scope="col">Solicitante</th>
						<th scope="col" class="text-center">Previsão Saída</th>
						<th scope="col" class="text-center">Consultar</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="solicitacao" items="${solicitacoesPassaporte}">
						<tr>
							<td class="text-center">${solicitacao.dataSolicitacaoFormatada}</td>
							<td class="text-center">${solicitacao.status}</td>
							<td>${solicitacao.idFormatado}</td>
							<td>${solicitacao.nomeSolicitante}</td>
							<td class="text-center">${solicitacao.previsaoSaidaFormatado}</td>
							<td class="text-center"><a href="consultaSolicitacaoPassaporte?id=${solicitacao.id}">Avaliar</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p class="lead">Não há solicitações para mostrar.</p>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<div id="footer"></div>
</body>
</html>