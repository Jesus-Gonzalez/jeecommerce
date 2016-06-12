angular.module('jeecommerce')
       .controller('pedidoController', function($scope, $http){

         var did,
             payment;

        $scope.direcciones = [];

        $scope.pid = "";

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
                        if (data.data.did)
                          did = data.data.did;

                        alertify.success('Dirección creada, proceda a pagar el pedido.');
                        doDireccionesHide();
                        $scope.direcciones.push({
                                                  did: did,
                                                  nombre: $scope.nombre,
                                                  direccion: $scope.direccion,
                                                  localidad: $scope.localidad,
                                                  codigoPostal: $scope.codpostal,
                                                  telefono: $scope.telefono
                                                });
                        $scope.nombre = $scope.direccion = $scope.localidad = $scope.codpostal = $scope.telefono = "";
                     },
                     function errorOnCreateDireccionCallback(data, status, headers, config) {
                       alertify.error("Ha sucedido algún error, por favor, verifique los campos introducidos");
                     });
          };

          $scope.seleccionarDireccion = function seleccionarDireccion(_did){
            did = _did;
            doDireccionesHide();
          };


          function doDireccionesHide(){
            $('#direcciones-wrapper').slideUp(345, function afterDireccionesHide(){
              $('#pago-wrapper').css('display', 'none').removeClass('hidden').slideDown(987, function afterPagoShow(){

                $('html,body').animate( { 'scrollTop': $('#pago-wrapper').offset().top } );

                $('#crear-direccion-panel').removeClass('in');
                $('#direcciones-wrapper').addClass('hidden');
              })
            });
          }

          function doDireccionesShow(){
            $('#pago-wrapper').slideUp(345, function afterDireccionesHide(){
              $('#direcciones-wrapper').css('display', 'none').removeClass('hidden').slideDown(987, function afterPagoShow(){

                $('html,body').animate( { 'scrollTop': $('#direcciones-wrapper').offset().top } );

                $('#pago-wrapper').addClass('hidden');
              })
            });
          }



          // Payment Actions //

          // Starts selection month/year

          $(document).ready(function() {
            var fechaActual = new Date(),
                mes = (fechaActual.getMonth() + 1).toString(),
                ano = fechaActual.getFullYear().toString();

            mes = mes[1] ? mes : "0" + mes;

            $('#mes-caducidad-tarjeta').find('option[value="' + mes + '"]').attr('selected', true);
            $('#ano-caducidad-tarjeta').find('option[value="' + ano + '"]').attr('selected', true);
          });

          // End selection month year

          $scope.returnToDirecciones = function returnToDirecciones(){
            doDireccionesShow();
          };

          $scope.pagar = function pagar(){

            // Comprobar si se va a realizar el pago con tarjeta
            if ($('#slc-tarjeta').hasClass('in'))
            {
              var $numeroTarjeta = $('#numero-tarjeta'),
                  $mesCaducidad = $('#mes-caducidad-tarjeta'),
                  $anoCaducidad = $('#ano-caducidad-tarjeta'),
                  $cvc = $('#cvc-tarjeta');

              // TODO: Añadir validación de número de tarjeta
              if (!$numeroTarjeta.val())
              {
                alertify.error("Introduzca una tarjeta de crédito válida.");

                $numeroTarjeta.parent().addClass('has-error');
                $numeroTarjeta.on('focus', function keyDownLoginEmail() {
                  $numeroTarjeta.parent().removeClass('has-error');
                  $numeroTarjeta.off('focus');
                });
              }

              if (!$cvc.val())
              {
                alertify.error("Debe introducir un código de seguridad para la tarjeta.");

                $cvc.parent().addClass('has-error');
                $cvc.on('focus', function keyDownLoginEmail() {
                  $cvc.parent().removeClass('has-error');
                  $cvc.off('focus');
                });
              }

              Stripe.card.createToken({

                number: $numeroTarjeta.val(),
                exp_month: $mesCaducidad.val(),
                exp_year: $anoCaducidad.val(),
                cvc: $cvc.val()

              }, function createStripeTokenCallback(status, response) {

                hacerPago({
                  formaPago: 'tarjeta',
                  token: response.id
                });

              });
            } else {
              var formaPago = $('input[type="radio"][name="forma-pago"]:checked').val();

              hacerPago({ formaPago: formaPago });

            }

            function hacerPago(data)
            {
              if (typeof did === 'undefined')
                return;

              data.did = did;

              $http({
                method: 'POST',
                url: ABS_PATH + '/pagar',
                data: data
              }).then(function onPaymentDone(response) {

                  var pid = response.data.pid;

                  if (pid)
                  {
                    $('#pasos-wrapper').fadeOut('fast', function fadeOutPasos() {
                      $('.pid-identificador').each(function(i,l){
                        $(l).text(pid);
                      });

                      if (data.formaPago != "contrarrembolso")
                      {
                        $('#forma-pago-' + data.formaPago).removeClass('hidden');
                      }

                      $('#pedido-confirmado-wrapper').hide().removeClass('hidden').fadeIn(767);
                    });
                  } else {
                    alertify.error("Ha sucedido un error creando su pedido. Inténtelo de nuevo o pruebe más tarde.");
                  }

                }, function errOnPaymentDone(response) {
                  console.log("error@hacerPago");
                  console.log(response);
                });
            }

          };

       });
