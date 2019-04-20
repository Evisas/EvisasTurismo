<!--
	IMPORTANTE: Usa classes CSS do bootstrap 
	PARÂMETROS:
	- TELA: Tela ativa (para ativar o respectivo item no menu). 
		Valores aceitos: 'HOME', 'SOLIC_PASS', 'SOLIC_VISTO'
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	 prefix="c" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
  <a class="navbar-brand" href="">
  	<img src="/images/logo_sm.png" alt="logotipo" height="40px" class="rounded-lg">
  	<span class="lead text-light" style="margin: auto 10px;">${autenticador.matricula} - ${autenticador.nome}</span>
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarAdmin" aria-controls="navbarAdmin" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarAdmin">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item <c:if test="${param.TELA eq 'HOME'}">active</c:if>">
        <a class="nav-link" href="${param.TELA eq 'HOME' ? '#' : 'home'}">Home</a>
      </li>
      <li class="nav-item <c:if test="${param.TELA eq 'SOLIC_PASS'}">active</c:if>">
        <a class="nav-link" href="solicitacoesPassaporte">Solicitações de Passaporte</a>
      </li>
      <li class="nav-item <c:if test="${param.TELA eq 'SOLIC_VISTO'}">active</c:if>">
        <a class="nav-link" href="solicitacoesVisto">Solicitações de Visto</a>
      </li>
    </ul>
    <ul class="nav navbar-right">
    	<li><a class="nav-link btn btn-outline-light" href="logout">Logout</a></li>
	</ul>
  </div>
</nav>
