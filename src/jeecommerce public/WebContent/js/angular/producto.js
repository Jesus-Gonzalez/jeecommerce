angular.module('jeecommerce')
        .controller('productoController', ['$scope', '$http', function($scope, $http) {

          $scope.anadirAlCarro = function(artid){

            var cantidad = new Number(0);

            try {

                cantidad = new Number($('#txt-cantidad-anadir').val());

            } catch (e) {

              alertify.error("Debe introducir un número como cantidad");
              return;

            }


            if (!cantidad || cantidad == 0)
            {
              alertify.error("Debe introducir una cantidad para el producto/servicio")
              return;
            }

            $http({

  					  method: 'POST',
  					  url: ABS_PATH + '/carro/add',
  					  data: { artid: artid, cantidad: cantidad }

  					}).then(function successCallback(response) {

                if (response.data.exito && response.data.exito === true)
                {
                  alertify.success("Se ha añadido el producto al carro.");

                  $('#lbl-carro-total').text(response.data.total);
                  $('#lbl-carro-count').text(response.data.countArticulos);

                } else {
                  alertify.error("Ha ocurrido un error al añadir el producto al carro.");
                }

              }, function errorCallback(response) {

                alertify.error("Ha ocurrido un error al añadir el producto al carro.");

  					  });
          };

        }])
        .controller('comentariosController', ['$scope', '$http', function($scope, $http){

        }]);
