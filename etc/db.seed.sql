INSERT INTO categorias (supercat, nombre, descripcion)
VALUES
(NULL, 'CATEGORÍA RAÍZ', 'CATEGORÍA RAÍZ'),
(1, 'Productos Agrícolas', 'Selección de productos agrícolas de alta calidad'),
(1, 'Servicios Agrícolas', 'Servicios agrícolas al mejor precio del mercado'),
(2, 'Abonos', 'Abonos para sus tierras'),
(2, 'Alfalfa', 'Alimento para los animales')

-- SELECT * FROM categorias

--

INSERT INTO public.articulos(
            catid, nombre, descripcion, precio, imagen)
VALUES
(?, ?, ?, ?, ?),
(?, ?, ?, ?, ?),
(?, ?, ?, ?, ?),
(?, ?, ?, ?, ?),;

INSERT INTO private.configuracion(
            parametro, valor)
    VALUES ('NumElementosPorPagina', 20);