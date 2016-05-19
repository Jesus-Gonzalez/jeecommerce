angular.module('jeecommerce')
       .controller('pedidoController', function($scope, $http){

         var did,
             payment;

         $scope.anadirDireccion = function anadirDireccion(){

            var nombre = $('#input-nombre').val(),
                direccion = $('#input-direccion').val(),
                localidad = $('#input-localidad').val(),
                codpostal = $('#input-codigo-postal').val(),
                provincia = $('#input-provincia').val(),
                telefono = $('#input-telefono').val();

                 $http({
                   method: 'POST',
                   url: ABS_PATH + '/direccion/crea',
                   data: {
                     nombre: $scope.nombre,
                     direccion: $scope.direccion,
                     localidad: $scope.localidad,
                     codpostal: $scope.codpostal,
                     telefono: $scope.telefono
                   }
                 }).then(function createDireccionSuccessCallback(data, status, headers, config) {
                        console.log("did returned: " + data.data.did)

                        if (data.data.did)
                          did = data.data.did;

                        $('#direcciones-wrapper').slideUp(345, function afterDireccionesHide(){
                          $('#pago-wrapper').css('display', 'none').removeClass('hidden').slideDown(987, function afterPagoShow(){
                            console.log("slided down pago-wrapper");

                            alertify.success('Dirección creada, proceda a pagar el pedido.');

                            $('html,body').animate( { 'scrollTop': $('#pago-wrapper').offset().top } );
                          })
                        });
                     },
                     function errorOnCreateDireccionCallback(data, status, headers, config) {
                       alertify.error("Ha sucedido algún error, por favor, verifique los campos introducidos");
                     });
          };

          $scope.pagar = function pagar(){

            // Comprobar si se va a realizar el pago con tarjeta
            if ($('#slc-tarjeta').hasClass('in'))
            {
              Stripe.card.createToken({

                number: $scope.numeroTarjeta,
                exp_month: $scope.mesCaducidadTarjeta,
                exp_year: $scope.anoCaducidadTarjeta,
                cvc: $scope.cvc

              }, function createStripeTokenCallback(status, response) {

                console.log("token generado: " + response.id);

                $http({
                  method: 'POST',
                  url: '/pagar',
                  data: {
                    formaPago: 'tarjeta',
                    token: response.id
                  }
                }).then(function onPaymentDone(response) {
                    console.log("success@pagarConTarjeta");
                    console.log(response);
                  }, function errOnPaymentDone(response) {
                    console.log("error@pagarConTarjeta");
                    console.log(response);
                  });
              });
            } else {
              console.log("Not paying with credit card");
            }

          };

       });
