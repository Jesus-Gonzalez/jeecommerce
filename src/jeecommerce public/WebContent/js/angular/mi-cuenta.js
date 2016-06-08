angular.module('jeecommerce')
      .controller('cuentaController', function($scope, $http){

        $scope.modificarPerfil = function(){

          var hayErrores = false;

          if ($scope.frmPerfil.$invalid)
          {
            alertify.error("El formulario contiene campos no válidos. Por favor, revise los campos.");
            hayErrores = true;
            return;
          }

          if (typeof $scope.email != 'undefined' && $scope.email.length < 6)
          {
            alertify.error("El campo <email> contiene fallos.");
            $('#email').addClass('ng-invalid');
            hayErrores = true;
          }

          if (typeof $scope.contrasenaNueva != 'undefined')
          {
            if ($scope.contrasenaNueva.length < 6)
            {
              alertify.error('La longitud de su nueva contraseña debe ser mayor de 6');
              $('#contrasenaNueva').addClass('ng-invalid');
              hayErrores = true;
            }

            if (typeof $scope.contrasenaActual === 'undefined')
            {
              alertify.error('Debe introducir su contraseña actual para cambiar de contraseña');
              $('#contrasenaActual').addClass('ng-invalid');
              hayErrores = true;
            }

            if (typeof $scope.contrasenaNuevaConfirmar === 'undefined')
            {
              alertify.error('Debe confirmar su contraseña para modificar su perfil.');
              $('#contrasenaNuevaConfirmar').addClass('ng-invalid')
              hayErrores = true;
            }
          }

            // Si hay errores, volver.
            if (hayErrores === true)
              return;

            // Si no hay errores modificar el perfil y hacer comprobación por parte del servidor
            $http({
              method: 'POST',
              url: ABS_PATH + "/usuarios/perfil",
              data: {
                tipo: 'modificar',
                perfil: {
                  correo: $('#email').val(),
                  oldContrasena: $('#contrasenaActual').val(),
                  newContrasena: $('#contrasenaNueva').val(),
                  confirmContrasena: $('#contrasenaNuevaConfirmar').val()
                }
              }
            }).then(function successEditProfileCallback(response){

              if (response.data.success)
              {
                alertify.success("Su cuenta ha sido modificada con éxito.");

                if (response.data.activacion)
                {
                  $scope.cuentaNecesitaVerificacion = true;
                }
              }

            }, function errorEditProfileCallback(response){
              alertify.error("Ha sucedido un error durante la modificación de su cuenta. Por favor inténtelo más tarde o contacte con nosotros");
            });
        };

        $scope.modificarDireccion = function(did){

          window.location.href = ABS_PATH + '/editar-direccion.html?did=' + did;

        };

      });
