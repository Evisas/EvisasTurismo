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
	<a href="admin/aceitarSolicitacao${param.COMPL_TIPO_SOLICITACAO}?id=${solicitacao.id}" class="btn btn-success">Aceitar</a>
	<a href="admin/recusarSolicitacao${param.COMPL_TIPO_SOLICITACAO}?id=${solicitacao.id}" class="btn btn-danger">Recusar</a>
</c:if>
<c:if test="${not usuario.funcionario}">
	<c:choose>
		<c:when test="${empty solicitacao.status}"> <!-- Usuário está criando a solicitação -->
			<input type="submit" value="Solicitar" class="btn btn-primary" />
		</c:when>
		<c:when test="${solicitacao.status eq 'PENDENTE'}">
			<a id="cancelar-solicitacao" acao="cancelar" href="cancelamentoSolicitacao${param.COMPL_TIPO_SOLICITACAO}?id=${solicitacao.id}" class="btn btn-dark">Cancelar Solicitação</a>
			<input type="button" id="preparar-editar" value="Editar" class="btn btn-primary" />
			<input type="button" id="cancelar-edicao" value="Cancelar Edição" class="btn btn-secondary" style="display: none;" />
			<input type="submit" id="editar" value="Reenviar Solicitação" class="btn btn-primary" style="display: none;" />
		</c:when>
		<c:when test="${solicitacao.status eq 'RECUSADA'}">
			<input type="button" id="preparar-editar" value="Editar" class="btn btn-primary" />
			<input type="button" id="cancelar-edicao" value="Cancelar Edição" class="btn btn-secondary" style="display: none;" />
			<input type="submit" id="editar" value="Reenviar Solicitação" class="btn btn-primary" style="display: none;" />
		</c:when>
	</c:choose>
</c:if>

<script type="text/javascript">
var ACTION_EDICAO_SOLICITACAO = "edicaoSolicitacao" + "${param.COMPL_TIPO_SOLICITACAO}";

$(document).ready(function(){
	$("input#preparar-editar").click(function(){
		$(".editavel").attr("disabled", false);
//		$("div.documento-baixar").hide();
		$("div.opt-docto-informar").css("display", "inline");
		$("input#documento").prop("disabled", true);
		
		$("form.form-solicit-docto").attr("action", ACTION_EDICAO_SOLICITACAO);
		$("input#cancelar-edicao, input#editar").show();
		$("input#preparar-editar,#cancelar-solicitacao").hide();
	});
	$("input#cancelar-edicao").click(function(){
		$(".editavel").attr("disabled", true);
//		$("div.documento-baixar").show();
		$("div.opt-docto-informar").hide();

		$("input#cancelar-edicao, input#editar").hide();
		$("input#preparar-editar,#cancelar-solicitacao").show();
	});
	$("#informarNovoDocumento").click(function(){
		$("input#documento").prop("disabled", !$("input#documento").is(":disabled"));
	});
	$("a#cancelar-solicitacao").click(function(){
		$("#modal-confirmacao .modal-body .acao").text($(this).attr("acao"));
		$("#modal-confirmacao a.btn-primary").attr("href", $(this).attr("href"));
		$("#modal-confirmacao").modal();
		event.preventDefault();	// não chama link por enquanto
	});
});
</script>

<%@include file="modal-confirmacao.jspf"%>