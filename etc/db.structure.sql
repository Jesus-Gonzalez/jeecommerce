--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.6
-- Dumped by pg_dump version 9.4.6
-- Started on 2016-04-21 17:00:26 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2115 (class 1262 OID 75363)
-- Name: jeecommerce; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE jeecommerce WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_ES.UTF-8' LC_CTYPE = 'es_ES.UTF-8';


\connect jeecommerce

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 8 (class 2615 OID 75513)
-- Name: chats; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA chats;


--
-- TOC entry 9 (class 2615 OID 75541)
-- Name: private; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA private;


--
-- TOC entry 1 (class 3079 OID 11861)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2117 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 208 (class 1255 OID 75554)
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


SET search_path = chats, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 191 (class 1259 OID 75516)
-- Name: chats; Type: TABLE; Schema: chats; Owner: -; Tablespace: 
--

CREATE TABLE chats (
    chid bigint NOT NULL,
    nombre character varying NOT NULL,
    email character varying NOT NULL,
    asunto character varying NOT NULL,
    fecha timestamp without time zone NOT NULL,
    estado smallint NOT NULL
);


--
-- TOC entry 190 (class 1259 OID 75514)
-- Name: chats_chid_seq; Type: SEQUENCE; Schema: chats; Owner: -
--

CREATE SEQUENCE chats_chid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2118 (class 0 OID 0)
-- Dependencies: 190
-- Name: chats_chid_seq; Type: SEQUENCE OWNED BY; Schema: chats; Owner: -
--

ALTER SEQUENCE chats_chid_seq OWNED BY chats.chid;


--
-- TOC entry 193 (class 1259 OID 75527)
-- Name: chats_mensajes; Type: TABLE; Schema: chats; Owner: -; Tablespace: 
--

CREATE TABLE chats_mensajes (
    chmid bigint NOT NULL,
    chid bigint NOT NULL,
    es_usuario boolean NOT NULL,
    mensaje character varying NOT NULL,
    fecha timestamp without time zone NOT NULL
);


--
-- TOC entry 192 (class 1259 OID 75525)
-- Name: chats_mensajes_chmid_seq; Type: SEQUENCE; Schema: chats; Owner: -
--

CREATE SEQUENCE chats_mensajes_chmid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2119 (class 0 OID 0)
-- Dependencies: 192
-- Name: chats_mensajes_chmid_seq; Type: SEQUENCE OWNED BY; Schema: chats; Owner: -
--

ALTER SEQUENCE chats_mensajes_chmid_seq OWNED BY chats_mensajes.chmid;


SET search_path = private, pg_catalog;

--
-- TOC entry 195 (class 1259 OID 75544)
-- Name: administradores; Type: TABLE; Schema: private; Owner: -; Tablespace: 
--

CREATE TABLE administradores (
    adminid bigint NOT NULL,
    nombre character varying NOT NULL,
    contrasena character varying NOT NULL
);


--
-- TOC entry 194 (class 1259 OID 75542)
-- Name: administradores_adminid_seq; Type: SEQUENCE; Schema: private; Owner: -
--

CREATE SEQUENCE administradores_adminid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2120 (class 0 OID 0)
-- Dependencies: 194
-- Name: administradores_adminid_seq; Type: SEQUENCE OWNED BY; Schema: private; Owner: -
--

ALTER SEQUENCE administradores_adminid_seq OWNED BY administradores.adminid;


SET search_path = public, pg_catalog;

--
-- TOC entry 180 (class 1259 OID 75412)
-- Name: activaciones; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE activaciones (
    aid bigint NOT NULL,
    uid bigint NOT NULL,
    clave character varying NOT NULL
);


--
-- TOC entry 179 (class 1259 OID 75410)
-- Name: activaciones_aid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE activaciones_aid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2121 (class 0 OID 0)
-- Dependencies: 179
-- Name: activaciones_aid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE activaciones_aid_seq OWNED BY activaciones.aid;


--
-- TOC entry 187 (class 1259 OID 75481)
-- Name: articulos; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE articulos (
    artid bigint NOT NULL,
    cod_articulo character varying NOT NULL,
    catid bigint NOT NULL,
    nombre character varying NOT NULL,
    descripcion character varying NOT NULL,
    precio numeric NOT NULL
);


--
-- TOC entry 186 (class 1259 OID 75479)
-- Name: articulos_artid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE articulos_artid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2122 (class 0 OID 0)
-- Dependencies: 186
-- Name: articulos_artid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE articulos_artid_seq OWNED BY articulos.artid;


--
-- TOC entry 177 (class 1259 OID 75393)
-- Name: bienvenida; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE bienvenida (
    uid bigint NOT NULL,
    enviado boolean DEFAULT false NOT NULL,
    bid bigint NOT NULL
);


--
-- TOC entry 178 (class 1259 OID 75402)
-- Name: bienvenida_bid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE bienvenida_bid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2123 (class 0 OID 0)
-- Dependencies: 178
-- Name: bienvenida_bid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE bienvenida_bid_seq OWNED BY bienvenida.bid;


--
-- TOC entry 189 (class 1259 OID 75499)
-- Name: categorias; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE categorias (
    catid bigint NOT NULL,
    supercat bigint,
    nombre character varying NOT NULL,
    descripcion character varying NOT NULL
);


--
-- TOC entry 188 (class 1259 OID 75497)
-- Name: categorias_catid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE categorias_catid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2124 (class 0 OID 0)
-- Dependencies: 188
-- Name: categorias_catid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE categorias_catid_seq OWNED BY categorias.catid;


--
-- TOC entry 182 (class 1259 OID 75428)
-- Name: direcciones; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE direcciones (
    did bigint NOT NULL,
    uid bigint NOT NULL,
    nombre character varying NOT NULL,
    direccion character varying NOT NULL,
    localidad character varying NOT NULL,
    codigo_postal character varying NOT NULL,
    pronvicia character varying NOT NULL,
    telefono character varying NOT NULL
);


--
-- TOC entry 181 (class 1259 OID 75426)
-- Name: direcciones_did_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE direcciones_did_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2125 (class 0 OID 0)
-- Dependencies: 181
-- Name: direcciones_did_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE direcciones_did_seq OWNED BY direcciones.did;


--
-- TOC entry 184 (class 1259 OID 75444)
-- Name: pedidos; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE pedidos (
    pid bigint NOT NULL,
    fecha timestamp without time zone NOT NULL,
    estado smallint NOT NULL,
    importe numeric NOT NULL,
    forma_pago smallint NOT NULL,
    observaciones character varying
);


--
-- TOC entry 185 (class 1259 OID 75452)
-- Name: pedidos_detalles; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE pedidos_detalles (
    pid bigint NOT NULL,
    artid bigint NOT NULL,
    cantidad integer NOT NULL
);


--
-- TOC entry 183 (class 1259 OID 75442)
-- Name: pedidos_pid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE pedidos_pid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2126 (class 0 OID 0)
-- Dependencies: 183
-- Name: pedidos_pid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE pedidos_pid_seq OWNED BY pedidos.pid;


--
-- TOC entry 176 (class 1259 OID 75380)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE usuarios (
    uid bigint NOT NULL,
    email character varying NOT NULL,
    contrasena character varying NOT NULL,
    nombre character varying NOT NULL,
    fecha_registro timestamp without time zone DEFAULT now() NOT NULL,
    fecha_conexion timestamp without time zone,
    direccion_ip inet,
    datos_compras integer[],
    activado boolean DEFAULT false NOT NULL
);


--
-- TOC entry 175 (class 1259 OID 75378)
-- Name: usuarios_uid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE usuarios_uid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2127 (class 0 OID 0)
-- Dependencies: 175
-- Name: usuarios_uid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE usuarios_uid_seq OWNED BY usuarios.uid;


SET search_path = chats, pg_catalog;

--
-- TOC entry 1965 (class 2604 OID 75519)
-- Name: chid; Type: DEFAULT; Schema: chats; Owner: -
--

ALTER TABLE ONLY chats ALTER COLUMN chid SET DEFAULT nextval('chats_chid_seq'::regclass);


--
-- TOC entry 1966 (class 2604 OID 75530)
-- Name: chmid; Type: DEFAULT; Schema: chats; Owner: -
--

ALTER TABLE ONLY chats_mensajes ALTER COLUMN chmid SET DEFAULT nextval('chats_mensajes_chmid_seq'::regclass);


SET search_path = private, pg_catalog;

--
-- TOC entry 1967 (class 2604 OID 75547)
-- Name: adminid; Type: DEFAULT; Schema: private; Owner: -
--

ALTER TABLE ONLY administradores ALTER COLUMN adminid SET DEFAULT nextval('administradores_adminid_seq'::regclass);


SET search_path = public, pg_catalog;

--
-- TOC entry 1960 (class 2604 OID 75415)
-- Name: aid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY activaciones ALTER COLUMN aid SET DEFAULT nextval('activaciones_aid_seq'::regclass);


--
-- TOC entry 1963 (class 2604 OID 75484)
-- Name: artid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY articulos ALTER COLUMN artid SET DEFAULT nextval('articulos_artid_seq'::regclass);


--
-- TOC entry 1959 (class 2604 OID 75404)
-- Name: bid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY bienvenida ALTER COLUMN bid SET DEFAULT nextval('bienvenida_bid_seq'::regclass);


--
-- TOC entry 1964 (class 2604 OID 75502)
-- Name: catid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY categorias ALTER COLUMN catid SET DEFAULT nextval('categorias_catid_seq'::regclass);


--
-- TOC entry 1961 (class 2604 OID 75431)
-- Name: did; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY direcciones ALTER COLUMN did SET DEFAULT nextval('direcciones_did_seq'::regclass);


--
-- TOC entry 1962 (class 2604 OID 75447)
-- Name: pid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos ALTER COLUMN pid SET DEFAULT nextval('pedidos_pid_seq'::regclass);


--
-- TOC entry 1955 (class 2604 OID 75383)
-- Name: uid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY usuarios ALTER COLUMN uid SET DEFAULT nextval('usuarios_uid_seq'::regclass);


SET search_path = chats, pg_catalog;

--
-- TOC entry 1989 (class 2606 OID 75524)
-- Name: pk_chats; Type: CONSTRAINT; Schema: chats; Owner: -; Tablespace: 
--

ALTER TABLE ONLY chats
    ADD CONSTRAINT pk_chats PRIMARY KEY (chid);


--
-- TOC entry 1991 (class 2606 OID 75535)
-- Name: pk_chats_mensajes; Type: CONSTRAINT; Schema: chats; Owner: -; Tablespace: 
--

ALTER TABLE ONLY chats_mensajes
    ADD CONSTRAINT pk_chats_mensajes PRIMARY KEY (chmid, chid);


SET search_path = private, pg_catalog;

--
-- TOC entry 1993 (class 2606 OID 75552)
-- Name: pk_administradores; Type: CONSTRAINT; Schema: private; Owner: -; Tablespace: 
--

ALTER TABLE ONLY administradores
    ADD CONSTRAINT pk_administradores PRIMARY KEY (adminid);


SET search_path = public, pg_catalog;

--
-- TOC entry 1975 (class 2606 OID 75420)
-- Name: pk_activaciones; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY activaciones
    ADD CONSTRAINT pk_activaciones PRIMARY KEY (aid, uid, clave);


--
-- TOC entry 1983 (class 2606 OID 75489)
-- Name: pk_articulos; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY articulos
    ADD CONSTRAINT pk_articulos PRIMARY KEY (artid, cod_articulo);


--
-- TOC entry 1973 (class 2606 OID 75409)
-- Name: pk_bienvenida; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY bienvenida
    ADD CONSTRAINT pk_bienvenida PRIMARY KEY (uid, bid);


--
-- TOC entry 1987 (class 2606 OID 75507)
-- Name: pk_categorias; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY categorias
    ADD CONSTRAINT pk_categorias PRIMARY KEY (catid);


--
-- TOC entry 1977 (class 2606 OID 75436)
-- Name: pk_direcciones; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY direcciones
    ADD CONSTRAINT pk_direcciones PRIMARY KEY (did, uid);


--
-- TOC entry 1979 (class 2606 OID 75462)
-- Name: pk_pedidos; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY pedidos
    ADD CONSTRAINT pk_pedidos PRIMARY KEY (pid);


--
-- TOC entry 1981 (class 2606 OID 75464)
-- Name: pk_pedidos_detalles; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY pedidos_detalles
    ADD CONSTRAINT pk_pedidos_detalles PRIMARY KEY (pid, artid);


--
-- TOC entry 1969 (class 2606 OID 75390)
-- Name: pk_usuarios; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT pk_usuarios PRIMARY KEY (uid);


--
-- TOC entry 1985 (class 2606 OID 75491)
-- Name: unq_articulos_artid; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY articulos
    ADD CONSTRAINT unq_articulos_artid UNIQUE (artid);


--
-- TOC entry 1971 (class 2606 OID 75392)
-- Name: unq_usuarios_email; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT unq_usuarios_email UNIQUE (email);


--
-- TOC entry 2001 (class 2620 OID 75555)
-- Name: trigger_crear_bienvenida; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER trigger_crear_bienvenida AFTER INSERT ON usuarios FOR EACH STATEMENT EXECUTE PROCEDURE crear_bienvenida();


SET search_path = chats, pg_catalog;

--
-- TOC entry 2000 (class 2606 OID 75536)
-- Name: fk_chats_mensajes_chid; Type: FK CONSTRAINT; Schema: chats; Owner: -
--

ALTER TABLE ONLY chats_mensajes
    ADD CONSTRAINT fk_chats_mensajes_chid FOREIGN KEY (chid) REFERENCES chats(chid);


SET search_path = public, pg_catalog;

--
-- TOC entry 1995 (class 2606 OID 75421)
-- Name: fk_activaciones_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY activaciones
    ADD CONSTRAINT fk_activaciones_uid FOREIGN KEY (uid) REFERENCES usuarios(uid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1994 (class 2606 OID 75397)
-- Name: fk_bienvenida_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY bienvenida
    ADD CONSTRAINT fk_bienvenida_uid FOREIGN KEY (uid) REFERENCES usuarios(uid);


--
-- TOC entry 1999 (class 2606 OID 75508)
-- Name: fk_categorias_supercat; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY categorias
    ADD CONSTRAINT fk_categorias_supercat FOREIGN KEY (catid) REFERENCES categorias(catid) NOT VALID;


--
-- TOC entry 1996 (class 2606 OID 75437)
-- Name: fk_direcciones_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY direcciones
    ADD CONSTRAINT fk_direcciones_uid FOREIGN KEY (uid) REFERENCES usuarios(uid);


--
-- TOC entry 1998 (class 2606 OID 75492)
-- Name: fk_pedidos_detalles_artid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos_detalles
    ADD CONSTRAINT fk_pedidos_detalles_artid FOREIGN KEY (artid) REFERENCES articulos(artid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1997 (class 2606 OID 75465)
-- Name: fk_pedidos_detalles_pid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos_detalles
    ADD CONSTRAINT fk_pedidos_detalles_pid FOREIGN KEY (pid) REFERENCES pedidos(pid);


-- Completed on 2016-04-21 17:00:26 CEST

--
-- PostgreSQL database dump complete
--

