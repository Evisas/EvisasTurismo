<!--
	IMPORTANTE: Usa classes CSS do bootstrap 
	PARÂMETROS:
	- TELA: Tela ativa (para ativar o respectivo item no menu). 
		Valores aceitos: 'HOME', 'SOLIC_PASS', 'SOLIC_VISTO', 'ACOMPANHAR_SOLIC'
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	 prefix="c" %>

<nav class="navbar navbar-expand-md navbar-dark bg-info">
  <a class="navbar-brand" href="">
  	<img src="/images/logo_sm.png" alt="logotipo" height="40px" class="rounded-lg">
  	<span class="lead text-light" style="margin: auto 10px;">${usuario.nome}</span>
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarUsuario" aria-controls="navbarUsuario" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarUsuario">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item <c:if test="${param.TELA eq 'HOME'}">active</c:if>">
        <a class="nav-link" href="${param.TELA eq 'HOME' ? '#' : 'home'}">Home</a>
      </li>
      <li class="nav-item dropdown <c:if test="${(param.TELA eq 'SOLIC_PASS') or (param.TELA eq 'SOLIC_VISTO')}">active</c:if>">
        <a class="nav-link dropdown-toggle" href="#" id="navbarUsuarioDropdownSolicitar" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Solicitar</a>
        <div class="dropdown-menu" aria-labelledby="navbarUsuarioDropdownSolicitar">
          <a class="dropdown-item <c:if test="${param.TELA eq 'SOLIC_PASS'}">active</c:if>" href="${param.TELA eq 'SOLIC_PASS' ? '#' : 'solicitacaoPassaporte'}">Passaporte</a>
          <a class="dropdown-item <c:if test="${param.TELA eq 'SOLIC_VISTO'}">active</c:if>" href="${param.TELA eq 'SOLIC_VISTO' ? '#' : 'solicitacaoVisto'}">Visto</a>
        </div>
      </li>
      <li class="nav-item <c:if test="${param.TELA eq 'ACOMPANHAR_SOLIC'}">active</c:if>">
        <a class="nav-link" href="${param.TELA eq 'ACOMPANHAR_SOLIC' ? '#' : 'acompanhamentoSolicitacoes'}">Acompanhar Solicitações</a>
      </li>
    </ul>
    <ul class="nav navbar-right">
    	<li><a class="nav-link btn btn-outline-light" href="logout">Logout</a></li>
	</ul>
  </div>
</nav>
