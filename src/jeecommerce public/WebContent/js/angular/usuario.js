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
              console.log("success@registro");

            console.log(data);

              if (data.error)
              {
                alertify.error("Error creando al usuario");
                console.log(data.error);
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
                console.log("success@login");
                console.log(data);

                alertify.success("Se ha registrado e identificado con éxito en el sitio web.");

                window.location.href = REFERRER || 'catalogo.html';
              })
              .fail(function failOnLoginAfterReg() {
                console.log("error@login");
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

            console.log("email: " + $scope.email);
            console.log("contrasena: " + $scope.contrasena);

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

              alertify.success("Se ha identificado con éxito en el sitio web.");

              window.location.href = REFERRER || 'catalogo.html';
            })
            .fail(function(data) {
              console.log("error@login");
              console.log("error log: " + data);
              alertify.error("Ha sucedido un error identificándose. Inténtelo de nuevo.");
            });

            return false;
          });
        });
