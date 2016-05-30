angular.module('jeecommerce')
      .controller('editarDireccionController', function($scope, $http){

        $scope.editarDireccion = function editarDireccion(did) {

          $http({
            method: 'POST',
            url: ABS_PATH + '/editar-direccion',
            data: {
              nombre: $scope.nombre,
              direccion: $scope.direccion,
              localidad: $scope.localidad,
              codpostal: $scope.codpostal,
              telefono: $scope.telefono
            }
          }).then(function editDidSuccessCallback(response){

            console.log('succcess');
            console.log(response);

          }, function delDidSuccessCallback(response){

            console.log('error');
            console.log(response);

          });

        };

      });
