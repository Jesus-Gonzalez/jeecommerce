angular.module('jeecommerce')
       .controller('pedidoController', function($scope, $http){

         $scope.anadirDireccion = function(){

            var nombre = $('#input-nombre').val(),
                direccion = $('#input-direccion').val(),
                localidad = $('#input-localidad').val(),
                codpostal = $('#input-codigo-postal').val(),
                provincia = $('#input-provincia').val(),
                telefono = $('#input-telefono').val();

                 $http({
                   method: 'POST',
                   url: ABS_PATH + '/direccion/crea',
                   data: {
                     nombre: nombre,
                     direccion = direccion,
                     localidad = localidad,
                     codpostal = codpostal,
                     provincia = provincia,
                     telefono = telefono
                   }
                 }).then(function successCallback(data, status, headers, config) {
                      console.log(data);
                     },
                     function errorCallback(data, status, headers, config) {
                       console.log(data);
                     });

       });
