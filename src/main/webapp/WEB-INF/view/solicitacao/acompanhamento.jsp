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
		
		<div class="text-left solicitacoes-passaporte">
			<h2>Solicitações de Passaporte</h2>
			
			<c:choose>
			<c:when test="${not empty solicitacoesPassaporte}">
				<div class="bg-white">
				<table class="table table-bordered table-hover table-responsive-md">
					<thead class="table-info">
					<tr>
						<th scope="col">Código</th>
						<th scope="col">Solicitante</th>
						<th scope="col" class="text-center">Previsão Saída</th>
						<th scope="col" class="text-center">Data Solicitação</th>
						<th scope="col" class="text-center">Status</th>
						<th scope="col" class="text-center">Consultar</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="solicitacao" items="${solicitacoesPassaporte}">
						<tr>
							<td>${solicitacao.idFormatado}</td>
							<td>${solicitacao.nomeSolicitante}</td>
							<td class="text-center">${solicitacao.previsaoSaidaFormatado}</td>
							<td class="text-center">${solicitacao.dataSolicitacaoFormatada}</td>
							<td class="text-center">${solicitacao.status}</td>
							<td class="text-center"><a href="consultarSolicitacaoPassaporte?id=${solicitacao.id}">Consultar</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
			</c:when>
			<c:otherwise>
				<p class="lead">Não há solicitações para mostrar, clique <a href="solicitacaoPassaporte">aqui</a> para fazer uma solicitação.</p>
			</c:otherwise>
			</c:choose>
		</div>

		<div class="text-left solicitacoes-visto">
		<h2>Solicitações de Visto</h2>
		
			<c:choose>
			<c:when test="${not empty solicitacoesVisto}">
				<div class="bg-white">
				<table class="table table-bordered table-hover table-responsive-md">
					<thead class="table-info">
					<tr>
						<th scope="col">Código</th>
						<th scope="col">Solicitante</th>
						<th scope="col" class="text-center">Nascimento</th>
						<th scope="col">País Residência</th>
						<th scope="col">País Visitar</th>
						<th scope="col" class="text-center">Data Solicitação</th>
						<th scope="col" class="text-center">Status</th>
						<th scope="col" class="text-center">Consultar</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="solicitacao" items="${solicitacoesVisto}">
						<tr>
							<td>${solicitacao.idFormatado}</td>
							<td>${solicitacao.nomeSolicitante}</td>
							<td class="text-center">${solicitacao.dataNascimentoSolicitanteFormatada}</td>
							<td>${solicitacao.paisDeResidencia}</td>
							<td>${solicitacao.paisAVisitar}</td>
							<td class="text-center">${solicitacao.dataSolicitacaoFormatada}</td>
							<td class="text-center">${solicitacao.status}</td>
							<td class="text-center"><a href="consultarSolicitacaoVisto?id=${solicitacao.id}">Consultar</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
			</c:when>
			<c:otherwise>
				<p class="lead">Não há solicitações para mostrar, clique <a href="solicitacaoVisto">aqui</a> para fazer uma solicitação.</p>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<div id="footer"></div>
</body>
</html>