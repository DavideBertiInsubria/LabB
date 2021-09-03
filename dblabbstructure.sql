--
-- PostgreSQL database dump
--

-- Dumped from database version 12.8 (Ubuntu 12.8-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.8 (Ubuntu 12.8-0ubuntu0.20.04.1)

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
-- Name: tipologiacentro; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.tipologiacentro AS ENUM (
    'ospedaliero',
    'aziendale',
    'hub'
);


ALTER TYPE public.tipologiacentro OWNER TO postgres;

--
-- Name: vaccino; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.vaccino AS ENUM (
    'Pfizer',
    'AstraZeneca',
    'Moderna',
    'JJ'
);


ALTER TYPE public.vaccino OWNER TO postgres;

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

SET default_table_access_method = heap;

--
-- Name: centrivaccinali; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.centrivaccinali (
    idcentro integer NOT NULL,
    nome character varying(60) NOT NULL,
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
    cf character(16) NOT NULL,
    idcentro integer NOT NULL,
    nick character varying(20) NOT NULL,
    idvaccinazione integer NOT NULL
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
-- Name: eventoavverso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.eventoavverso (
    idevento integer NOT NULL,
    evento character varying(50) NOT NULL
);


ALTER TABLE public.eventoavverso OWNER TO postgres;

--
-- Name: eventoavverso_idevento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.eventoavverso_idevento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.eventoavverso_idevento_seq OWNER TO postgres;

--
-- Name: eventoavverso_idevento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.eventoavverso_idevento_seq OWNED BY public.eventoavverso.idevento;


--
-- Name: segnalazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.segnalazione (
    idsegnalazione integer NOT NULL,
    idevento integer NOT NULL,
    severita integer NOT NULL,
    nota character varying(256),
    idvaccinazione integer NOT NULL
);


ALTER TABLE public.segnalazione OWNER TO postgres;

--
-- Name: segnalazione_idsegnalazione_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.segnalazione_idsegnalazione_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.segnalazione_idsegnalazione_seq OWNER TO postgres;

--
-- Name: segnalazione_idsegnalazione_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.segnalazione_idsegnalazione_seq OWNED BY public.segnalazione.idsegnalazione;


--
-- Name: vaccinati; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vaccinati (
    idcentro integer NOT NULL,
    nome character varying(30) NOT NULL,
    cognome character varying(30) NOT NULL,
    cf character(16) NOT NULL,
    datasomministrazione date NOT NULL,
    vaccinosomministrato public.vaccino,
    idvaccinazione integer NOT NULL,
    nomecentro character varying(60) NOT NULL
);


ALTER TABLE public.vaccinati OWNER TO postgres;

--
-- Name: vaccinati_idvaccinazione_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vaccinati_idvaccinazione_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vaccinati_idvaccinazione_seq OWNER TO postgres;

--
-- Name: vaccinati_idvaccinazione_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vaccinati_idvaccinazione_seq OWNED BY public.vaccinati.idvaccinazione;


--
-- Name: centrivaccinali idcentro; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.centrivaccinali ALTER COLUMN idcentro SET DEFAULT nextval('public.centrivaccinali_idcentro_seq'::regclass);


--
-- Name: cittadiniregistrati idutente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cittadiniregistrati ALTER COLUMN idutente SET DEFAULT nextval('public.cittadiniregistrati_idutente_seq'::regclass);


--
-- Name: eventoavverso idevento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eventoavverso ALTER COLUMN idevento SET DEFAULT nextval('public.eventoavverso_idevento_seq'::regclass);


--
-- Name: segnalazione idsegnalazione; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segnalazione ALTER COLUMN idsegnalazione SET DEFAULT nextval('public.segnalazione_idsegnalazione_seq'::regclass);


--
-- Name: vaccinati idvaccinazione; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccinati ALTER COLUMN idvaccinazione SET DEFAULT nextval('public.vaccinati_idvaccinazione_seq'::regclass);


--
-- Name: centrivaccinali centrivaccinali_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.centrivaccinali
    ADD CONSTRAINT centrivaccinali_pkey PRIMARY KEY (idcentro);


--
-- Name: cittadiniregistrati cittadiniregistrati_idvaccinazione_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cittadiniregistrati
    ADD CONSTRAINT cittadiniregistrati_idvaccinazione_key UNIQUE (idvaccinazione);


--
-- Name: cittadiniregistrati cittadiniregistrati_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cittadiniregistrati
    ADD CONSTRAINT cittadiniregistrati_pkey PRIMARY KEY (idutente);


--
-- Name: eventoavverso eventoavverso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eventoavverso
    ADD CONSTRAINT eventoavverso_pkey PRIMARY KEY (idevento);


--
-- Name: segnalazione segnalazione_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segnalazione
    ADD CONSTRAINT segnalazione_pkey PRIMARY KEY (idsegnalazione);


--
-- Name: vaccinati vaccinati_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccinati
    ADD CONSTRAINT vaccinati_pkey PRIMARY KEY (idvaccinazione);


--
-- Name: cittadiniregistrati cittadiniregistrati_idcentro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cittadiniregistrati
    ADD CONSTRAINT cittadiniregistrati_idcentro_fkey FOREIGN KEY (idcentro) REFERENCES public.centrivaccinali(idcentro);


--
-- Name: cittadiniregistrati cittadiniregistrati_idvaccinazione_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cittadiniregistrati
    ADD CONSTRAINT cittadiniregistrati_idvaccinazione_fkey FOREIGN KEY (idvaccinazione) REFERENCES public.vaccinati(idvaccinazione);


--
-- Name: segnalazione segnalazione_idevento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segnalazione
    ADD CONSTRAINT segnalazione_idevento_fkey FOREIGN KEY (idevento) REFERENCES public.eventoavverso(idevento);


--
-- Name: segnalazione segnalazione_idvaccinazione_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.segnalazione
    ADD CONSTRAINT segnalazione_idvaccinazione_fkey FOREIGN KEY (idvaccinazione) REFERENCES public.vaccinati(idvaccinazione);


--
-- PostgreSQL database dump complete
--

