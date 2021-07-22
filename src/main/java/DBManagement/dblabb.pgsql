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
-- Data for Name: centrivaccinali; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.centrivaccinali (idcentro, nome, indirizzo, tipologia) FROM stdin;
269	Centro Verde	piazza massimina de filippis;541;Teramo;FI;16344	hub
270	Centro Azzurro	via campanella;257;Medio Campidano;PI;38712	ospedaliero
271	Centro coulier	via tiranno;812;Messina;AT;32166	ospedaliero
272	Scuola Elementare sibilla caldironi	piazza abele ghazi;475;Trapani;EN;16805	aziendale
273	Scuola Elementare ferruccio Pinna	piazza lucillo de cataldo;118;Brescia;AT;26430	aziendale
274	Pala kosek	piazza siracusa;69;Pistoia;RN;32696	aziendale
275	Centro zenina	viale rifredo piccoli;988;Pistoia;LU;19982	hub
276	Scuola Elementare torquato nicastro	via Lorusso;751;Agrigento;PT;35343	ospedaliero
277	Pala Verde	viale ermenegildo di tuoro ;573;Salerno;RA;22433	aziendale
278	Scuola Media madio ciervo	viale baronti;164;Ferrara;KR;33858	aziendale
279	Scuola Superiore pedja Pastore	piazza aurora fassbender;911;Catania;VA;12480	hub
280	Scuola Superiore gelsomino Romeo	via dorindo piani;96;Medio Campidano;BI;20719	aziendale
281	Scuola Superiore adelcisa giurgola	piazza resende azevedo;553;Asti;TO;21025	ospedaliero
282	Scuola Superiore midio giuliani	viale martellini ;165;Bergamo;AR;37139	hub
283	Scuola Media meno sanges	piazza terziano Bruno;208;Genova;AN;27853	hub
284	Scuola Media ivania casella	piazza porta;73;Brindisi;PC;14157	hub
285	Scuola Superiore achilla tosolini	piazza iachini;172;Taranto;VB;32927	hub
286	Scuola Superiore basilico mahu	viale cospito;935;Ragusa;BN;35100	ospedaliero
287	Scuola Elementare angiolina hossain	via mafalda fontana;731;Taranto;CA;20730	aziendale
288	Scuola Media argentino de matteo	via astra lanconelli;404;Alessandria;BL;27123	aziendale
289	Pala Verde	viale wilmer;473;Napoli;SR;24294	ospedaliero
290	Centro Azzurro	viale Greco;751;Ferrara;BN;17515	aziendale
291	Pala Bianco	piazza pasquini;343;Trento;FM;30432	ospedaliero
292	Scuola Media era michilli	via antonelli;317;Brindisi;BS;16098	ospedaliero
293	Pala Rosso	viale madina Sanna;414;Latina;SV;12853	ospedaliero
294	Pala siapian	viale odeide lucarini;497;Brescia;LC;30604	hub
295	Scuola Media elfride Valente	via euclida lazazzera;311;Ferrara;NU;29751	aziendale
296	Scuola Media crisostomo Giorgi	piazza bernardi;557;Como;RI;33086	hub
297	Scuola Elementare dalino bertini	via moiano;600;Catanzaro;GR;19407	aziendale
298	Campo lattanzi	viale meucci;972;Trapani;VI;30161	ospedaliero
299	Pala Rosso	piazza speranzo Ferrara;809;Enna;AQ;15307	ospedaliero
300	Centro Leone	via filippi;519;Barletta-Andria-Trani;CL;13330	aziendale
301	Centro Gallo	piazza geminio pascucci;945;La Spezia;MO;29222	hub
302	Centro mezzogori	viale caccamo;767;Teramo;EN;37425	aziendale
303	Pala Verde	via bertini;218;Verbano-Cusio-Ossola;SS;17207	ospedaliero
304	Scuola Media giangaetano pierucci	via alcina Longo;751;Alessandria;BN;37662	hub
305	Pala severi	piazza gallo Ferro;921;Vibo Valentia;BN;18153	hub
306	Pala Rosso	viale arduilio tambini;717;Olbia-Tempio;BZ;26964	ospedaliero
307	Centro Marino	via la bella;275;Vibo Valentia;CB;11684	ospedaliero
308	Scuola Superiore realda di carlo	via Messina;220;Messina;TV;30528	ospedaliero
309	Scuola Elementare parisino hijjoui	viale domenica scardone;252;Campobasso;PT;17497	hub
310	Scuola Elementare fulva angiuli	viale deiana;260;Firenze;PD;17895	aziendale
311	Centro Verde	piazza valvano;823;Novara;MT;37616	ospedaliero
312	Campo romani bergonzo	piazza vaccaro;502;Brescia;CL;21447	hub
313	Pala haji	via antonelli;34;Massa-Carrara;SP;17299	ospedaliero
314	Scuola Media amelio diacetti	viale pomponio clementucci;195;Potenza;CB;16476	hub
315	Pala Bianco	via genoveffa el haqaoui;908;Ragusa;RN;19598	aziendale
316	Campo Azzurro	via boschi;967;La Spezia;BL;26827	ospedaliero
317	Scuola Media pascuccio pasolini	viale bernardi;44;Cosenza;PV;38447	ospedaliero
318	Scuola Elementare marlena massa	via albo stazzone;484;Rovigo;GR;36919	ospedaliero
319	Scuola Media trifone Farina	piazza zanelli;928;Cuneo;RI;11913	hub
320	Campo martys	piazza fassbender;52;Latina;CZ;34713	ospedaliero
321	Scuola Superiore clemens tisba	piazza olivelli;801;Aosta;OT;33507	aziendale
322	Campo Bianco	piazza acunzo;309;Ragusa;BZ;10260	ospedaliero
323	Scuola Superiore ottilia morsiani	via fedilla zivkovic;352;Vercelli;CE;35786	hub
324	Scuola Superiore niobe giura	via de bacco;371;Matera;AV;30968	aziendale
325	Scuola Elementare andreina zaccaria	piazza vassura;217;Rimini;PD;33929	aziendale
326	Pala colle	piazza bassani;176;Salerno;BO;14285	aziendale
327	Scuola Media alideo de stefano	piazza nuccia carucci;273;Aosta;CL;29953	hub
328	Scuola Elementare fervida Ferrero	viale ottavio Calabrese;111;Bari;BR;24899	hub
329	Scuola Media sostine petritoli	via michela Lombardo;447;Forlì-Cesena;SI;31666	aziendale
330	Centro haryadi	viale paglia;45;Trento;TS;35476	ospedaliero
331	Scuola Elementare ambrogino morsiani	viale giammaria Monti;36;Agrigento;FR;26539	hub
332	Scuola Superiore meuccia stazzone	via berardi;70;Rieti;FR;12353	aziendale
333	Pala gindre	via andrietto Pellegrini;779;Aosta;LO;12014	ospedaliero
334	Scuola Elementare amero russo	via fossaroli;94;Trieste;CZ;14055	aziendale
335	Scuola Media olvea fossaroli	viale el hallaf;332;Napoli;BL;15294	ospedaliero
336	Scuola Elementare agenore Barbieri	viale ortemio kosek;173;Sondrio;LC;13266	hub
337	Scuola Media ledina de simone	viale polissena porta;141;Cagliari;BI;15595	aziendale
338	Campo pulsoni	viale oseo scardone;489;Livorno;CN;31977	aziendale
339	Campo zeolla	viale pelliccia;628;Verbano-Cusio-Ossola;LC;14228	hub
340	Campo Blu	via tufano;247;Treviso;MO;21262	hub
341	Scuola Elementare polissena de simone	viale Pellegrini;185;Pavia;AG;22217	aziendale
342	Scuola Elementare mileda sementilli	via de stefano;697;Campobasso;TN;35752	hub
343	Campo manchia	viale giordano contiu;752;Savona;BR;27726	hub
344	Centro Azzurro	via mane;578;Parma;ME;12287	hub
345	Centro haryadi	via remedino chiricozzi;598;Taranto;RA;15129	ospedaliero
346	Scuola Superiore rotilio gagliardi	piazza metozzi;658;Belluno;AV;15526	aziendale
347	Scuola Superiore dera borkowska	via arcangelo succi;353;Verona;PC;24873	hub
348	Campo Grassi	piazza marilla antonelli;381;Monza e della Brianza;VT;34989	ospedaliero
349	Centro Rosso	via rosati;275;Livorno;AP;27136	hub
350	Campo mohammed	viale terzillo colombo ;595;Pisa;VI;25652	hub
351	Scuola Elementare valchiria barsanti	via comini;868;Torino;MO;33645	aziendale
352	Centro Palumbo	via pereira de castro;991;Lecco;LC;14758	hub
353	Centro Verde	via celinia steffenoni;552;Brescia;VV;21196	hub
354	Scuola Media vituccio Rossi	via vanni;594;Macerata;FR;24037	ospedaliero
355	Scuola Superiore affortunata Marino	via capanna;944;Monza e della Brianza;GE;38706	ospedaliero
356	Scuola Media lisimaco lelli	viale colongi;91;Massa-Carrara;SR;17603	ospedaliero
357	Scuola Superiore ivaldo Ferrari	via bentivegna;709;Latina;FC;25052	hub
358	Scuola Media feliciola Marchi	via fabbrizi;98;Perugia;BG;26192	aziendale
359	Campo cenciarelli	via pascale;350;Vercelli;VV;18003	hub
360	Scuola Superiore nevis caccamo	piazza gisberto baldinotti;819;Cosenza;NO;12708	ospedaliero
361	Pala Pace	via gealapu;372;Padova;UD;30596	aziendale
362	Scuola Media madia Romeo	via lillina De Angelis;239;Torino;TS;27562	hub
363	Scuola Media giliola Valentini	via bastianina saccone;257;Avellino;RG;27000	hub
364	Scuola Media graziosuccio di renna	viale anno nini;505;Foggia;RI;14922	ospedaliero
365	Scuola Elementare poldina ranfi	piazza eichenberger;621;Arezzo;RN;39940	aziendale
366	Scuola Media lusitania panichi	via iacobelli;643;Vibo Valentia;SR;21768	aziendale
367	Scuola Elementare lice guzman soriano	piazza pongetti;933;Benevento;CN;10384	aziendale
368	Scuola Elementare fabiana kazi	viale altomiro ceccaroni;240;Prato;RN;32417	ospedaliero
\.


--
-- Data for Name: cittadiniregistrati; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cittadiniregistrati (idutente, nome, cognome, email, password, cf, idcentro, nick, idvaccinazione) FROM stdin;
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

COPY public.segnalazione (idsegnalazione, idevento, severita, nota, idvaccinazione) FROM stdin;
\.


--
-- Data for Name: vaccinati; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vaccinati (idcentro, nome, cognome, cf, datasomministrazione, vaccinosomministrato, idvaccinazione, nomecentro) FROM stdin;
302	orfina	metozzi	AEIMUGFMNXLGRDVQ	2018-07-22	JJ	136	Centro mezzogori
327	ciardino	terracciano	GQVOVLTEBPCUPRPB	2020-05-06	JJ	137	Scuola Media alideo de stefano
335	domitilla	benhadda	ROQAAVVRJMJXUDLP	2021-05-08	JJ	138	Scuola Media olvea fossaroli
341	adinolfo	Riva	OABSMQBVMBUIVIFU	2019-04-23	JJ	139	Scuola Elementare polissena de simone
347	eolo	Monti	RGZMELRIMOADRTWE	2020-03-26	AstraZeneca	140	Scuola Superiore dera borkowska
272	aloisa	manchia	VIKQXHZRERIGSNTM	2018-06-22	Pfizer	141	Scuola Elementare sibilla caldironi
275	arienzo	terracciano	ITFQQKVPTJWEDWHU	2018-03-14	Moderna	142	Centro zenina
346	mileda	abu	TGGLPIAKFUZQWEFP	2018-06-16	JJ	143	Scuola Superiore rotilio gagliardi
317	viero	crognale	SYXCXSIGFHNRDFRH	2020-11-03	AstraZeneca	144	Scuola Media pascuccio pasolini
334	sostine	contiu	GZYSQOYFHFQOUZDX	2019-08-01	JJ	145	Scuola Elementare amero russo
288	fertile	romani bergonzo	IUFCEYXUVKLQYFRR	2018-03-05	AstraZeneca	146	Scuola Media argentino de matteo
357	orsini	maldini	XMZFTMJWQHNEZJUT	2018-10-26	JJ	147	Scuola Superiore ivaldo Ferrari
317	zaccaria	Di Stefano	JYFKRMUKANALEWEO	2019-09-08	Pfizer	148	Scuola Media pascuccio pasolini
332	saverina	pompili	FRZABNDGDYOUQAPZ	2018-06-13	AstraZeneca	149	Scuola Superiore meuccia stazzone
292	achiropita	paolini	LGCAYNRJCJXJOQKC	2018-03-01	JJ	150	Scuola Media era michilli
319	iliade	Barbieri	BZXNZQMMDNWPNGWF	2021-02-11	JJ	151	Scuola Media trifone Farina
302	innocentina	armaroli	OTLFICVSIJMQCAUR	2018-11-14	Moderna	152	Centro mezzogori
276	ermidio	Mele	PVFLLXMISXGDXJEU	2020-04-21	Moderna	153	Scuola Elementare torquato nicastro
319	orlana	Amato	IBGHWQPJQHCSAZBY	2021-04-11	AstraZeneca	154	Scuola Media trifone Farina
322	agato	Fumagalli	YPAYOFGCDZPXBTCM	2021-04-12	Pfizer	155	Campo Bianco
314	erinna	borgat-wohlbang	CKROSEQIUDRBKYGU	2018-10-27	Pfizer	156	Scuola Media amelio diacetti
301	marisa	Aiello	ZMYUJYTQCUIKMQMA	2020-10-10	Pfizer	157	Centro Gallo
357	adelfio	costa	KVCQENWOSQNKWURB	2019-08-02	AstraZeneca	158	Scuola Superiore ivaldo Ferrari
339	graziano	bonn	XPZTQBAUTTJRZZUS	2019-12-06	JJ	159	Campo zeolla
349	mimina	cecchetti	JMZFKKFENBEMTSTI	2021-09-18	JJ	160	Centro Rosso
276	oceano	costantini	LMHIAYUCCALTTDLF	2021-09-13	AstraZeneca	161	Scuola Elementare torquato nicastro
286	mirta	di paolo	TPNKKAOQEOUAUFBO	2018-07-01	Moderna	162	Scuola Superiore basilico mahu
331	marsilia	Meloni	XDMFTFXBAQHVXPCS	2021-03-24	JJ	163	Scuola Elementare ambrogino morsiani
319	iacopa	mirante	NGXAQMDCOBNNMVKK	2018-01-05	AstraZeneca	164	Scuola Media trifone Farina
331	carmelina	manchia	MMFXJFRSOQSVEBEH	2021-01-04	JJ	165	Scuola Elementare ambrogino morsiani
365	ferruccio	toader	POMTQMOSMMLISGPU	2019-03-28	Moderna	166	Scuola Elementare poldina ranfi
312	melchiorrina	antonelli	EVBNUGEVWFTRLEZL	2019-09-09	Pfizer	167	Campo romani bergonzo
335	isaura	fratini	FOJNZEPFSGQRVIFC	2018-07-09	JJ	168	Scuola Media olvea fossaroli
362	risiero	carpi marasco	HMUIIWOXCBCLYVHV	2021-03-25	Moderna	169	Scuola Media madia Romeo
287	lucrezio	vaccaro	MPIRAWRYDHBQWJCN	2018-05-01	Moderna	170	Scuola Elementare angiolina hossain
320	agricolo	de stefano	MHTIQAKEYUXQALYC	2021-11-21	AstraZeneca	171	Campo martys
328	pascasio	tarozzi	JSUPIIIDYRLIHAHQ	2018-11-11	AstraZeneca	172	Scuola Elementare fervida Ferrero
366	stefanio	apreda	PIGVLRAWWYSEFFHZ	2018-11-03	AstraZeneca	173	Scuola Media lusitania panichi
316	otellia	Ceccarelli	XJRYXAJRFQKLFAAM	2018-10-18	Moderna	174	Campo Azzurro
305	dino	Perrone	BUSLIOFFWEOMCVBX	2021-06-26	Moderna	175	Pala severi
359	uberta	ciampaglia	XZGPDNVZLSUUGREH	2020-12-06	Moderna	176	Campo cenciarelli
284	tanja	cenciarelli	LRBRVZWIJNDACZYG	2019-08-01	Moderna	177	Scuola Media ivania casella
275	celsina	speziale	TBILZLZNHIVKBWQP	2020-12-11	Pfizer	178	Centro zenina
351	aldovina	ceccaroni	ZTTPUHACSEIMGQIJ	2021-12-14	Pfizer	179	Scuola Elementare valchiria barsanti
363	dolorino	pracucci	UDYXPYAZELZWKSUG	2020-09-10	Moderna	180	Scuola Media giliola Valentini
297	salvatorina	narducci	QXRBEIRWJOYGGFIM	2020-06-17	JJ	181	Scuola Elementare dalino bertini
333	effisio	palma 	HYCREKBWQHMPKVON	2020-11-16	JJ	182	Pala gindre
280	clelio	Pinna	WMTLXSMQOIFWTSZZ	2019-11-16	Moderna	183	Scuola Superiore gelsomino Romeo
325	giampietro	Giorgi	JJKNQXLCOWLZELOG	2018-11-28	Pfizer	184	Scuola Elementare andreina zaccaria
272	dusolina	bento da silva	RLXDAIQSYIJRLLVY	2019-12-12	JJ	185	Scuola Elementare sibilla caldironi
280	luciano	bernardi	AOHOGBCOSYAKDCBG	2020-05-22	Moderna	186	Scuola Superiore gelsomino Romeo
340	giovacchina	filippi	LWTLKSNCIBHFXRFQ	2019-03-24	JJ	187	Campo Blu
368	reto	colonna	YKHSEVPBKERMGIIO	2018-09-13	JJ	188	Scuola Elementare fabiana kazi
298	gioiello	tronconi	BOUYLDCZRDYRYNCJ	2018-07-11	Moderna	189	Campo lattanzi
354	frazino	Coppola	WBVKUTWFWQWPVYZM	2020-09-25	Moderna	190	Scuola Media vituccio Rossi
297	febrizio	domenicali	BDIBIQCKVZTHPRML	2019-12-19	AstraZeneca	191	Scuola Elementare dalino bertini
281	isea	napoli	HILIIBVYJQCRYOBN	2021-09-05	Moderna	192	Scuola Superiore adelcisa giurgola
285	andreano	tarnogrodzki	SUKHMKRLTVZMKPZG	2021-12-22	AstraZeneca	193	Scuola Superiore achilla tosolini
335	antero	Arena	PHSLQLQJZUPSOESC	2019-03-28	Pfizer	194	Scuola Media olvea fossaroli
366	antimina	ruggieri	ITFJEYZPFODBLGMT	2021-10-08	Pfizer	195	Scuola Media lusitania panichi
294	antonietto	bello	IIMEGFNKMUNGGUQW	2021-05-27	AstraZeneca	196	Pala siapian
313	trentino	cusin	RTFEYAZPHFAMQDIL	2021-12-05	JJ	197	Pala haji
336	adeodata	fortini	GMVIKYEIFSBXBKRW	2019-09-05	AstraZeneca	198	Scuola Elementare agenore Barbieri
296	egea	hajji	XEOASXHNKIFBUCDC	2021-10-06	Pfizer	199	Scuola Media crisostomo Giorgi
284	giugliano	lena	AMLVUDMKGQHQDIWL	2018-07-16	AstraZeneca	200	Scuola Media ivania casella
319	romualda	la sorda	BSBRFZSEEYTXUIEJ	2019-04-01	AstraZeneca	201	Scuola Media trifone Farina
316	venusta	filagna	AAGIFISITRVINUFW	2018-04-16	Moderna	202	Campo Azzurro
365	ilide	zuccherelli	YNBYALTOYFDOVCWB	2020-05-11	Pfizer	203	Scuola Elementare poldina ranfi
343	ariella	panchetti	VKUYDDRTPJDVICYD	2018-02-13	JJ	204	Campo manchia
295	terzillo	cichon	GFNYYTLAIOORTFPH	2019-02-26	Moderna	205	Scuola Media elfride Valente
349	wandina	Moretti	CKXTDFRMNWMHOXKM	2021-07-04	Pfizer	206	Centro Rosso
358	daniella	pompili	RWVJPFGWIQFKLRNW	2018-04-04	AstraZeneca	207	Scuola Media feliciola Marchi
335	nivea	palazzari	LQLTRMPGCIUMJPDK	2019-03-23	AstraZeneca	208	Scuola Media olvea fossaroli
338	ulderica	huaman lopez	IFEOFTAABXYPREQJ	2021-12-13	JJ	209	Campo pulsoni
294	giovancarla	boncompagni	HCKDWFNDBSGUOHJQ	2020-11-18	Pfizer	210	Pala siapian
282	orviano	cenciarelli	IHFVGNDXOIMQEJXN	2021-04-07	Moderna	211	Scuola Superiore midio giuliani
313	illeana	lacerenza	SSQLMYQXZNKOHRLH	2018-02-27	AstraZeneca	212	Pala haji
336	elvina	sarpieri	DQYIFCJOCSIXOBFE	2019-01-24	Pfizer	213	Scuola Elementare agenore Barbieri
318	novarina	bardi	YMCNUORYJNTADXTZ	2018-10-09	Moderna	214	Scuola Elementare marlena massa
279	ottino	lippi	LFVEXWKWRMBVVTRP	2019-06-28	AstraZeneca	215	Scuola Superiore pedja Pastore
368	imma	cichon	ERDDESQCVYFSRFHJ	2019-06-14	Pfizer	216	Scuola Elementare fabiana kazi
\.


--
-- Name: centrivaccinali_idcentro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.centrivaccinali_idcentro_seq', 368, true);


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
-- Name: vaccinati_idvaccinazione_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vaccinati_idvaccinazione_seq', 216, true);


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

