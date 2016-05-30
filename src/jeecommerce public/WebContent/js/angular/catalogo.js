// TODO: Fix pagination

angular.module('jeecommerce')
			.service('productosService', function($http){

				this.anadirAlCarro = function(artid, cantidad){

					if (typeof cantidad === 'undefined')
					{
						try {
								var cantidad = new Number(0);
								cantidad = new Number($('#txt-cantidad-anadir').val());

						} catch (e) {

							alertify.error("Debe introducir un número como cantidad");
							return;

						}
					}


					if (typeof cantidad ==='undefined' || cantidad === 0)
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

				return this;
			})
	   	.factory('catalogoFactory', function($http){

	   		return {

	   			productos: [],

	   			categoria: -1,

	   			paginas: 0,

					pagina: 0,

	   			loadProductos: function(callback){

	   				var factoria = this;

	   				if (typeof this.categoria === 'undefined')
	   					this.categoria = -1;

						if (typeof this.pagina === 'undefined')
							this.pagina = 0;

	   				$http({

					  method: 'POST',
					  url: ABS_PATH + '/articulos/get',
					  data: { categoria: this.categoria, pagina: this.pagina }

					}).then(function successCallback(response) {

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

					    if (callback)
					    	callback();

					  }, function errorCallback(response) {

					  	console.log("error");
					    console.log(response);

					  });

	   			}
	   		}

	   	})
			// Controlador de las categorías
	   	.controller('categoriasController', ['$http', '$scope', 'catalogoFactory', function($http, $scope, $catalogo){

			$scope.categorias = [];

			$http({

				method: 'GET',
				url: ABS_PATH + '/categorias/get'

			}).then(function success(response){

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
		// Controlador del catálogo
		.controller('catalogoController', ['$scope', 'catalogoFactory', 'productosService', function($scope, $catalogo, $productos){

			$scope.fechaActual = new Date().getTime();

			$scope.anadirAlCarro = $productos.anadirAlCarro;

			// Cuando la página cargue por primera vez el controlador:
			// Se establece la categoría como -1 (indefinida/todas categorias)
			$catalogo.categoria = -1;
			// Cargamos los productos para la categoría
			$catalogo.loadPaginas();
			$catalogo.loadProductos();

			$scope.$watch(function(){ return $catalogo.productos; }, function(nuevoValor, oldValor){
				if (typeof nuevoValor != 'undefined' && typeof nuevoValor.paginas === 'undefined')
				{
					$scope.productos = $catalogo.productos;
				}
			});
		}])
		// Controlador de paginación del catálogo
		.controller('paginacionController', ['$scope', 'catalogoFactory', function($scope, $catalogo){

			$catalogo.loadPaginas();

			$scope.$watch(function(){ return $catalogo.pagina; }, function(nuevo, old){
				if (typeof nuevo != 'undefined')
				{
					$scope.pagina = $catalogo.pagina;
				}
			});

			$scope.$watch(function(){ return $catalogo.paginas; }, function(nuevo, old){
				if (typeof nuevo != 'undefined')
				{
					$scope.paginas = $catalogo.paginas;
				}
			});

			$scope.irToPagina = function(pagina){
				$catalogo.pagina = pagina;
				$catalogo.loadProductos();
			};

		}]);
