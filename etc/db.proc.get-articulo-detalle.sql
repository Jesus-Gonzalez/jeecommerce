CREATE OR REPLACE FUNCTION get_articulo_detalle (bigint)
RETURNS SETOF refcursor AS $$
BEGIN

DECLARE cur_articulos refcursor;
DECLARE cur_categoria refcursor;
DECLARE cur_comentarios refcursor;

	BEGIN
	OPEN cur_articulos FOR
		SELECT * FROM articulos WHERE artid = $1;

	RETURN NEXT cur_articulos;

	OPEN cur_categoria FOR
		SELECT nombre FROM categorias
			WHERE catid = (SELECT catid FROM articulos WHERE artid = $1);

	RETURN NEXT cur_categoria;

	OPEN cur_comentarios FOR SELECT * FROM comentarios WHERE artid = $1;
	RETURN NEXT cur_comentarios;
	END;

END
$$ LANGUAGE plpgsql;