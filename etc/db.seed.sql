--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

-- Started on 2016-06-12 12:48:31 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = chats, pg_catalog;

--
-- TOC entry 2275 (class 0 OID 16391)
-- Dependencies: 183
-- Data for Name: chats; Type: TABLE DATA; Schema: chats; Owner: commerce
--

COPY chats (chid, nombre, email, asunto, fecha, estado, uuid) FROM stdin;
\.


--
-- TOC entry 2309 (class 0 OID 0)
-- Dependencies: 184
-- Name: chats_chid_seq; Type: SEQUENCE SET; Schema: chats; Owner: commerce
--

SELECT pg_catalog.setval('chats_chid_seq', 1, false);


--
-- TOC entry 2277 (class 0 OID 16399)
-- Dependencies: 185
-- Data for Name: chats_mensajes; Type: TABLE DATA; Schema: chats; Owner: commerce
--

COPY chats_mensajes (chmid, chid, es_usuario, mensaje, fecha) FROM stdin;
\.


--
-- TOC entry 2310 (class 0 OID 0)
-- Dependencies: 186
-- Name: chats_mensajes_chmid_seq; Type: SEQUENCE SET; Schema: chats; Owner: commerce
--

SELECT pg_catalog.setval('chats_mensajes_chmid_seq', 1, false);


SET search_path = private, pg_catalog;

--
-- TOC entry 2279 (class 0 OID 16407)
-- Dependencies: 187
-- Data for Name: administradores; Type: TABLE DATA; Schema: private; Owner: commerce
--

COPY administradores (adminid, nombre, contrasena) FROM stdin;
1	jesus	jesus
\.


--
-- TOC entry 2311 (class 0 OID 0)
-- Dependencies: 188
-- Name: administradores_adminid_seq; Type: SEQUENCE SET; Schema: private; Owner: commerce
--

SELECT pg_catalog.setval('administradores_adminid_seq', 1, true);


--
-- TOC entry 2281 (class 0 OID 16415)
-- Dependencies: 189
-- Data for Name: bancos; Type: TABLE DATA; Schema: private; Owner: commerce
--

COPY bancos (bid, nombre, numero, activo) FROM stdin;
3	BBVA	11455881428845688658	t
4	Banco Santander	451154458745878458	t
5	ING	4247892918189181991	t
10	Banco Popular de España	1000 2000 3000 4000	f
\.


--
-- TOC entry 2312 (class 0 OID 0)
-- Dependencies: 190
-- Name: bancos_bid_seq; Type: SEQUENCE SET; Schema: private; Owner: commerce
--

SELECT pg_catalog.setval('bancos_bid_seq', 10, true);


--
-- TOC entry 2283 (class 0 OID 16424)
-- Dependencies: 191
-- Data for Name: configuracion; Type: TABLE DATA; Schema: private; Owner: commerce
--

COPY configuracion (parametro, valor) FROM stdin;
NumElementosPorPagina	3
NumItemsGestionPedidos	20
\.


--
-- TOC entry 2304 (class 0 OID 16631)
-- Dependencies: 212
-- Data for Name: contacto; Type: TABLE DATA; Schema: private; Owner: commerce
--

COPY contacto (cid, tipo, numero, mostrar) FROM stdin;
\.


--
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 211
-- Name: contacto_cid_seq; Type: SEQUENCE SET; Schema: private; Owner: commerce
--

SELECT pg_catalog.setval('contacto_cid_seq', 1, false);


SET search_path = public, pg_catalog;

--
-- TOC entry 2301 (class 0 OID 16499)
-- Dependencies: 209
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: commerce
--

COPY usuarios (uid, correo, contrasena, nombre, fecha_registro, fecha_conexion, direccion_ip, datos_compras, activado) FROM stdin;
30	jesus92gz@gmail.com	123123123	Jesus Gonzalez	2016-05-22 11:39:06.156	2016-06-11 19:37:47.277	::1	\N	t
\.


--
-- TOC entry 2284 (class 0 OID 16430)
-- Dependencies: 192
-- Data for Name: activaciones; Type: TABLE DATA; Schema: public; Owner: commerce
--

COPY activaciones (aid, uid, clave, fecha_limite) FROM stdin;
\.


--
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 193
-- Name: activaciones_aid_seq; Type: SEQUENCE SET; Schema: public; Owner: commerce
--

SELECT pg_catalog.setval('activaciones_aid_seq', 4, true);


--
-- TOC entry 2286 (class 0 OID 16438)
-- Dependencies: 194
-- Data for Name: articulos; Type: TABLE DATA; Schema: public; Owner: commerce
--

COPY articulos (artid, catid, nombre, descripcion, precio, imagen, stock, minimo, fecha_creacion, destacado) FROM stdin;
1	5	Alfalfa secada al sol	Alfalfa de primerísima calidad para sus animales. Se vende por kg.	0.45	alfalfa-packs.jpg	5455	100	20:22:21.374747	f
2	4	Abono para pipas de girasol	Abono para sus cultivos de pipas de girasol	4.55	girasol.jpg	400	10	20:22:21.374747	f
3	3	Recogida de alfalfa	Recogida profesional de su cultivo, así como empaquetado y transporte.	4.10	tractor-img.jpg	\N	\N	20:22:21.374747	f
4	5	Alfalfa para Ganado	Alfalfa de primera calidad para su ganado	0.65	alfalfa-packs.jpg	1575	10	18:13:13.100687	t
\.


--
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 195
-- Name: articulos_artid_seq; Type: SEQUENCE SET; Schema: public; Owner: commerce
--

SELECT pg_catalog.setval('articulos_artid_seq', 4, true);


--
-- TOC entry 2288 (class 0 OID 16448)
-- Dependencies: 196
-- Data for Name: avisos_stock; Type: TABLE DATA; Schema: public; Owner: commerce
--

COPY avisos_stock (artid, avisado, correo) FROM stdin;
3	\N	hola@mundo.com
\.


--
-- TOC entry 2289 (class 0 OID 16451)
-- Dependencies: 197
-- Data for Name: bienvenida; Type: TABLE DATA; Schema: public; Owner: commerce
--

COPY bienvenida (uid, enviado, bid) FROM stdin;
30	f	29
\.


--
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 198
-- Name: bienvenida_bid_seq; Type: SEQUENCE SET; Schema: public; Owner: commerce
--

SELECT pg_catalog.setval('bienvenida_bid_seq', 29, true);


--
-- TOC entry 2291 (class 0 OID 16457)
-- Dependencies: 199
-- Data for Name: categorias; Type: TABLE DATA; Schema: public; Owner: commerce
--

COPY categorias (catid, supercat, nombre, descripcion) FROM stdin;
1	\N	CATEGORÍA RAÍZ	CATEGORÍA RAÍZ
2	1	Productos Agrícolas	Selección de productos agrícolas de alta calidad
3	1	Servicios Agrícolas	Servicios agrícolas al mejor precio del mercado
4	2	Abonos	Abonos para sus tierras
5	2	Alfalfa	Alimento para los animales
\.


--
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 200
-- Name: categorias_catid_seq; Type: SEQUENCE SET; Schema: public; Owner: commerce
--

SELECT pg_catalog.setval('categorias_catid_seq', 5, true);


--
-- TOC entry 2293 (class 0 OID 16465)
-- Dependencies: 201
-- Data for Name: comentarios; Type: TABLE DATA; Schema: public; Owner: commerce
--

COPY comentarios (cid, uid, artid, contenido, fecha) FROM stdin;
3	-1	4	Muy buen producto!	2016-06-10 00:59:56.071
4	-1	2	Buen producto	2016-06-11 19:15:22.817
\.


--
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 202
-- Name: comentarios_cid_seq; Type: SEQUENCE SET; Schema: public; Owner: commerce
--

SELECT pg_catalog.setval('comentarios_cid_seq', 4, true);


--
-- TOC entry 2295 (class 0 OID 16474)
-- Dependencies: 203
-- Data for Name: direcciones; Type: TABLE DATA; Schema: public; Owner: commerce
--

COPY direcciones (did, uid, nombre, direccion, localidad, codigo_postal, telefono) FROM stdin;
34	30	angular	angularjs	angularjs.city	101001	blabla
35	30	angular2	holamundo	holahol	298191	hola
\.


--
-- TOC entry 2319 (class 0 OID 0)
-- Dependencies: 204
-- Name: direcciones_did_seq; Type: SEQUENCE SET; Schema: public; Owner: commerce
--

SELECT pg_catalog.setval('direcciones_did_seq', 35, true);


--
-- TOC entry 2297 (class 0 OID 16482)
-- Dependencies: 205
-- Data for Name: pedidos; Type: TABLE DATA; Schema: public; Owner: commerce
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
-- Data for Name: pedidos_detalles; Type: TABLE DATA; Schema: public; Owner: commerce
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
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 207
-- Name: pedidos_pid_seq; Type: SEQUENCE SET; Schema: public; Owner: commerce
--

SELECT pg_catalog.setval('pedidos_pid_seq', 20, true);


--
-- TOC entry 2300 (class 0 OID 16493)
-- Dependencies: 208
-- Data for Name: recordarme; Type: TABLE DATA; Schema: public; Owner: commerce
--

COPY recordarme (id, token, salt, uid) FROM stdin;
\.


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 210
-- Name: usuarios_uid_seq; Type: SEQUENCE SET; Schema: public; Owner: commerce
--

SELECT pg_catalog.setval('usuarios_uid_seq', 30, true);


-- Completed on 2016-06-12 12:48:32 CEST

--
-- PostgreSQL database dump complete
--

