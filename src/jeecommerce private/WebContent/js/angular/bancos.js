angular.module('jeecommerce.private')
       .controller('bancosController', function($scope, $http){

         $scope.bancos = [];

         var $formBanco;

         $(document).ready(function(){
           $formBanco = $('#form-banco');

           $formBanco.dialog({
             autoOpen: false,
             modal: true,
             draggable: false
           });
         });




        // CREATE BANKS METHODS

         $scope.showCrearBanco = function showCrearBanco() {

           $scope.bid = "";
           $scope.nombre = "";
           $scope.numero = "";
           $scope.activo = "";
           
           $formBanco.dialog('option', 'title', 'Crear Banco');
           $formBanco.dialog('option', 'buttons', [
             {
               text: "Guardar",
               class: 'btn btn-success',
               click: $scope.crearBanco,
             },

             {
               text: "Cancelar",
               class: 'btn btn-danger',
               click: function(){
                $formBanco.dialog('close');
               }
             }
           ]);

           $formBanco.dialog('open');
         };

         $scope.crearBanco = function scopeCrearBanco() {
           $http({
             url: ABS_PATH + "/bancos/crud",
             method: 'POST',
             data: {
               accion: 'crear',
               nombre: $scope.nombre,
               numero: $scope.numero,
               activo: $scope.activo === true || false
             }
           }).then(function successCrearBanco(response){
             console.log("success", response);

             if (response.data.exito)
             {
               alertify.success("Banco creado con éxito :)");

               $scope.bancos.push({
                 bid: response.data.bid,
                 nombre: $scope.nombre,
                 numero: $scope.numero,
                 activo: $scope.activo
               });

               $('#sin-bancos').hide();
               $('#tabla-bancos').show();

               $formBanco.dialog('close');

             } else if (response.data.error) {

               alertify.error("Error creando nuevo banco. Compruebe los datos.");
             }

           }, function errorCrearBanco(response){
             alertify.error("Error creando nuevo banco. Compruebe los datos.");
           });
         };







         // EDIT BANKS METHODS

         $scope.showEditarBanco = function showEditarBanco(bid) {

           $http({
             url: ABS_PATH + "/bancos/crud",
             method: 'POST',
             data: {
               accion: 'get',
               bid: bid, // @ES6 just bid,
               // Bad planned: I have to pass details unnecesary details ...
               nombre: "getBanco",
               numero: "getBanco",
               activo: false
             }
           }).then(function successGetBanco(response){

             if (response.data.exito)
             {
               $scope.bid = bid;
               $scope.nombre = response.data.nombre;
               $scope.numero = response.data.numero;
               $scope.activo = response.data.activo;

               $formBanco.dialog('option', 'title', 'Editar Banco - ID: ' + bid);

               $formBanco.dialog('option', 'buttons', [
                 {
                   text: "Actualizar",
                   class: 'btn btn-success',
                   click: $scope.editarBanco,
                 },

                 {
                   text: "Cancelar",
                   class: 'btn btn-danger',
                   click: function(){
                    $formBanco.dialog('close');
                   }
                 }
               ]);

               $formBanco.dialog('open');

             } else if (response.data.error) {

               alertify.error("Error cuando se obtenían datos del banco");
             }

           }, function errorGetBanco(response){

             alertify.error("Error cuando se obtenían datos del banco");
           });
         };

         $scope.editarBanco = function editarBanco() {
           $http({
             url: ABS_PATH + '/bancos/crud',
             method: 'POST',
             data: {
               accion: 'actualizar',
               bid: $scope.bid,
               nombre: $scope.nombre,
               numero: $scope.numero,
               activo: $scope.activo === true || false
             }
           }).then(function successEditarBanco(response){

             console.log("response@sucess", response);

             if (response.data.exito)
             {
               var $trBanco = $('#tabla-bancos').find('tr[data-bid="' + $scope.bid + '"]');

               $trBanco.find('.nombre').text($scope.nombre);
               $trBanco.find('.numero').text($scope.numero);
               $trBanco.find('.activo').text($scope.activo === true ? "Activo" : "No Activo");

               $formBanco.dialog('close');

               alertify.success("Éxito editando el banco. :)");

             } else if  (response.data.error) {
               alertify.error("Error al editar el banco.");
             }

           }, function errorEditarBanco(response){
             console.log("response@error", response);

             alertify.error("Error al editar el banco.");
           });
         };





         // BANCO BORRAR
         $scope.confirmBorrarBanco = function confirmBorrarBanco(bid) {
           if ( confirm("¿Realmente desea borrar el banco con ID '" +  bid  + "' ?") )
           {
             $http({
               url: ABS_PATH + "/bancos/crud",
               method: 'POST',
               data: {
                 accion: 'borrar',
                 bid: bid,
                 // Bad planned: unnecesary fields
                 nombre: 'borrarBanco',
                 numero: 'borrarBanco',
                 activo: false
               }
             }).then(function successBorrarBanco(response){

               if (response.data.exito)
               {
                 $('#tabla-bancos').find('tr[data-bid="' + bid + '"]').remove();

                 alertify.success('Éxito borrando banco. :)');

               } else if (response.data.error) {

                 alertify.error('Error borrando banco.');
               }
             }, function errorBorrarBanco(response){

               alertify.error('Error borrando banco.');
             })
           }
         };
       });
