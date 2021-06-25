--
-- PostgreSQL database dump
--

-- Dumped from database version 10.17 (Ubuntu 10.17-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.17 (Ubuntu 10.17-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: tipologiacentro; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.tipologiacentro AS ENUM (
    'ospedaliero',
    'aziendale',
    'hub'
);


ALTER TYPE public.tipologiacentro OWNER TO postgres;

--
-- Name: vaccinosomministrato; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.vaccinosomministrato AS ENUM (
    'Pfizer',
    'AstraZeneca',
    'Moderna',
    'J&J'
);


ALTER TYPE public.vaccinosomministrato OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: centrivaccinali; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.centrivaccinali (
    idcentro integer NOT NULL,
    nome character varying(30) NOT NULL,
    indirizzo character varying(100) NOT NULL,
    tipologia public.tipologiacentro NOT NULL
);


ALTER TABLE public.centrivaccinali OWNER TO postgres;

--
-- Name: centrivaccinali_idcentro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.centrivaccinali_idcentro_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.centrivaccinali_idcentro_seq OWNER TO postgres;

--
-- Name: centrivaccinali_idcentro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.centrivaccinali_idcentro_seq OWNED BY public.centrivaccinali.idcentro;


--
-- Name: cittadiniregistrati; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cittadiniregistrati (
    idutente integer NOT NULL,
    nome character varying(30) NOT NULL,
    cognome character varying(30) NOT NULL,
    email character varying(30) NOT NULL,
    password character(64) NOT NULL,
    idvaccinazione integer,
    cf character(16) NOT NULL,
    idcentro integer NOT NULL
);


ALTER TABLE public.cittadiniregistrati OWNER TO postgres;

--
-- Name: cittadiniregistrati_idutente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cittadiniregistrati_idutente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cittadiniregistrati_idutente_seq OWNER TO postgres;

--
-- Name: cittadiniregistrati_idutente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cittadiniregistrati_idutente_seq OWNED BY public.cittadiniregistrati.idutente;


--
-- Name: centrivaccinali idcentro; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.centrivaccinali ALTER COLUMN idcentro SET DEFAULT nextval('public.centrivaccinali_idcentro_seq'::regclass);


--
-- Name: cittadiniregistrati idutente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cittadiniregistrati ALTER COLUMN idutente SET DEFAULT nextval('public.cittadiniregistrati_idutente_seq'::regclass);


--
-- Data for Name: centrivaccinali; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.centrivaccinali (idcentro, nome, indirizzo, tipologia) FROM stdin;
\.


--
-- Data for Name: cittadiniregistrati; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cittadiniregistrati (idutente, nome, cognome, email, password, idvaccinazione, cf, idcentro) FROM stdin;
\.


--
-- Name: centrivaccinali_idcentro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.centrivaccinali_idcentro_seq', 1, false);


--
-- Name: cittadiniregistrati_idutente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cittadiniregistrati_idutente_seq', 1, false);


--
-- Name: centrivaccinali centrivaccinali_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.centrivaccinali
    ADD CONSTRAINT centrivaccinali_pkey PRIMARY KEY (idcentro);


--
-- Name: cittadiniregistrati cittadiniregistrati_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cittadiniregistrati
    ADD CONSTRAINT cittadiniregistrati_pkey PRIMARY KEY (idutente);


--
-- Name: cittadiniregistrati cittadiniregistrati_idcentro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cittadiniregistrati
    ADD CONSTRAINT cittadiniregistrati_idcentro_fkey FOREIGN KEY (idcentro) REFERENCES public.centrivaccinali(idcentro);


--
-- PostgreSQL database dump complete
--

