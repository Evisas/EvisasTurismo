
var preencherAEsquerda = function(str, tamanho, pad) {
	var strRetorno = str.toString();
	if (!pad) { pad = '0'; } // se pad for undefined ou nulo, será '0'
	
	while (strRetorno.length < tamanho) {
		strRetorno = pad + strRetorno;
	}
	return strRetorno;
};

/**
 * Converte tipo date para string no formato: aaaa-mm-dd
 */
var formatarDateToAAAAMMDD = function(date) {
	return date.getFullYear() + "-" + 
		   preencherAEsquerda(date.getMonth()+1, 2, '0') + "-" + 
		   preencherAEsquerda(date.getDate(), 2, '0');
};

/**
 * Converte string data no formato 'aaaa-mm-dd' para 'dd/mm/aaaa'
 */ 
var converterStrDataAAAAMMDDtoDDMMAAAA = function(strData) {
	var arrData = strData.split("-");
	return arrData[2] + "/" + arrData[1] + "/" + arrData[0];
};

/**
 * Formata data no formato dd/mm/aaaa antes de inserí-la na mensagem
 */
var formatarMsgValidacaoComData = function(str) {
	return function(paramData) {
		return jQuery.validator.format(str, converterStrDataAAAAMMDDtoDDMMAAAA(paramData));
	};
};

var aplicarMascaraTelefone8ou9digitos = function(seletor) {
	var maskBehavior = function (val) {
		return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
	}
	var options = { 
		onKeyPress: function(val, e, field, options) {
			field.mask(maskBehavior.apply({}, arguments), options);
		}
	};
	
	$(seletor).mask(maskBehavior, options);
}
