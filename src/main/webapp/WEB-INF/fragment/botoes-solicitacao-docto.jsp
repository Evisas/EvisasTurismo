<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	 prefix="c" %>
<!--
	Tipos de bot�es: 
	a href (link) -> Para chamar outra url passando nenhum ou um par�metro
	submit -> chamar url passando os campos do formul�rio
	button -> chamar fun��o javascript
 -->

<c:if test="${not empty solicitacao.status}"> <!-- Est� consultando (n�o criando), ent�o tem bot�o voltar -->
	<a href="${autenticador.funcionario ? 'solicitacoes'.concat(param.COMPL_TIPO_SOLICITACAO) : 'acompanhamentoSolicitacoes'}" class="btn btn-link">Voltar</a>
</c:if>
<c:if test="${autenticador.funcionario and solicitacao.status eq 'PENDENTE'}">
	<a id="aceitar-solicitacao" acao="aceitar" href="aceitaSolicitacao${param.COMPL_TIPO_SOLICITACAO}?id=${solicitacao.id}" class="modal-confirmacao btn btn-success">Aceitar</a>
	<a id="recusar-solicitacao" acao="recusar" href="" class="modal-recusa btn btn-danger">Recusar</a>
</c:if>
<c:if test="${not autenticador.funcionario}">
	<c:choose>
		<c:when test="${empty solicitacao.status}"> <!-- Usu�rio est� criando a solicita��o -->
			<input type="submit" value="Solicitar" class="btn btn-primary" />
		</c:when>
		<c:when test="${solicitacao.status eq 'PENDENTE'}">
			<a id="cancelar-solicitacao" acao="cancelar" href="cancelamentoSolicitacao${param.COMPL_TIPO_SOLICITACAO}?id=${solicitacao.id}" class="modal-confirmacao btn btn-dark">Cancelar Solicita��o</a>
			<input type="button" id="preparar-editar" value="Editar" class="btn btn-primary" />
			<input type="button" id="cancelar-edicao" value="Cancelar Edi��o" class="btn btn-secondary" style="display: none;" />
			<input type="submit" id="editar" value="Reenviar Solicita��o" class="btn btn-primary" style="display: none;" />
		</c:when>
		<c:when test="${solicitacao.status eq 'RECUSADA'}">
			<input type="button" id="preparar-editar" value="Editar" class="btn btn-primary" />
			<input type="button" id="cancelar-edicao" value="Cancelar Edi��o" class="btn btn-secondary" style="display: none;" />
			<input type="submit" id="editar" value="Reenviar Solicita��o" class="btn btn-primary" style="display: none;" />
		</c:when>
	</c:choose>
</c:if>

<script type="text/javascript">
var ACTION_EDICAO_SOLICITACAO = "edicaoSolicitacao" + "${param.COMPL_TIPO_SOLICITACAO}";
var ACTION_RECUSA_SOLICITACAO = "recusaSolicitacao" + "${param.COMPL_TIPO_SOLICITACAO}";

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
	$("a.modal-confirmacao").click(function(){
		$("#modal-confirmacao .modal-body .acao").text($(this).attr("acao"));
		$("#modal-confirmacao .btn-primary").attr("href", $(this).attr("href"));
		$("#modal-confirmacao").modal();
		event.preventDefault();	// n�o chama link por enquanto
	});
	$("a.modal-recusa").click(function(){
		$("#modal-recusa form").attr("action", ACTION_RECUSA_SOLICITACAO);
		$("#modal-recusa").modal();
		event.preventDefault();	// n�o chama link por enquanto
	});
});
</script>
