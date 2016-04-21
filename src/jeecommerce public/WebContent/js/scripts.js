// TODO
$(document).ready(function(){

	$(document).on('click', '.link-compartir', function(){
		console.log($(this).parents('.entrada'));
		$(this).parents('.entrada').find('.compartir-redes-sociales').removeClass('oculto');
	})

	$('#video-empresa').on('play', function(){
		// Reproduce un sonido cuando se reproduce el vídeo
		$('#audio-video-empresa')[0].play();
	});

});