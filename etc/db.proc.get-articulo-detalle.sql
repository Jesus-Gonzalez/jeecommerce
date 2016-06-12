--
-- TOC entry 214 (class 1255 OID 16390)
-- Name: get_articulo_detalle(bigint); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION get_articulo_detalle(bigint) RETURNS SETOF refcursor
    LANGUAGE plpgsql
    AS $_$
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
$_$;
