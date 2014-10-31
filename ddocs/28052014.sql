--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.4
-- Dumped by pg_dump version 9.1.4
-- Started on 2014-05-28 00:23:27

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 169 (class 3079 OID 11639)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1892 (class 0 OID 0)
-- Dependencies: 169
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 162 (class 1259 OID 25963)
-- Dependencies: 1867 1868 5
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE categoria (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    descricao character varying(300),
    anonimato boolean DEFAULT true NOT NULL,
    idpai integer,
    isvalid boolean DEFAULT false
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 161 (class 1259 OID 25961)
-- Dependencies: 5 162
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_id_seq OWNER TO postgres;

--
-- TOC entry 1893 (class 0 OID 0)
-- Dependencies: 161
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categoria_id_seq OWNED BY categoria.id;


--
-- TOC entry 1894 (class 0 OID 0)
-- Dependencies: 161
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_id_seq', 15, true);


--
-- TOC entry 166 (class 1259 OID 25985)
-- Dependencies: 5
-- Name: cidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cidade (
    id integer NOT NULL,
    nome character varying(100),
    idestado integer
);


ALTER TABLE public.cidade OWNER TO postgres;

--
-- TOC entry 165 (class 1259 OID 25983)
-- Dependencies: 166 5
-- Name: cidade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cidade_id_seq OWNER TO postgres;

--
-- TOC entry 1895 (class 0 OID 0)
-- Dependencies: 165
-- Name: cidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cidade_id_seq OWNED BY cidade.id;


--
-- TOC entry 1896 (class 0 OID 0)
-- Dependencies: 165
-- Name: cidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cidade_id_seq', 1, false);


--
-- TOC entry 168 (class 1259 OID 26008)
-- Dependencies: 5
-- Name: denuncia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE denuncia (
    id integer NOT NULL,
    idcategoria integer,
    data_realizacao date,
    descricao character varying(300)
);


ALTER TABLE public.denuncia OWNER TO postgres;

--
-- TOC entry 167 (class 1259 OID 26006)
-- Dependencies: 5 168
-- Name: denuncia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE denuncia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.denuncia_id_seq OWNER TO postgres;

--
-- TOC entry 1897 (class 0 OID 0)
-- Dependencies: 167
-- Name: denuncia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE denuncia_id_seq OWNED BY denuncia.id;


--
-- TOC entry 1898 (class 0 OID 0)
-- Dependencies: 167
-- Name: denuncia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('denuncia_id_seq', 1, true);


--
-- TOC entry 164 (class 1259 OID 25977)
-- Dependencies: 5
-- Name: estado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estado (
    id integer NOT NULL,
    nome character varying(100),
    sigla character varying(5)
);


ALTER TABLE public.estado OWNER TO postgres;

--
-- TOC entry 163 (class 1259 OID 25975)
-- Dependencies: 5 164
-- Name: estado_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE estado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_id_seq OWNER TO postgres;

--
-- TOC entry 1899 (class 0 OID 0)
-- Dependencies: 163
-- Name: estado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE estado_id_seq OWNED BY estado.id;


--
-- TOC entry 1900 (class 0 OID 0)
-- Dependencies: 163
-- Name: estado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('estado_id_seq', 1, false);


--
-- TOC entry 1866 (class 2604 OID 25966)
-- Dependencies: 161 162 162
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria ALTER COLUMN id SET DEFAULT nextval('categoria_id_seq'::regclass);


--
-- TOC entry 1870 (class 2604 OID 25988)
-- Dependencies: 165 166 166
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade ALTER COLUMN id SET DEFAULT nextval('cidade_id_seq'::regclass);


--
-- TOC entry 1871 (class 2604 OID 26011)
-- Dependencies: 167 168 168
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY denuncia ALTER COLUMN id SET DEFAULT nextval('denuncia_id_seq'::regclass);


--
-- TOC entry 1869 (class 2604 OID 25980)
-- Dependencies: 164 163 164
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado ALTER COLUMN id SET DEFAULT nextval('estado_id_seq'::regclass);


--
-- TOC entry 1883 (class 0 OID 25963)
-- Dependencies: 162
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO categoria VALUES (752, 'emse', 'ajgjgjp', false, 2, false);
INSERT INTO categoria VALUES (2, 'Educação', 'Educação de qualidade é um direito de todos', true, NULL, true);
INSERT INTO categoria VALUES (5, 'Mau trato contra animais', 'Mau trato contra animais', true, NULL, true);
INSERT INTO categoria VALUES (4, 'Transporte', 'Estacionamento, condiçoes da via, preços', true, NULL, true);
INSERT INTO categoria VALUES (3, 'Estabelecimentos', 'Área destinada a denuncias de abuso de preços, falta de qualidade de produtos e serviços ', true, NULL, true);
INSERT INTO categoria VALUES (1, 'Violência', 'Area destinada a denuncia de violência relacionada a terceiros e animais', true, NULL, true);


--
-- TOC entry 1885 (class 0 OID 25985)
-- Dependencies: 166
-- Data for Name: cidade; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1886 (class 0 OID 26008)
-- Dependencies: 168
-- Data for Name: denuncia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO denuncia VALUES (50, 4, NULL, 'asdasdasdas');
INSERT INTO denuncia VALUES (51, 3, NULL, 'Buraco na via');


--
-- TOC entry 1884 (class 0 OID 25977)
-- Dependencies: 164
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1873 (class 2606 OID 25969)
-- Dependencies: 162 162
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- TOC entry 1877 (class 2606 OID 25990)
-- Dependencies: 166 166
-- Name: cidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);


--
-- TOC entry 1879 (class 2606 OID 26013)
-- Dependencies: 168 168
-- Name: denuncia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY denuncia
    ADD CONSTRAINT denuncia_pkey PRIMARY KEY (id);


--
-- TOC entry 1875 (class 2606 OID 25982)
-- Dependencies: 164 164
-- Name: estado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);


--
-- TOC entry 1880 (class 2606 OID 25996)
-- Dependencies: 162 1872 162
-- Name: categoria_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_categoria_fkey FOREIGN KEY (idpai) REFERENCES categoria(id);


--
-- TOC entry 1881 (class 2606 OID 25991)
-- Dependencies: 166 164 1874
-- Name: cidade_estado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_estado_fkey FOREIGN KEY (idestado) REFERENCES estado(id);


--
-- TOC entry 1882 (class 2606 OID 26024)
-- Dependencies: 1872 162 168
-- Name: denuncia_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY denuncia
    ADD CONSTRAINT denuncia_categoria_fkey FOREIGN KEY (idcategoria) REFERENCES categoria(id);


--
-- TOC entry 1891 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-05-28 00:23:27

--
-- PostgreSQL database dump complete
--

