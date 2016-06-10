angular.module('jeecommerce')
        .controller('productoController', ['$scope', '$http', 'productosService', function($scope, $http, $productos) {

          $scope.anadirAlCarro = $productos.anadirAlCarro;

        }])
        .controller('comentariosController', ['$scope', '$http', function($scope, $http){

          $scope.comentarios = [];

          $scope.comentar = function comentar(artid){

            var captchaResponse = grecaptcha.getResponse();

            if (!captchaResponse)
            {
              alertify.error("Captcha no validado");
              return;
            }

            grecaptcha.reset();

            $http({
              url: ABS_PATH + "/comentarios/crear/async",
              method: 'POST',
              data: {
                artid: artid,
                contenido: $scope.contenido,
                captchaResponse: captchaResponse
              }
            }).then(function successOnComment(response){
              console.log("success", response);
              var comentario = {
                contenido: $scope.contenido,
                fecha: new Date()
              };

              $scope.comentarios.push(comentario);

              $('#wrapper-sin-comentarios').hide();

              alertify.success("Éxito realizando el comentario.");

            }, function errorOnComment(response){

              console.log("error", response);


              alertify.error("Error al realizar el comentario. Por favor, compruebe los datos.");

            });
          };

        }]);
