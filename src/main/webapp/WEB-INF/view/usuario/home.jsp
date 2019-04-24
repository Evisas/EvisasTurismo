<!DOCTYPE html>

<html lang="pt-br">
<%@include file="../../fragment/header.jspf"%>
<body>
	<div id="header">
		<jsp:include page="../../fragment/usuario-navegacao.jsp">
			<jsp:param name="TELA" value="HOME"/>
		</jsp:include>
	</div>

	<div id="content" class="container text-center">
		<h1 class="titulo"><strong>EVISAS</strong></h1>
		<%@include file="../../fragment/alert-messages.jspf"%>
		
		<div class="row">
			<div class="col-sm">
				<div class="card h-100 p-3 shadow">
					<img src="/images/passaporte.jpg" class="card-img-top img-h-200" alt="imagem passaporte">
					<div class="card-body">
						<h5 class="card-title">Solicite seu passaporte</h5>
						<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						<a href="solicitacaoPassaporte" class="btn btn-primary stretched-link">Solicitar</a>
					</div>
				</div>
  			</div>
			<div class="col-sm">
				<div class="card h-100 p-3 shadow">
					<img src="/images/visto2.jpg" class="card-img-top img-h-200" alt="imagem visto">
					<div class="card-body">
						<h5 class="card-title">Solicite seu visto</h5>
						<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						<a href="solicitacaoVisto" class="btn btn-primary stretched-link">Solicitar</a>
					</div>
	  			</div>
  			</div>
			<div class="col-sm">
				<div class="card h-100 p-3 shadow">
					<img src="/images/pesquisa.png" class="card-img-top img-h-200" alt="imagem de lupa/busca">
					<div class="card-body">
						<h5 class="card-title">Acompanhe suas solicitações</h5>
						<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						<a href="acompanhamentoSolicitacoes" class="btn btn-primary stretched-link">Acompanhar</a>
					</div>
	  			</div>
  			</div>
		</div>
		
	</div>
	
	<div id="footer"></div>
</body>
</html>