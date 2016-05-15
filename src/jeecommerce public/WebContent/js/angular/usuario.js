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
            .done(function(data) {
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
              .done(function(data) {
                console.log("success@login");
                console.log(data)
              })
              .fail(function() {
                console.log("error@login");
              });

              // setTimeout(function(){
              //   window.location.href = ABS_PATH + "/carro.html";
              // }, 567);
            })
            .fail(function(data) {
              console.log("error@registro");
              console.log(data);
              alertify.error("Ha sucedido un error creando el usuario. Por favor, compruebe los campos.");
            });

            //window.location.href = "/carro.html";
            return false;
          });

        })
        .controller('loginController', function($scope, $http){

        });
