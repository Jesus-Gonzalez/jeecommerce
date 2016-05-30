angular.module('jeecommerce')
        .controller('productoController', ['$scope', '$http', 'productosService', function($scope, $http, $productos) {

          $scope.anadirAlCarro = $productos.anadirAlCarro;

        }])
        .controller('comentariosController', ['$scope', '$http', function($scope, $http){

        }]);
