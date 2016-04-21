angular.module('proyectoFinal', [])
	   .controller('cvCtrl', ['$scope', function($scope){

	   		$scope.cv = {};

	   		$scope.enviarCurriculum = function($event){

	   			$event.preventDefault();

	   			$('#trabaja-datos-cv').hide();
	   			$('#trabaja-cv-exito').show();

	   			return false;
	   		};

	   }])