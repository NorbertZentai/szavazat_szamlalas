--
-- PostgreSQL database dump
--

-- Dumped from database version 11.22
-- Dumped by pg_dump version 11.22

-- Started on 2024-11-05 13:47:17

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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 205 (class 1259 OID 16459)
-- Name: adatok; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adatok (
    nev character varying(100),
    telefonszam character varying(20)
);


ALTER TABLE public.adatok OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16396)
-- Name: felhasznalok; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.felhasznalok (
    felhasznalo_id integer NOT NULL,
    nev character varying(100) NOT NULL,
    jelszo character varying(100) NOT NULL
);


ALTER TABLE public.felhasznalok OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16394)
-- Name: felhasznalok_felhasznalo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.felhasznalok_felhasznalo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.felhasznalok_felhasznalo_id_seq OWNER TO postgres;

--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 196
-- Name: felhasznalok_felhasznalo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.felhasznalok_felhasznalo_id_seq OWNED BY public.felhasznalok.felhasznalo_id;


--
-- TOC entry 204 (class 1259 OID 16441)
-- Name: foglalasok; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.foglalasok (
    foglalas_id integer NOT NULL,
    felhasznalo_id integer,
    szallas_id integer,
    allapot character varying(50)
);


ALTER TABLE public.foglalasok OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16439)
-- Name: foglalasok_foglalas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.foglalasok_foglalas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.foglalasok_foglalas_id_seq OWNER TO postgres;

--
-- TOC entry 2867 (class 0 OID 0)
-- Dependencies: 203
-- Name: foglalasok_foglalas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.foglalasok_foglalas_id_seq OWNED BY public.foglalasok.foglalas_id;


--
-- TOC entry 200 (class 1259 OID 16413)
-- Name: jogosultsag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jogosultsag (
    felhasznalo_id integer NOT NULL,
    szerep_id integer NOT NULL
);


ALTER TABLE public.jogosultsag OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16430)
-- Name: szallasok; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.szallasok (
    szallas_id integer NOT NULL,
    ar numeric(10,2) NOT NULL,
    leiras text
);


ALTER TABLE public.szallasok OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16428)
-- Name: szallasok_szallas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.szallasok_szallas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.szallasok_szallas_id_seq OWNER TO postgres;

--
-- TOC entry 2868 (class 0 OID 0)
-- Dependencies: 201
-- Name: szallasok_szallas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.szallasok_szallas_id_seq OWNED BY public.szallasok.szallas_id;


--
-- TOC entry 199 (class 1259 OID 16404)
-- Name: szerepek; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.szerepek (
    szerep_id integer NOT NULL,
    szerepnev character varying(100) NOT NULL,
    szerep_leiras text
);


ALTER TABLE public.szerepek OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16402)
-- Name: szerepek_szerep_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.szerepek_szerep_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.szerepek_szerep_id_seq OWNER TO postgres;

--
-- TOC entry 2869 (class 0 OID 0)
-- Dependencies: 198
-- Name: szerepek_szerep_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.szerepek_szerep_id_seq OWNED BY public.szerepek.szerep_id;


--
-- TOC entry 206 (class 1259 OID 16472)
-- Name: velemenyek; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.velemenyek (
    ertekeles integer,
    uzenet text
);


ALTER TABLE public.velemenyek OWNER TO postgres;

--
-- TOC entry 2711 (class 2604 OID 16399)
-- Name: felhasznalok felhasznalo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.felhasznalok ALTER COLUMN felhasznalo_id SET DEFAULT nextval('public.felhasznalok_felhasznalo_id_seq'::regclass);


--
-- TOC entry 2714 (class 2604 OID 16444)
-- Name: foglalasok foglalas_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.foglalasok ALTER COLUMN foglalas_id SET DEFAULT nextval('public.foglalasok_foglalas_id_seq'::regclass);


--
-- TOC entry 2713 (class 2604 OID 16433)
-- Name: szallasok szallas_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.szallasok ALTER COLUMN szallas_id SET DEFAULT nextval('public.szallasok_szallas_id_seq'::regclass);


--
-- TOC entry 2712 (class 2604 OID 16407)
-- Name: szerepek szerep_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.szerepek ALTER COLUMN szerep_id SET DEFAULT nextval('public.szerepek_szerep_id_seq'::regclass);


--
-- TOC entry 2859 (class 0 OID 16459)
-- Dependencies: 205
-- Data for Name: adatok; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.adatok (nev, telefonszam) FROM stdin;
\.


--
-- TOC entry 2851 (class 0 OID 16396)
-- Dependencies: 197
-- Data for Name: felhasznalok; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.felhasznalok (felhasznalo_id, nev, jelszo) FROM stdin;
\.


--
-- TOC entry 2858 (class 0 OID 16441)
-- Dependencies: 204
-- Data for Name: foglalasok; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.foglalasok (foglalas_id, felhasznalo_id, szallas_id, allapot) FROM stdin;
\.


--
-- TOC entry 2854 (class 0 OID 16413)
-- Dependencies: 200
-- Data for Name: jogosultsag; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jogosultsag (felhasznalo_id, szerep_id) FROM stdin;
\.


--
-- TOC entry 2856 (class 0 OID 16430)
-- Dependencies: 202
-- Data for Name: szallasok; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.szallasok (szallas_id, ar, leiras) FROM stdin;
\.


--
-- TOC entry 2853 (class 0 OID 16404)
-- Dependencies: 199
-- Data for Name: szerepek; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.szerepek (szerep_id, szerepnev, szerep_leiras) FROM stdin;
\.


--
-- TOC entry 2860 (class 0 OID 16472)
-- Dependencies: 206
-- Data for Name: velemenyek; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.velemenyek (ertekeles, uzenet) FROM stdin;
\.


--
-- TOC entry 2870 (class 0 OID 0)
-- Dependencies: 196
-- Name: felhasznalok_felhasznalo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.felhasznalok_felhasznalo_id_seq', 1, false);


--
-- TOC entry 2871 (class 0 OID 0)
-- Dependencies: 203
-- Name: foglalasok_foglalas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.foglalasok_foglalas_id_seq', 1, false);


--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 201
-- Name: szallasok_szallas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.szallasok_szallas_id_seq', 1, false);


--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 198
-- Name: szerepek_szerep_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.szerepek_szerep_id_seq', 1, false);


--
-- TOC entry 2716 (class 2606 OID 16401)
-- Name: felhasznalok felhasznalok_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.felhasznalok
    ADD CONSTRAINT felhasznalok_pkey PRIMARY KEY (felhasznalo_id);


--
-- TOC entry 2724 (class 2606 OID 16446)
-- Name: foglalasok foglalasok_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.foglalasok
    ADD CONSTRAINT foglalasok_pkey PRIMARY KEY (foglalas_id);


--
-- TOC entry 2720 (class 2606 OID 16417)
-- Name: jogosultsag jogosultsag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogosultsag
    ADD CONSTRAINT jogosultsag_pkey PRIMARY KEY (felhasznalo_id, szerep_id);


--
-- TOC entry 2722 (class 2606 OID 16438)
-- Name: szallasok szallasok_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.szallasok
    ADD CONSTRAINT szallasok_pkey PRIMARY KEY (szallas_id);


--
-- TOC entry 2718 (class 2606 OID 16412)
-- Name: szerepek szerepek_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.szerepek
    ADD CONSTRAINT szerepek_pkey PRIMARY KEY (szerep_id);


--
-- TOC entry 2727 (class 2606 OID 16447)
-- Name: foglalasok foglalasok_felhasznalo_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.foglalasok
    ADD CONSTRAINT foglalasok_felhasznalo_id_fkey FOREIGN KEY (felhasznalo_id) REFERENCES public.felhasznalok(felhasznalo_id) ON DELETE CASCADE;


--
-- TOC entry 2728 (class 2606 OID 16452)
-- Name: foglalasok foglalasok_szallas_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.foglalasok
    ADD CONSTRAINT foglalasok_szallas_id_fkey FOREIGN KEY (szallas_id) REFERENCES public.szallasok(szallas_id) ON DELETE CASCADE;


--
-- TOC entry 2725 (class 2606 OID 16418)
-- Name: jogosultsag jogosultsag_felhasznalo_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogosultsag
    ADD CONSTRAINT jogosultsag_felhasznalo_id_fkey FOREIGN KEY (felhasznalo_id) REFERENCES public.felhasznalok(felhasznalo_id) ON DELETE CASCADE;


--
-- TOC entry 2726 (class 2606 OID 16423)
-- Name: jogosultsag jogosultsag_szerep_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogosultsag
    ADD CONSTRAINT jogosultsag_szerep_id_fkey FOREIGN KEY (szerep_id) REFERENCES public.szerepek(szerep_id) ON DELETE CASCADE;


-- Completed on 2024-11-05 13:47:18

--
-- PostgreSQL database dump complete
--

