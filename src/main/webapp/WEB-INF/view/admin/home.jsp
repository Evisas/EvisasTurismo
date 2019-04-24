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
		
		<div class="row">
			<div class="col-sm">
				<div class="card h-100 p-3 shadow">
					<img src="/images/passaportes.jpg" class="card-img-top img-h-250" alt="imagem passaportes">
					<div class="card-body">
						<h5 class="card-title">Análise de Passaportes</h5>
						<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						<a href="solicitacoesPassaporte" class="btn btn-primary stretched-link">Analisar</a>
					</div>
				</div>
  			</div>
			<div class="col-sm">
				<div class="card h-100 p-3 shadow">
					<img src="/images/analisar-vistos.jpg" class="card-img-top img-h-250" alt="imagem vistos">
					<div class="card-body">
						<h5 class="card-title">Análise de Vistos</h5>
						<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						<a href="solicitacoesVisto" class="btn btn-primary stretched-link">Analisar</a>
					</div>
	  			</div>
  			</div>
		</div>
		
	</div>
	
	<div id="footer"></div>
</body>
</html>