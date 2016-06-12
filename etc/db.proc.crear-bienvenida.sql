--
-- TOC entry 213 (class 1255 OID 16389)
-- Name: crear_bienvenida(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION crear_bienvenida() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

	BEGIN
		INSERT INTO bienvenida (uid) VALUES (NEW.uid);
		RETURN NULL;
	END;

$$;
