angular.module('jeecommerce.private')
       .controller('productosController', function($scope, $http){

         var $formProducto;

         $(document).ready(function(){
           $formProducto = $('#form-producto');

           $formProducto.dialog({
             autoOpen: false,
             modal: true,
             draggable: false
           });
         });

         $scope.showCrearProducto = function showCrearProducto() {
           $formProducto.dialog('option', 'title', 'Crear Producto');
           $formProducto.dialog('option', 'buttons', [
             {
               text: "Guardar",
               class: 'btn btn-success',
               click: $scope.crearProducto,
             },

             {
               text: "Cancelar",
               class: 'btn btn-danger',
               click: function(){
                $formProducto.dialog('close');
               }
             }
           ]);
           $formProducto.dialog('open');
         };

         $scope.crearProducto = function scopeCrearProducto() {
           console.log("creating product");
         };

       });
