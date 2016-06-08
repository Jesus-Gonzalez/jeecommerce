var REFERRER = typeof REFERRER === 'undefined' ? undefined : REFERRER;


angular.module('jeecommerce')
        .controller('registroController', function($scope, $http){

          $('#registro').on('submit', function(){


            var nombre = $('#nombre').val(),
                email = $('#email').val(),
                contrasena = $('#contrasena').val(),
                recontrasena = $('#recontrasena').val();

            $.ajax({
              url: ABS_PATH + '/usuarios/crear',
              type: 'POST',
              dataType: 'json',
              data: {
                      nombre: nombre,
                      correo: email,
                      contrasena: contrasena,
                      contrasenaConfirmar: recontrasena
                    }
            })
            .done(function successOnReg(data) {

              if (data.error)
              {
                alertify.error("Error creando al usuario");
                return;
              }

              $.ajax({
                url: ABS_PATH + '/usuarios/login',
                type: 'POST',
                dataType: 'json',
                data: {
                        email: email,
                        contrasena: contrasena
                      }
              })
              .done(function successOnLoginAfterReg(data) {

                alertify.success("Se ha registrado e identificado con éxito en el sitio web.");

                window.location.href = REFERRER || 'catalogo.html';
              })
              .fail(function failOnLoginAfterReg(data) {
                console.log("error@login");
                console.log(data);
              });
            })
            .fail(function failOnReg(data) {
              console.log("error@registro");
              console.log(data);
              alertify.error("Ha sucedido un error creando el usuario. Por favor, compruebe los campos.");
            });

            //window.location.href = "/carro.html";
            return false;
          });

        })
        .controller('loginController', function($scope, $http){

          $('#login').on('submit', function onLoginSubmit(){

            $.ajax({
              url: ABS_PATH + '/usuarios/login',
              type: 'POST',
              dataType: 'json',
              data: {
                      email: $scope.email,
                      contrasena: $scope.contrasena
                    }
            })
            .done(function(data) {
              console.log("success@login");
              console.log(data);

              if (!data.error)
              {
                alertify.success("Se ha identificado con éxito en el sitio web.");
                window.location.href = REFERRER || 'catalogo.html';
                return;
              }

              if (data.error)
              {
                if (data.error.cuenta && data.error.cuenta.activado)
                {
                  alertify.error("La cuenta está pendiente de activación.");
                  $('#login-email').parent().addClass('has-error');
                  $('#login-email').on('keyDown.login-email', function keyDownLoginEmail() {
                    $('#login-email').parent().removeClass('has-error');
                    $('#login-email').off('keyDown.login-email');
                  });
                  return;
                }

                if (data.usuario)
                {
                  if (data.error.usuario.existe)
                  {
                    alertify.error("El usuario introducido no existe.");
                    $('#login-email').parent().addClass('has-error');
                    $('#login-email').on('keyDown.login-email', function keyDownLoginEmail() {
                      $('#login-email').parent().removeClass('has-error');
                      $('#login-email').off('keyDown.login-email');
                    });
                  }
                  else if (data.error.usuario.contrasena)
                  {
                    alertify.error("La contraseña introducida no es correcta.");
                    $('#login-pwd').parent().addClass('has-error');
                    // Arrow functions sample: ES2015
                    $('#login-pwd').on('keydown.login-pwd', () => {
                      $('#login-pwd').parent().removeClass('has-error');
                      $('#login-pwd').off('keydown.login-pwd');
                    });

                    // Let's keep this here, just in case the final browser won't support arrow functions
                    $('#login-pwd').on('keydown.login-pwd', function() {
                      $('#login-pwd').parent().removeClass('has-error');
                      $('#login-pwd').off('keydown.login-pwd');
                    });
                  }
                }
              }
            })
            .fail(function(data) {
              console.log("error@login");
              console.log("error log: " + data);
              alertify.error("Ha sucedido un error identificándose. Inténtelo de nuevo.");
            });

            return false;
          });
        });
