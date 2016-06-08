angular.module('jeecommerce')
      .controller('editarDireccionController', function($scope, $http){

        $scope.editarDireccion = function editarDireccion(did) {

          var nombre = $('#input-nombre').val(),
              direccion = $('#input-direccion').val(),
              localidad = $('#input-localidad').val(),
              codpostal = $('#input-codigo-postal').val(),
              telefono = $('#input-telefono').val();

          $http({
            method: 'POST',
            url: ABS_PATH + '/editar-direccion',
            data: {
              did: did,
              // did,
              nombre: nombre,
              // ES2015 in comments
              // nombre,
              direccion: direccion,
              // direccion,
              localidad: localidad,
              // localidad,
              codpostal: codpostal,
              // codpostal,
              telefono: telefono
              // telefono
            }
          }).then(function editDidSuccessCallback(response){

            console.log('succcess');
            console.log(response);

            alertify.success("Dirección modificada con éxito.");
            window.location.href = ABS_PATH + "/mi-cuenta.html";

          }, function delDidSuccessCallback(response){

            console.log('error');
            console.log(response);

            alertify.error("Algún dato incorrecto. Revise los campos.");

          });

        };

      });
