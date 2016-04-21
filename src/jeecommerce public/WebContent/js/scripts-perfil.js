$(document).ready(function(){

	$('#perfil-estado').on('click', function(){

		$(this).addClass('oculto');

		var $txtEstado = $('#perfil-txt-estado');
		$txtEstado.prop("value", "");
		$txtEstado.prop("placeholder", $(this).text());
		$txtEstado.removeClass('oculto');
		$txtEstado.focus();

	});

	$('#form-estado').on('submit', function(v){

		v.preventDefault();

		var $estado = $('#perfil-estado'),
			$txtEstado = $('#perfil-txt-estado'),
			nuevoEstado = $txtEstado.val();

		if (!nuevoEstado)
			return false;

		$estado.text(nuevoEstado);
		$estado.append(' <i class="fa fa-pencil-square-o hidden"></i>');

		$txtEstado.addClass('oculto');
		$estado.removeClass('oculto');


		return false;

	});

});