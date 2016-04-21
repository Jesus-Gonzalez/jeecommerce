$(document).ready(function(){

	$('#form-registro').on('submit', function(v){

		v.preventDefault();

		$(this).hide();

		$('#portada-registro-success').removeClass('oculto');

		return false;

	});

});