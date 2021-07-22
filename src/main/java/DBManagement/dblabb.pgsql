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
    idvaccinazione integer NOT NULL,
    cf character(16) NOT NULL,
    idcentro integer NOT NULL,
    nick character varying(20) NOT NULL
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
    idvaccinazione integer NOT NULL,
    idevento integer NOT NULL,
    severita integer NOT NULL,
    nota character varying(256)
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
    nomecentro character varying(30) NOT NULL,
    nome character varying(30) NOT NULL,
    cognome character varying(30) NOT NULL,
    cf character(16) NOT NULL,
    datasomministrazione date NOT NULL,
    vaccinosomministrato public.vaccino,
    idvaccinazione integer NOT NULL
);


ALTER TABLE public.vaccinati OWNER TO postgres;

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
-- Data for Name: centrivaccinali; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.centrivaccinali (idcentro, nome, indirizzo, tipologia) FROM stdin;
72	Scuola Media ontario De Simone	piazza narducci;585;Treviso;AT;28070	ospedaliero
73	Centro Verde	via bartolucci;335;Alessandria;MT;23789	ospedaliero
74	Campo simoni	via romualda rosato;186;Massa-Carrara;SV;38886	hub
75	Campo Azzurro	piazza cristofaro bazhanova;68;Terni;CA;12355	ospedaliero
76	Campo spoletini	piazza coluccia;28;Massa-Carrara;TN;32114	ospedaliero
77	Pala sestito	via barruga;708;Rieti;BO;26620	aziendale
78	Pala Bianco	piazza Esposito;744;Terni;TE;23437	aziendale
79	Scuola Elementare reni pulsoni	piazza pupa cospito;638;Enna;VA;29968	hub
80	Centro Bianco	via rosana nurul;46;Ragusa;VV;19981	hub
81	Centro Verde	piazza cristofaro linzi;249;Taranto;LE;29719	hub
82	Scuola Elementare luigi fasano	piazza galli;35;Barletta-Andria-Trani;RC;39061	aziendale
83	Centro Verde	piazza Moretti;607;Vercelli;VS;35155	aziendale
84	Scuola Media ermella fortini	viale algeria Mazza;27;Catania;TR;26969	ospedaliero
85	Centro De Luca	via ginesio panchetti;498;Vibo Valentia;RG;17567	aziendale
86	Centro guidi	via raulino pereira de castro;821;Brindisi;FC;17045	aziendale
87	Campo Bianco	piazza idiano Agostini;493;Rimini;TO;27091	hub
88	Campo Azzurro	via alcide coccia;817;Monza e della Brianza;MO;20230	hub
89	Centro Bianco	via ferrarina popovici;715;Ragusa;MN;32898	hub
90	Scuola Superiore rena anklin	viale canichella;672;Reggio Calabria;BA;24424	aziendale
91	Pala totaro 	viale amato;479;Cagliari;IS;29457	aziendale
92	Scuola Superiore ringo Pepe	via aldimiro michilli;384;Lecco;GO;28477	hub
93	Centro Rosso	piazza catia Lorusso;793;Siena;MO;39775	ospedaliero
94	Pala Blu	via mercantini;922;La Spezia;PC;19213	ospedaliero
95	Centro simoni	viale luzietti;99;Taranto;AT;13143	ospedaliero
96	Campo Rossi	viale sorgentone;311;Fermo;AR;18802	hub
97	Campo linzi	viale vinciguerra;40;Napoli;PA;37403	ospedaliero
98	Scuola Media orifiamma severi	via gilia zenina;836;Gorizia;BT;12283	ospedaliero
99	Scuola Media lazzero zito	viale driade barone;517;Cuneo;FM;21971	ospedaliero
100	Pala Rosso	viale coccia;43;Perugia;VS;31501	ospedaliero
101	Centro borkowska	viale mucci;168;Reggio Calabria;CN;29742	ospedaliero
102	Campo buongrazio	piazza de santis;512;Terni;VR;15170	hub
103	Scuola Superiore pilar parini	via zeolla;602;Lucca;AQ;30470	hub
104	Scuola Media remaldo iuliano	via Farina;267;Teramo;SS;39665	ospedaliero
105	Scuola Media gentilio piccoli	via terziano Villani;301;Matera;PR;29490	hub
106	Scuola Media leonella panza	piazza Cavallo;831;Vibo Valentia;ME;39619	ospedaliero
107	Pala Bianco	via ilide hossain;156;Ancona;LC;11597	hub
108	Centro colonna	viale ascenza ricci;780;Teramo;FC;25228	hub
109	Campo Blu	viale lazazzera;818;Brescia;BO;39339	hub
110	Scuola Media udalrico pia	piazza zito;612;Terni;BZ;10092	ospedaliero
111	Campo borkowska	via ferruccio massa;313;Cuneo;TE;39677	aziendale
112	Scuola Media filindo giura	viale eudemo jagmin;187;Oristano;RO;10713	hub
113	Campo Rosso	viale emumwen;618;Lecce;VV;14511	hub
114	Scuola Media oves tiranno	piazza barsanti;581;Firenze;PI;37548	aziendale
115	Scuola Media mohamad bassani	piazza fortini;249;Padova;LC;10337	hub
116	Campo patwary	via rainesi;492;Enna;SV;33014	ospedaliero
117	Pala Verde	viale terziano graffiedi;584;Reggio Calabria;BO;12922	aziendale
118	Scuola Media osema Pagano	piazza chaibi;305;Cremona;RE;27312	aziendale
119	Scuola Superiore oldea scotti	via odissea De Simone;746;Pescara;IM;13121	ospedaliero
120	Scuola Media otino Franco	piazza nuti;621;Grosseto;OT;26915	aziendale
121	Pala Bianco	piazza mammola karim;674;Agrigento;VA;36160	hub
122	Campo fico	piazza montuori;547;Brescia;MB;11509	hub
123	Scuola Media idalma marano	viale alberica Bruno;958;Viterbo;TP;16554	hub
124	Pala cipollone	viale di carlo;500;Asti;BZ;10113	ospedaliero
125	Pala Azzurro	via alcmena rizzelli;740;Udine;IS;17766	ospedaliero
126	Pala Rosso	via palestina salvini;896;Teramo;TR;15280	aziendale
127	Campo Blu	via di sipio;877;Trento;VR;35250	aziendale
128	Campo Rosso	via mentana Santoro;935;Sassari;CL;28640	aziendale
129	Scuola Media filino leonetti	piazza clodomiro buccolieri;833;Aosta;BN;10029	aziendale
130	Scuola Media malvina Giordano	piazza orana cipollone;900;Siracusa;AG;21814	ospedaliero
131	Pala Bianco	via micol caporilli;136;Pesaro e Urbino;LC;30068	ospedaliero
132	Campo Bianco	via baronti;162;Belluno;CI;27303	ospedaliero
133	Scuola Media saturnino fiumi	via Ceccarelli;798;Aosta;CS;12539	ospedaliero
\.


--
-- Data for Name: cittadiniregistrati; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cittadiniregistrati (idutente, nome, cognome, email, password, idvaccinazione, cf, idcentro, nick) FROM stdin;
\.


--
-- Data for Name: eventoavverso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.eventoavverso (idevento, evento) FROM stdin;
1	Mal di testa
2	Febbre
3	Dolori muscolari e articolari
4	Linfoadenopatia
5	Tachicardia
6	Crisi ipertensiva
\.


--
-- Data for Name: segnalazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.segnalazione (idsegnalazione, idvaccinazione, idevento, severita, nota) FROM stdin;
\.


--
-- Data for Name: vaccinati; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vaccinati (idcentro, nomecentro, nome, cognome, cf, datasomministrazione, vaccinosomministrato, idvaccinazione) FROM stdin;
\.


--
-- Name: centrivaccinali_idcentro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.centrivaccinali_idcentro_seq', 133, true);


--
-- Name: cittadiniregistrati_idutente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cittadiniregistrati_idutente_seq', 23, true);


--
-- Name: eventoavverso_idevento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.eventoavverso_idevento_seq', 6, true);


--
-- Name: segnalazione_idsegnalazione_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.segnalazione_idsegnalazione_seq', 1, false);


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

