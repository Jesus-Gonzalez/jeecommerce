angular.module('jeecommerce.private')
       .controller('loginController', function($scope, $http){

        $scope.identificarse = function scopeIdentificarse(){
          $http({
            url: ABS_PATH + '/login',
            method: 'POST',
            data: {
              nombre: $scope.nombre,
              contrasena: $scope.contrasena
            }
          }).then(function loginSuccessCallback(response){
            console.log("success", response);
            if (response.data.exito)
            {
              alertify.success("Identificado con éxito! Redireccionando...");
              window.location.href = ABS_PATH + "/dashboard"
            } else {
              alertify.error("Error al identificarse. Compruebe los campos.");
            }
          }, function loginErrorCallback(response){
            console.log("error", response);
            alertify.error("Error al identificarse. Compruebe los campos.");
          });
        };

       });
