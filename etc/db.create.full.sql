--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

-- Started on 2016-06-12 12:42:16 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 16387)
-- Name: chats; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA chats;


--
-- TOC entry 9 (class 2615 OID 16388)
-- Name: private; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA private;


--
-- TOC entry 1 (class 3079 OID 12361)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2311 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

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


SET search_path = chats, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 183 (class 1259 OID 16391)
-- Name: chats; Type: TABLE; Schema: chats; Owner: -
--

CREATE TABLE chats (
    chid bigint NOT NULL,
    nombre character varying NOT NULL,
    email character varying NOT NULL,
    asunto character varying NOT NULL,
    fecha timestamp without time zone NOT NULL,
    estado smallint NOT NULL,
    uuid character varying
);


--
-- TOC entry 184 (class 1259 OID 16397)
-- Name: chats_chid_seq; Type: SEQUENCE; Schema: chats; Owner: -
--

CREATE SEQUENCE chats_chid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2312 (class 0 OID 0)
-- Dependencies: 184
-- Name: chats_chid_seq; Type: SEQUENCE OWNED BY; Schema: chats; Owner: -
--

ALTER SEQUENCE chats_chid_seq OWNED BY chats.chid;


--
-- TOC entry 185 (class 1259 OID 16399)
-- Name: chats_mensajes; Type: TABLE; Schema: chats; Owner: -
--

CREATE TABLE chats_mensajes (
    chmid bigint NOT NULL,
    chid bigint NOT NULL,
    es_usuario boolean NOT NULL,
    mensaje character varying NOT NULL,
    fecha timestamp without time zone NOT NULL
);


--
-- TOC entry 186 (class 1259 OID 16405)
-- Name: chats_mensajes_chmid_seq; Type: SEQUENCE; Schema: chats; Owner: -
--

CREATE SEQUENCE chats_mensajes_chmid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 186
-- Name: chats_mensajes_chmid_seq; Type: SEQUENCE OWNED BY; Schema: chats; Owner: -
--

ALTER SEQUENCE chats_mensajes_chmid_seq OWNED BY chats_mensajes.chmid;


SET search_path = private, pg_catalog;

--
-- TOC entry 187 (class 1259 OID 16407)
-- Name: administradores; Type: TABLE; Schema: private; Owner: -
--

CREATE TABLE administradores (
    adminid bigint NOT NULL,
    nombre character varying NOT NULL,
    contrasena character varying NOT NULL
);


--
-- TOC entry 188 (class 1259 OID 16413)
-- Name: administradores_adminid_seq; Type: SEQUENCE; Schema: private; Owner: -
--

CREATE SEQUENCE administradores_adminid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 188
-- Name: administradores_adminid_seq; Type: SEQUENCE OWNED BY; Schema: private; Owner: -
--

ALTER SEQUENCE administradores_adminid_seq OWNED BY administradores.adminid;


--
-- TOC entry 189 (class 1259 OID 16415)
-- Name: bancos; Type: TABLE; Schema: private; Owner: -
--

CREATE TABLE bancos (
    bid bigint NOT NULL,
    nombre character varying,
    numero character varying NOT NULL,
    activo boolean DEFAULT true NOT NULL
);


--
-- TOC entry 190 (class 1259 OID 16422)
-- Name: bancos_bid_seq; Type: SEQUENCE; Schema: private; Owner: -
--

CREATE SEQUENCE bancos_bid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 190
-- Name: bancos_bid_seq; Type: SEQUENCE OWNED BY; Schema: private; Owner: -
--

ALTER SEQUENCE bancos_bid_seq OWNED BY bancos.bid;


--
-- TOC entry 191 (class 1259 OID 16424)
-- Name: configuracion; Type: TABLE; Schema: private; Owner: -
--

CREATE TABLE configuracion (
    parametro character varying NOT NULL,
    valor character varying NOT NULL
);


--
-- TOC entry 212 (class 1259 OID 16631)
-- Name: contacto; Type: TABLE; Schema: private; Owner: -
--

CREATE TABLE contacto (
    cid bigint NOT NULL,
    tipo smallint NOT NULL,
    numero character varying NOT NULL,
    mostrar boolean NOT NULL
);


--
-- TOC entry 211 (class 1259 OID 16629)
-- Name: contacto_cid_seq; Type: SEQUENCE; Schema: private; Owner: -
--

CREATE SEQUENCE contacto_cid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 211
-- Name: contacto_cid_seq; Type: SEQUENCE OWNED BY; Schema: private; Owner: -
--

ALTER SEQUENCE contacto_cid_seq OWNED BY contacto.cid;


SET search_path = public, pg_catalog;

--
-- TOC entry 192 (class 1259 OID 16430)
-- Name: activaciones; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE activaciones (
    aid bigint NOT NULL,
    uid bigint NOT NULL,
    clave character varying NOT NULL,
    fecha_limite timestamp without time zone NOT NULL
);


--
-- TOC entry 193 (class 1259 OID 16436)
-- Name: activaciones_aid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE activaciones_aid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 193
-- Name: activaciones_aid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE activaciones_aid_seq OWNED BY activaciones.aid;


--
-- TOC entry 194 (class 1259 OID 16438)
-- Name: articulos; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE articulos (
    artid bigint NOT NULL,
    catid bigint NOT NULL,
    nombre character varying NOT NULL,
    descripcion character varying NOT NULL,
    precio numeric NOT NULL,
    imagen character varying,
    stock integer,
    minimo integer,
    fecha_creacion time without time zone DEFAULT now(),
    destacado boolean DEFAULT false NOT NULL
);


--
-- TOC entry 195 (class 1259 OID 16446)
-- Name: articulos_artid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE articulos_artid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 195
-- Name: articulos_artid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE articulos_artid_seq OWNED BY articulos.artid;


--
-- TOC entry 196 (class 1259 OID 16448)
-- Name: avisos_stock; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE avisos_stock (
    artid bigint NOT NULL,
    avisado boolean,
    correo character varying NOT NULL
);


--
-- TOC entry 197 (class 1259 OID 16451)
-- Name: bienvenida; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE bienvenida (
    uid bigint NOT NULL,
    enviado boolean DEFAULT false NOT NULL,
    bid bigint NOT NULL
);


--
-- TOC entry 198 (class 1259 OID 16455)
-- Name: bienvenida_bid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE bienvenida_bid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2319 (class 0 OID 0)
-- Dependencies: 198
-- Name: bienvenida_bid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE bienvenida_bid_seq OWNED BY bienvenida.bid;


--
-- TOC entry 199 (class 1259 OID 16457)
-- Name: categorias; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE categorias (
    catid bigint NOT NULL,
    supercat bigint,
    nombre character varying NOT NULL,
    descripcion character varying NOT NULL
);


--
-- TOC entry 200 (class 1259 OID 16463)
-- Name: categorias_catid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE categorias_catid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 200
-- Name: categorias_catid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE categorias_catid_seq OWNED BY categorias.catid;


--
-- TOC entry 201 (class 1259 OID 16465)
-- Name: comentarios; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE comentarios (
    cid bigint NOT NULL,
    uid bigint NOT NULL,
    artid bigint NOT NULL,
    contenido character varying NOT NULL,
    fecha timestamp without time zone DEFAULT now() NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 16472)
-- Name: comentarios_cid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE comentarios_cid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 202
-- Name: comentarios_cid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE comentarios_cid_seq OWNED BY comentarios.cid;


--
-- TOC entry 203 (class 1259 OID 16474)
-- Name: direcciones; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE direcciones (
    did bigint NOT NULL,
    uid bigint NOT NULL,
    nombre character varying NOT NULL,
    direccion character varying NOT NULL,
    localidad character varying NOT NULL,
    codigo_postal character varying NOT NULL,
    telefono character varying NOT NULL
);


--
-- TOC entry 204 (class 1259 OID 16480)
-- Name: direcciones_did_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE direcciones_did_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 204
-- Name: direcciones_did_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE direcciones_did_seq OWNED BY direcciones.did;


--
-- TOC entry 205 (class 1259 OID 16482)
-- Name: pedidos; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE pedidos (
    pid bigint NOT NULL,
    fecha timestamp without time zone NOT NULL,
    estado smallint NOT NULL,
    importe numeric NOT NULL,
    forma_pago smallint NOT NULL,
    observaciones character varying,
    uid bigint NOT NULL,
    did bigint NOT NULL
);


--
-- TOC entry 206 (class 1259 OID 16488)
-- Name: pedidos_detalles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE pedidos_detalles (
    pid bigint NOT NULL,
    artid bigint NOT NULL,
    cantidad integer NOT NULL
);


--
-- TOC entry 207 (class 1259 OID 16491)
-- Name: pedidos_pid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE pedidos_pid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2323 (class 0 OID 0)
-- Dependencies: 207
-- Name: pedidos_pid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE pedidos_pid_seq OWNED BY pedidos.pid;


--
-- TOC entry 208 (class 1259 OID 16493)
-- Name: recordarme; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE recordarme (
    id character varying NOT NULL,
    token character varying NOT NULL,
    salt character varying,
    uid bigint NOT NULL
);


--
-- TOC entry 209 (class 1259 OID 16499)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE usuarios (
    uid bigint NOT NULL,
    correo character varying NOT NULL,
    contrasena character varying NOT NULL,
    nombre character varying NOT NULL,
    fecha_registro timestamp without time zone DEFAULT now() NOT NULL,
    fecha_conexion timestamp without time zone,
    direccion_ip inet,
    datos_compras integer[],
    activado boolean DEFAULT false NOT NULL
);


--
-- TOC entry 210 (class 1259 OID 16507)
-- Name: usuarios_uid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE usuarios_uid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 210
-- Name: usuarios_uid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE usuarios_uid_seq OWNED BY usuarios.uid;


SET search_path = chats, pg_catalog;

--
-- TOC entry 2092 (class 2604 OID 16509)
-- Name: chid; Type: DEFAULT; Schema: chats; Owner: -
--

ALTER TABLE ONLY chats ALTER COLUMN chid SET DEFAULT nextval('chats_chid_seq'::regclass);


--
-- TOC entry 2093 (class 2604 OID 16510)
-- Name: chmid; Type: DEFAULT; Schema: chats; Owner: -
--

ALTER TABLE ONLY chats_mensajes ALTER COLUMN chmid SET DEFAULT nextval('chats_mensajes_chmid_seq'::regclass);


SET search_path = private, pg_catalog;

--
-- TOC entry 2094 (class 2604 OID 16511)
-- Name: adminid; Type: DEFAULT; Schema: private; Owner: -
--

ALTER TABLE ONLY administradores ALTER COLUMN adminid SET DEFAULT nextval('administradores_adminid_seq'::regclass);


--
-- TOC entry 2096 (class 2604 OID 16512)
-- Name: bid; Type: DEFAULT; Schema: private; Owner: -
--

ALTER TABLE ONLY bancos ALTER COLUMN bid SET DEFAULT nextval('bancos_bid_seq'::regclass);


--
-- TOC entry 2111 (class 2604 OID 16634)
-- Name: cid; Type: DEFAULT; Schema: private; Owner: -
--

ALTER TABLE ONLY contacto ALTER COLUMN cid SET DEFAULT nextval('contacto_cid_seq'::regclass);


SET search_path = public, pg_catalog;

--
-- TOC entry 2097 (class 2604 OID 16513)
-- Name: aid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY activaciones ALTER COLUMN aid SET DEFAULT nextval('activaciones_aid_seq'::regclass);


--
-- TOC entry 2100 (class 2604 OID 16514)
-- Name: artid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY articulos ALTER COLUMN artid SET DEFAULT nextval('articulos_artid_seq'::regclass);


--
-- TOC entry 2102 (class 2604 OID 16515)
-- Name: bid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY bienvenida ALTER COLUMN bid SET DEFAULT nextval('bienvenida_bid_seq'::regclass);


--
-- TOC entry 2103 (class 2604 OID 16516)
-- Name: catid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY categorias ALTER COLUMN catid SET DEFAULT nextval('categorias_catid_seq'::regclass);


--
-- TOC entry 2104 (class 2604 OID 16517)
-- Name: cid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY comentarios ALTER COLUMN cid SET DEFAULT nextval('comentarios_cid_seq'::regclass);


--
-- TOC entry 2106 (class 2604 OID 16518)
-- Name: did; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY direcciones ALTER COLUMN did SET DEFAULT nextval('direcciones_did_seq'::regclass);


--
-- TOC entry 2107 (class 2604 OID 16519)
-- Name: pid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos ALTER COLUMN pid SET DEFAULT nextval('pedidos_pid_seq'::regclass);


--
-- TOC entry 2110 (class 2604 OID 16520)
-- Name: uid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY usuarios ALTER COLUMN uid SET DEFAULT nextval('usuarios_uid_seq'::regclass);


SET search_path = chats, pg_catalog;

--
-- TOC entry 2275 (class 0 OID 16391)
-- Dependencies: 183
-- Data for Name: chats; Type: TABLE DATA; Schema: chats; Owner: -
--

COPY chats (chid, nombre, email, asunto, fecha, estado, uuid) FROM stdin;
\.


--
-- TOC entry 2325 (class 0 OID 0)
-- Dependencies: 184
-- Name: chats_chid_seq; Type: SEQUENCE SET; Schema: chats; Owner: -
--

SELECT pg_catalog.setval('chats_chid_seq', 1, false);


--
-- TOC entry 2277 (class 0 OID 16399)
-- Dependencies: 185
-- Data for Name: chats_mensajes; Type: TABLE DATA; Schema: chats; Owner: -
--

COPY chats_mensajes (chmid, chid, es_usuario, mensaje, fecha) FROM stdin;
\.


--
-- TOC entry 2326 (class 0 OID 0)
-- Dependencies: 186
-- Name: chats_mensajes_chmid_seq; Type: SEQUENCE SET; Schema: chats; Owner: -
--

SELECT pg_catalog.setval('chats_mensajes_chmid_seq', 1, false);


SET search_path = private, pg_catalog;

--
-- TOC entry 2279 (class 0 OID 16407)
-- Dependencies: 187
-- Data for Name: administradores; Type: TABLE DATA; Schema: private; Owner: -
--

COPY administradores (adminid, nombre, contrasena) FROM stdin;
1	jesus	jesus
\.


--
-- TOC entry 2327 (class 0 OID 0)
-- Dependencies: 188
-- Name: administradores_adminid_seq; Type: SEQUENCE SET; Schema: private; Owner: -
--

SELECT pg_catalog.setval('administradores_adminid_seq', 1, true);


--
-- TOC entry 2281 (class 0 OID 16415)
-- Dependencies: 189
-- Data for Name: bancos; Type: TABLE DATA; Schema: private; Owner: -
--

COPY bancos (bid, nombre, numero, activo) FROM stdin;
3	BBVA	11455881428845688658	t
4	Banco Santander	451154458745878458	t
5	ING	4247892918189181991	t
10	Banco Popular de España	1000 2000 3000 4000	f
\.


--
-- TOC entry 2328 (class 0 OID 0)
-- Dependencies: 190
-- Name: bancos_bid_seq; Type: SEQUENCE SET; Schema: private; Owner: -
--

SELECT pg_catalog.setval('bancos_bid_seq', 10, true);


--
-- TOC entry 2283 (class 0 OID 16424)
-- Dependencies: 191
-- Data for Name: configuracion; Type: TABLE DATA; Schema: private; Owner: -
--

COPY configuracion (parametro, valor) FROM stdin;
NumElementosPorPagina	3
NumItemsGestionPedidos	20
\.


--
-- TOC entry 2304 (class 0 OID 16631)
-- Dependencies: 212
-- Data for Name: contacto; Type: TABLE DATA; Schema: private; Owner: -
--

COPY contacto (cid, tipo, numero, mostrar) FROM stdin;
\.


--
-- TOC entry 2329 (class 0 OID 0)
-- Dependencies: 211
-- Name: contacto_cid_seq; Type: SEQUENCE SET; Schema: private; Owner: -
--

SELECT pg_catalog.setval('contacto_cid_seq', 1, false);


SET search_path = public, pg_catalog;

--
-- TOC entry 2284 (class 0 OID 16430)
-- Dependencies: 192
-- Data for Name: activaciones; Type: TABLE DATA; Schema: public; Owner: -
--

COPY activaciones (aid, uid, clave, fecha_limite) FROM stdin;
\.


--
-- TOC entry 2330 (class 0 OID 0)
-- Dependencies: 193
-- Name: activaciones_aid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('activaciones_aid_seq', 4, true);


--
-- TOC entry 2286 (class 0 OID 16438)
-- Dependencies: 194
-- Data for Name: articulos; Type: TABLE DATA; Schema: public; Owner: -
--

COPY articulos (artid, catid, nombre, descripcion, precio, imagen, stock, minimo, fecha_creacion, destacado) FROM stdin;
1	5	Alfalfa secada al sol	Alfalfa de primerísima calidad para sus animales. Se vende por kg.	0.45	alfalfa-packs.jpg	5455	100	20:22:21.374747	f
2	4	Abono para pipas de girasol	Abono para sus cultivos de pipas de girasol	4.55	girasol.jpg	400	10	20:22:21.374747	f
3	3	Recogida de alfalfa	Recogida profesional de su cultivo, así como empaquetado y transporte.	4.10	tractor-img.jpg	\N	\N	20:22:21.374747	f
4	5	Alfalfa para Ganado	Alfalfa de primera calidad para su ganado	0.65	alfalfa-packs.jpg	1575	10	18:13:13.100687	t
\.


--
-- TOC entry 2331 (class 0 OID 0)
-- Dependencies: 195
-- Name: articulos_artid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('articulos_artid_seq', 4, true);


--
-- TOC entry 2288 (class 0 OID 16448)
-- Dependencies: 196
-- Data for Name: avisos_stock; Type: TABLE DATA; Schema: public; Owner: -
--

COPY avisos_stock (artid, avisado, correo) FROM stdin;
3	\N	hola@mundo.com
\.


--
-- TOC entry 2289 (class 0 OID 16451)
-- Dependencies: 197
-- Data for Name: bienvenida; Type: TABLE DATA; Schema: public; Owner: -
--

COPY bienvenida (uid, enviado, bid) FROM stdin;
30	f	29
\.


--
-- TOC entry 2332 (class 0 OID 0)
-- Dependencies: 198
-- Name: bienvenida_bid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('bienvenida_bid_seq', 29, true);


--
-- TOC entry 2291 (class 0 OID 16457)
-- Dependencies: 199
-- Data for Name: categorias; Type: TABLE DATA; Schema: public; Owner: -
--

COPY categorias (catid, supercat, nombre, descripcion) FROM stdin;
1	\N	CATEGORÍA RAÍZ	CATEGORÍA RAÍZ
2	1	Productos Agrícolas	Selección de productos agrícolas de alta calidad
3	1	Servicios Agrícolas	Servicios agrícolas al mejor precio del mercado
4	2	Abonos	Abonos para sus tierras
5	2	Alfalfa	Alimento para los animales
\.


--
-- TOC entry 2333 (class 0 OID 0)
-- Dependencies: 200
-- Name: categorias_catid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('categorias_catid_seq', 5, true);


--
-- TOC entry 2293 (class 0 OID 16465)
-- Dependencies: 201
-- Data for Name: comentarios; Type: TABLE DATA; Schema: public; Owner: -
--

COPY comentarios (cid, uid, artid, contenido, fecha) FROM stdin;
3	-1	4	Muy buen producto!	2016-06-10 00:59:56.071
4	-1	2	Buen producto	2016-06-11 19:15:22.817
\.


--
-- TOC entry 2334 (class 0 OID 0)
-- Dependencies: 202
-- Name: comentarios_cid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('comentarios_cid_seq', 4, true);


--
-- TOC entry 2295 (class 0 OID 16474)
-- Dependencies: 203
-- Data for Name: direcciones; Type: TABLE DATA; Schema: public; Owner: -
--

COPY direcciones (did, uid, nombre, direccion, localidad, codigo_postal, telefono) FROM stdin;
34	30	angular	angularjs	angularjs.city	101001	blabla
35	30	angular2	holamundo	holahol	298191	hola
\.


--
-- TOC entry 2335 (class 0 OID 0)
-- Dependencies: 204
-- Name: direcciones_did_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('direcciones_did_seq', 35, true);


--
-- TOC entry 2297 (class 0 OID 16482)
-- Dependencies: 205
-- Data for Name: pedidos; Type: TABLE DATA; Schema: public; Owner: -
--

COPY pedidos (pid, fecha, estado, importe, forma_pago, observaciones, uid, did) FROM stdin;
14	2016-05-22 13:04:30.627	1	4.55	4		30	35
15	2016-05-22 20:26:25.266	1	9.10	1		30	35
16	2016-05-22 20:35:09.293	1	9.10	0		30	35
17	2016-06-06 12:40:07.58	1	32.85	2		30	35
18	2016-06-07 23:33:33.26	1	32.80	2		30	34
19	2016-06-07 23:33:33.267	1	32.80	2		30	34
20	2016-06-11 19:20:58.756	1	22.75	2		30	34
\.


--
-- TOC entry 2298 (class 0 OID 16488)
-- Dependencies: 206
-- Data for Name: pedidos_detalles; Type: TABLE DATA; Schema: public; Owner: -
--

COPY pedidos_detalles (pid, artid, cantidad) FROM stdin;
14	2	1
15	2	2
16	2	2
17	1	73
19	3	8
18	3	8
20	2	5
\.


--
-- TOC entry 2336 (class 0 OID 0)
-- Dependencies: 207
-- Name: pedidos_pid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('pedidos_pid_seq', 20, true);


--
-- TOC entry 2300 (class 0 OID 16493)
-- Dependencies: 208
-- Data for Name: recordarme; Type: TABLE DATA; Schema: public; Owner: -
--

COPY recordarme (id, token, salt, uid) FROM stdin;
\.


--
-- TOC entry 2301 (class 0 OID 16499)
-- Dependencies: 209
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: -
--

COPY usuarios (uid, correo, contrasena, nombre, fecha_registro, fecha_conexion, direccion_ip, datos_compras, activado) FROM stdin;
30	jesus92gz@gmail.com	123123123	Jesus Gonzalez	2016-05-22 11:39:06.156	2016-06-11 19:37:47.277	::1	\N	t
\.


--
-- TOC entry 2337 (class 0 OID 0)
-- Dependencies: 210
-- Name: usuarios_uid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('usuarios_uid_seq', 30, true);


SET search_path = chats, pg_catalog;

--
-- TOC entry 2113 (class 2606 OID 16522)
-- Name: pk_chats; Type: CONSTRAINT; Schema: chats; Owner: -
--

ALTER TABLE ONLY chats
    ADD CONSTRAINT pk_chats PRIMARY KEY (chid);


--
-- TOC entry 2115 (class 2606 OID 16524)
-- Name: pk_chats_mensajes; Type: CONSTRAINT; Schema: chats; Owner: -
--

ALTER TABLE ONLY chats_mensajes
    ADD CONSTRAINT pk_chats_mensajes PRIMARY KEY (chmid, chid);


SET search_path = private, pg_catalog;

--
-- TOC entry 2117 (class 2606 OID 16526)
-- Name: pk_administradores; Type: CONSTRAINT; Schema: private; Owner: -
--

ALTER TABLE ONLY administradores
    ADD CONSTRAINT pk_administradores PRIMARY KEY (adminid);


--
-- TOC entry 2119 (class 2606 OID 16528)
-- Name: pk_bancos; Type: CONSTRAINT; Schema: private; Owner: -
--

ALTER TABLE ONLY bancos
    ADD CONSTRAINT pk_bancos PRIMARY KEY (bid, numero);


--
-- TOC entry 2121 (class 2606 OID 16530)
-- Name: pk_configuracion; Type: CONSTRAINT; Schema: private; Owner: -
--

ALTER TABLE ONLY configuracion
    ADD CONSTRAINT pk_configuracion PRIMARY KEY (parametro);


--
-- TOC entry 2147 (class 2606 OID 16639)
-- Name: pk_contacto; Type: CONSTRAINT; Schema: private; Owner: -
--

ALTER TABLE ONLY contacto
    ADD CONSTRAINT pk_contacto PRIMARY KEY (cid);


SET search_path = public, pg_catalog;

--
-- TOC entry 2123 (class 2606 OID 16532)
-- Name: pk_activaciones; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY activaciones
    ADD CONSTRAINT pk_activaciones PRIMARY KEY (aid, uid, clave);


--
-- TOC entry 2127 (class 2606 OID 16536)
-- Name: pk_bienvenida; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY bienvenida
    ADD CONSTRAINT pk_bienvenida PRIMARY KEY (uid, bid);


--
-- TOC entry 2129 (class 2606 OID 16538)
-- Name: pk_categorias; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY categorias
    ADD CONSTRAINT pk_categorias PRIMARY KEY (catid);


--
-- TOC entry 2131 (class 2606 OID 16540)
-- Name: pk_comentarios; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY comentarios
    ADD CONSTRAINT pk_comentarios PRIMARY KEY (cid);


--
-- TOC entry 2133 (class 2606 OID 16542)
-- Name: pk_direcciones; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY direcciones
    ADD CONSTRAINT pk_direcciones PRIMARY KEY (did, uid);


--
-- TOC entry 2137 (class 2606 OID 16544)
-- Name: pk_pedidos; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos
    ADD CONSTRAINT pk_pedidos PRIMARY KEY (pid);


--
-- TOC entry 2139 (class 2606 OID 16546)
-- Name: pk_pedidos_detalles; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos_detalles
    ADD CONSTRAINT pk_pedidos_detalles PRIMARY KEY (pid, artid);


--
-- TOC entry 2141 (class 2606 OID 16548)
-- Name: pk_recordarme; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recordarme
    ADD CONSTRAINT pk_recordarme PRIMARY KEY (id, uid);


--
-- TOC entry 2143 (class 2606 OID 16550)
-- Name: pk_usuarios; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT pk_usuarios PRIMARY KEY (uid);


--
-- TOC entry 2125 (class 2606 OID 16552)
-- Name: unq_articulos_artid; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY articulos
    ADD CONSTRAINT unq_articulos_artid UNIQUE (artid);


--
-- TOC entry 2145 (class 2606 OID 16554)
-- Name: unq_usuarios_email; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT unq_usuarios_email UNIQUE (correo);


--
-- TOC entry 2134 (class 1259 OID 16555)
-- Name: fki_pedidos_direcciones; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_pedidos_direcciones ON pedidos USING btree (did, uid);


--
-- TOC entry 2135 (class 1259 OID 16556)
-- Name: fki_pedidos_usuarios; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_pedidos_usuarios ON pedidos USING btree (uid);


--
-- TOC entry 2160 (class 2620 OID 16557)
-- Name: trigger_crear_bienvenida; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER trigger_crear_bienvenida AFTER INSERT ON usuarios FOR EACH ROW EXECUTE PROCEDURE crear_bienvenida();


SET search_path = chats, pg_catalog;

--
-- TOC entry 2148 (class 2606 OID 16558)
-- Name: fk_chats_mensajes_chid; Type: FK CONSTRAINT; Schema: chats; Owner: -
--

ALTER TABLE ONLY chats_mensajes
    ADD CONSTRAINT fk_chats_mensajes_chid FOREIGN KEY (chid) REFERENCES chats(chid);


SET search_path = public, pg_catalog;

--
-- TOC entry 2149 (class 2606 OID 16563)
-- Name: fk_activaciones_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY activaciones
    ADD CONSTRAINT fk_activaciones_uid FOREIGN KEY (uid) REFERENCES usuarios(uid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2150 (class 2606 OID 16568)
-- Name: fk_avisos_stock_articulos; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY avisos_stock
    ADD CONSTRAINT fk_avisos_stock_articulos FOREIGN KEY (artid) REFERENCES articulos(artid);


--
-- TOC entry 2151 (class 2606 OID 16578)
-- Name: fk_bienvenida_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY bienvenida
    ADD CONSTRAINT fk_bienvenida_uid FOREIGN KEY (uid) REFERENCES usuarios(uid);


--
-- TOC entry 2152 (class 2606 OID 16583)
-- Name: fk_categorias_supercat; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY categorias
    ADD CONSTRAINT fk_categorias_supercat FOREIGN KEY (catid) REFERENCES categorias(catid) NOT VALID;


--
-- TOC entry 2153 (class 2606 OID 16588)
-- Name: fk_comentarios_artid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY comentarios
    ADD CONSTRAINT fk_comentarios_artid FOREIGN KEY (artid) REFERENCES articulos(artid);


--
-- TOC entry 2154 (class 2606 OID 16598)
-- Name: fk_direcciones_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY direcciones
    ADD CONSTRAINT fk_direcciones_uid FOREIGN KEY (uid) REFERENCES usuarios(uid);


--
-- TOC entry 2157 (class 2606 OID 16603)
-- Name: fk_pedidos_detalles_artid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos_detalles
    ADD CONSTRAINT fk_pedidos_detalles_artid FOREIGN KEY (artid) REFERENCES articulos(artid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2158 (class 2606 OID 16608)
-- Name: fk_pedidos_detalles_pid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos_detalles
    ADD CONSTRAINT fk_pedidos_detalles_pid FOREIGN KEY (pid) REFERENCES pedidos(pid);


--
-- TOC entry 2155 (class 2606 OID 16613)
-- Name: fk_pedidos_direcciones; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos
    ADD CONSTRAINT fk_pedidos_direcciones FOREIGN KEY (did, uid) REFERENCES direcciones(did, uid);


--
-- TOC entry 2156 (class 2606 OID 16618)
-- Name: fk_pedidos_usuarios; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY pedidos
    ADD CONSTRAINT fk_pedidos_usuarios FOREIGN KEY (uid) REFERENCES usuarios(uid);


--
-- TOC entry 2159 (class 2606 OID 16623)
-- Name: fk_recordarme_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recordarme
    ADD CONSTRAINT fk_recordarme_uid FOREIGN KEY (uid) REFERENCES usuarios(uid);


-- Completed on 2016-06-12 12:42:18 CEST

--
-- PostgreSQL database dump complete
--

