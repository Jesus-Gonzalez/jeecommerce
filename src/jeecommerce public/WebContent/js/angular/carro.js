angular.module('jeecommerce')
        .controller('carroController', function($scope, $http){

          $scope.eliminarProductoDelCarro = function(artid){
            $http({
              method: 'POST',
              url: ABS_PATH + "/carro/update",
              data: {
                artid: artid,
                operacion: 'delete'
              }
            }).then(function successCallbackAtDelete(response){

              console.log("borrado");
              console.log(response);

              if (response.data.exito)
              {
                $('tr.producto[data-artid="' + artid + '"]').remove();

                $('#txt-precio-total').text(response.data.total);

                alertify.success("Artículo borrado con éxito");

                if ($('tr.producto').length === 0)
                {
                  $('#carro-con-productos').addClass('hidden');
                  $('#alert-no-hay-productos').removeClass('hidden');
                }
              }

            }, function errorCallbackAtDelete(response){
              console.log("error borrando:response", response);
            });
          };

          $scope.actualizaProductoDelCarro = function(artid){

            var cantidad = $('tr.producto[data-artid="' + artid + '"]').find('.input-cantidad').val();

            $http({
              method: 'POST',
              url: ABS_PATH + "/carro/update",
              data: {
                artid: artid,
                operacion: 'update',
                cantidad: cantidad
              }
            }).then(function successCallbackAtDelete(response){

              console.log("actualizado");
              console.log(response);

              if (response.data.exito)
              {
                $('tr.producto[data-artid="' + artid + '"]').find('.subtotal-producto').text(response.data.subtotal / 100);

                $('#txt-precio-total').text(response.data.total);

                alertify.success("Artículo actualizado con éxito");
              }

            }, function errorCallbackAtDelete(response){
              console.log("error actualizando:response", response);
            });

          };
        });
