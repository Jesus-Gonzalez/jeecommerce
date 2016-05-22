// TODO: Fix pagination

angular.module('jeecommerce')
	   	.factory('catalogoFactory', function($http){

	   		return {

	   			productos: [],

	   			categoria: -1,

	   			paginas: 0,

	   			loadProductos: function(callback, pagina){

	   				var factoria = this;

	   				if (!this.categoria)
	   					this.categoria = -1;

	   				if (!this.pagina)
	   					this.pagina = 1;

	   				$http({

					  method: 'POST',
					  url: ABS_PATH + '/articulos/get',
					  data: { categoria: this.categoria, pagina: this.pagina }

					}).then(function successCallback(response) {

						console.log(response.data);

					    factoria.productos = response.data;

					    if (callback)
					    	callback();

					  }, function errorCallback(response) {

					  	console.log("error");
					    console.log(response);

					  });
	   			},

	   			loadPaginas: function(callback){

	   				var factoria = this;

	   				if (!this.categoria)
	   					this.categoria = -1;

	   				$http({

					  method: 'POST',
					  url: ABS_PATH + '/articulos/get/paginas',
					  data: { categoria: this.categoria }

					}).then(function successCallback(response) {

					    factoria.paginas = response.data.paginas;

							console.log("getPaginas: ", response)

					    if (callback)
					    	callback();

					  }, function errorCallback(response) {

					  	console.log("error");
					    console.log(response);

					  });

	   			}
	   		}

	   	})
	   	.controller('categoriasController', ['$http', '$scope', 'catalogoFactory', function($http, $scope, $catalogo){

			$scope.categorias = [];

			$http({

				method: 'GET',
				url: ABS_PATH + '/categorias/get'

			}).then(function success(response){

				console.log("success@get.categorias", response);

				var categorias = response.data,
					categoria;

				function getSubcategorias(categoria)
				{
					var $sublist;

					if (categoria.subcategorias)
					{
						$sublist = $(document.createElement("ul"));
						for (var i=0; i < categoria.subcategorias.length; i++)
						{
							$sublist.append('<li><a href="javascript:void(0)" data-catid="' + categoria.subcategorias[i].catid + '" class="categoria-link"><i class="fa fa-leaf fw"></i> ' + categoria.subcategorias[i].nombre + '</a></li>');
							$sublist.append(getSubcategorias(categoria.subcategorias[i]));
						}

						return $sublist;
					}
				}

				var $ulCats = $('#list-categorias');
				var $listCats;

				for (var i=0; i < categorias.length; i++)
				{
					var $listCats = $('<li><a href="javascript:void(0)" data-catid="' + categorias[i].catid + '" class="categoria-link"><i class="fa fa-cube fw"></i> ' + categorias[i].nombre + '</a></li>');

					$listCats.append(getSubcategorias(categorias[i]));

					$ulCats.append($listCats);
				}

			}, function error(){

				console.log("error@get.categorias", response);

			});

			// Función que nos permite cargar productos para la categoría
			$(document).on('click', '.categoria-link', function(){

				var categoria = $(this).data('catid');

				$catalogo.categoria = categoria;

				$catalogo.loadPaginas();
				$catalogo.loadProductos();
			});

		}])
		.controller('catalogoController', ['$scope', 'catalogoFactory', function($scope, $catalogo){

			$scope.fechaActual = new Date().getTime();

			// Cuando la página cargue por primera vez el controlador:
			// Se establece la categoría como -1 (indefinida/todas categorias)
			$catalogo.categoria = -1;
			// Cargamos los productos para la categoría
			$catalogo.loadPaginas();
			$catalogo.loadProductos();

			$scope.$watch(function(){ return $catalogo.productos; }, function(nuevoValor, oldValor){
				if (typeof nuevoValor != 'undefined' && typeof nuevoValor.paginas === 'undefined')
				{
					console.log("nuevo: ", nuevoValor);
					console.log("productos: ", $catalogo.productos);
					$scope.productos = $catalogo.productos;
				}
			});
		}])
		.controller('paginacionController', ['$scope', 'catalogoFactory', function($scope, $catalogo){

			$scope.fechaActual = new Date().getTime();

			$catalogo.loadPaginas();

			$scope.$watch(function(){ return $catalogo.paginas; }, function(nuevo, old){
				if (typeof nuevo != 'undefined')
				{
					$scope.paginas = $catalogo.paginas;
				}
			});

		}]);
