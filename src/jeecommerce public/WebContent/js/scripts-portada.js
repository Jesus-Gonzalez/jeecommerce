$(document).ready(function(){

	$('#form-registro').on('submit', function(v){

		v.preventDefault();

		$(this).hide();

		$('#portada-registro-success').removeClass('oculto');

		return false;

	});

	$(window).scroll(function(event) {
	  $(".caja").each(function(i, el) {
	    var el = $(el);
	    if (el.visible(true)) {
	      el.addClass("mostrar-caja-slide-from-right");
	    }
	  });

		$(".portada-caja").each(function(i, el) {
	    var el = $(el);
	    if (el.visible(true)) {
	      el.addClass("mostrar-caja-slide-from-bottom");
	    }
	  });

		$("#btn-catalogo").each(function(i, el) {
	    var el = $(el);
	    if (el.visible(true)) {
	      el.addClass("mostrar-opacity");
	    }
	  });
	});

});
