app.controller('editorCtrl', ['$scope', function($scope){

	$scope.contenidoHtml = "";

	$scope.crearEntrada = function(){

		$('#editor').hide();

		$('#editor-entrada').show();

		$('#editor-entrada-contenido').html($scope.contenidoHtml);

		console.log($scope.contenidoHtml);

	};
}])