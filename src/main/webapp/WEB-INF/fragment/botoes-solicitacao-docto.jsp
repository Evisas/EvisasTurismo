<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	 prefix="c" %>
<!--
	Tipos de botões: 
	a href (link) -> Para chamar outra url passando nenhum ou um parâmetro
	submit -> chamar url passando os campos do formulário
	button -> chamar função javascript
 -->

<c:if test="${not empty solicitacao.status}"> <!-- Está consultando (não criando), então tem botão voltar -->
	<a href="${usuario.funcionario ? '' : 'acompanhamentoSolicitacoes'}" class="btn btn-link">Voltar</a>
</c:if>
<c:if test="${usuario.funcionario and solicitacao.status eq 'PENDENTE'}">
	<a href="admin/aceitarSolicitacao${TIPO_SOLICITACAO}?id=${solicitacao.id}" class="btn btn-success">Aceitar</a>
	<a href="admin/recusarSolicitacao${TIPO_SOLICITACAO}?id=${solicitacao.id}" class="btn btn-danger">Recusar</a>
</c:if>
<c:if test="${not usuario.funcionario}">
	<c:choose>
		<c:when test="${empty solicitacao.status}"> <!-- Usuário está criando a solicitação -->
			<input type="submit" value="Solicitar" class="btn btn-primary" />
		</c:when>
		<c:when test="${solicitacao.status eq 'PENDENTE'}">
			<a href="cancelarSolicitacao${TIPO_SOLICITACAO}?id=${solicitacao.id}" class="btn btn-dark">Cancelar</a>
			<input type="button" value="Editar" class="btn btn-primary" />
			<input type="button" value="Cancelar Edição" class="btn btn-secondary" style="display: none;" />
			<input type="submit" value="Reenviar" class="btn btn-primary" style="display: none;" />
		</c:when>
		<c:when test="${solicitacao.status eq 'RECUSADA'}">
			<input type="button" value="Editar" class="btn btn-primary" />
			<input type="button" value="Cancelar Edição" class="btn btn-secondary" style="display: none;" />
			<input type="submit" value="Reenviar" class="btn btn-primary" style="display: none;" />
		</c:when>
	</c:choose>
</c:if>
