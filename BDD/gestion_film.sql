PGDMP             
            x           gestionFilm    12.2    12.2     )           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            *           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            +           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ,           1262    16423    gestionFilm    DATABASE     �   CREATE DATABASE "gestionFilm" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';
    DROP DATABASE "gestionFilm";
                postgres    false            �            1259    16424 	   adherents    TABLE       CREATE TABLE public.adherents (
    id_adherents integer NOT NULL,
    prenom character varying(100) NOT NULL,
    nom character varying(100) NOT NULL,
    numero_rue integer,
    adresse_rue character varying(100),
    code_postal integer,
    ville character varying(100) NOT NULL
);
    DROP TABLE public.adherents;
       public         heap    postgres    false            �            1259    16432    articles    TABLE     �   CREATE TABLE public.articles (
    id_article integer NOT NULL,
    nb_disques integer,
    dvd_ou_bluray character varying(1) NOT NULL,
    bonus boolean,
    trois_d boolean,
    id_film integer,
    id_emprunteur integer
);
    DROP TABLE public.articles;
       public         heap    postgres    false            �            1259    16462    films    TABLE     �   CREATE TABLE public.films (
    id_film integer NOT NULL,
    titre character varying(100) NOT NULL,
    date_de_sortie date NOT NULL
);
    DROP TABLE public.films;
       public         heap    postgres    false            �            1259    16467    realisateurs    TABLE     �   CREATE TABLE public.realisateurs (
    id_realisateur integer NOT NULL,
    prenom character varying(100) NOT NULL,
    nom character varying NOT NULL
);
     DROP TABLE public.realisateurs;
       public         heap    postgres    false            �            1259    16475    realisations    TABLE     h   CREATE TABLE public.realisations (
    id_realisateur integer NOT NULL,
    id_film integer NOT NULL
);
     DROP TABLE public.realisations;
       public         heap    postgres    false            �            1259    16502    seq_adherent    SEQUENCE     v   CREATE SEQUENCE public.seq_adherent
    START WITH 100
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.seq_adherent;
       public          postgres    false            �            1259    16504    seq_film    SEQUENCE     s   CREATE SEQUENCE public.seq_film
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    DROP SEQUENCE public.seq_film;
       public          postgres    false                       0    16424 	   adherents 
   TABLE DATA           k   COPY public.adherents (id_adherents, prenom, nom, numero_rue, adresse_rue, code_postal, ville) FROM stdin;
    public          postgres    false    202   �        !          0    16432    articles 
   TABLE DATA           q   COPY public.articles (id_article, nb_disques, dvd_ou_bluray, bonus, trois_d, id_film, id_emprunteur) FROM stdin;
    public          postgres    false    203   �!       "          0    16462    films 
   TABLE DATA           ?   COPY public.films (id_film, titre, date_de_sortie) FROM stdin;
    public          postgres    false    204   �!       #          0    16467    realisateurs 
   TABLE DATA           C   COPY public.realisateurs (id_realisateur, prenom, nom) FROM stdin;
    public          postgres    false    205   )"       $          0    16475    realisations 
   TABLE DATA           ?   COPY public.realisations (id_realisateur, id_film) FROM stdin;
    public          postgres    false    206   `"       -           0    0    seq_adherent    SEQUENCE SET     =   SELECT pg_catalog.setval('public.seq_adherent', 100, false);
          public          postgres    false    207            .           0    0    seq_film    SEQUENCE SET     9   SELECT pg_catalog.setval('public.seq_film', 100, false);
          public          postgres    false    208            �
           2606    16431    adherents adherents_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.adherents
    ADD CONSTRAINT adherents_pkey PRIMARY KEY (id_adherents);
 B   ALTER TABLE ONLY public.adherents DROP CONSTRAINT adherents_pkey;
       public            postgres    false    202            �
           2606    16436    articles articles_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.articles
    ADD CONSTRAINT articles_pkey PRIMARY KEY (id_article);
 @   ALTER TABLE ONLY public.articles DROP CONSTRAINT articles_pkey;
       public            postgres    false    203            �
           2606    16466    films films_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.films
    ADD CONSTRAINT films_pkey PRIMARY KEY (id_film);
 :   ALTER TABLE ONLY public.films DROP CONSTRAINT films_pkey;
       public            postgres    false    204            �
           2606    16479 &   realisations id_realisateur_id_film_PK 
   CONSTRAINT     {   ALTER TABLE ONLY public.realisations
    ADD CONSTRAINT "id_realisateur_id_film_PK" PRIMARY KEY (id_realisateur, id_film);
 R   ALTER TABLE ONLY public.realisations DROP CONSTRAINT "id_realisateur_id_film_PK";
       public            postgres    false    206    206            �
           2606    16474    realisateurs realisateurs_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.realisateurs
    ADD CONSTRAINT realisateurs_pkey PRIMARY KEY (id_realisateur);
 H   ALTER TABLE ONLY public.realisateurs DROP CONSTRAINT realisateurs_pkey;
       public            postgres    false    205            �
           1259    16501    fki_id_emprunteur_FK    INDEX     T   CREATE INDEX "fki_id_emprunteur_FK" ON public.articles USING btree (id_emprunteur);
 *   DROP INDEX public."fki_id_emprunteur_FK";
       public            postgres    false    203            �
           1259    16495    fki_id_film_FK    INDEX     H   CREATE INDEX "fki_id_film_FK" ON public.articles USING btree (id_film);
 $   DROP INDEX public."fki_id_film_FK";
       public            postgres    false    203            �
           2606    16496    articles id_emprunteur_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public.articles
    ADD CONSTRAINT "id_emprunteur_FK" FOREIGN KEY (id_emprunteur) REFERENCES public.adherents(id_adherents) NOT VALID;
 E   ALTER TABLE ONLY public.articles DROP CONSTRAINT "id_emprunteur_FK";
       public          postgres    false    202    203    2707            �
           2606    16485    realisations id_film_FK    FK CONSTRAINT     }   ALTER TABLE ONLY public.realisations
    ADD CONSTRAINT "id_film_FK" FOREIGN KEY (id_film) REFERENCES public.films(id_film);
 C   ALTER TABLE ONLY public.realisations DROP CONSTRAINT "id_film_FK";
       public          postgres    false    206    2713    204            �
           2606    16490    articles id_film_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public.articles
    ADD CONSTRAINT "id_film_FK" FOREIGN KEY (id_film) REFERENCES public.films(id_film) NOT VALID;
 ?   ALTER TABLE ONLY public.articles DROP CONSTRAINT "id_film_FK";
       public          postgres    false    204    2713    203            �
           2606    16480    realisations id_realisateur_FK    FK CONSTRAINT     �   ALTER TABLE ONLY public.realisations
    ADD CONSTRAINT "id_realisateur_FK" FOREIGN KEY (id_realisateur) REFERENCES public.realisateurs(id_realisateur);
 J   ALTER TABLE ONLY public.realisations DROP CONSTRAINT "id_realisateur_FK";
       public          postgres    false    2715    206    205                �   x�U��
�0�'_�/��Z˂Z�5A�&�^�����_�s���3'���@����L��-���Hwy�Q�#d���&h���\�m�)�X��e�(��4�k�ņ]��W�F��#��?|B�[�_�Z
���w\L���}��m�����$�B�W�l��CA�6\˳�����u��q?�      !      x�3��L���,�4�4����� $�6      "   T   x�3��IUN�L�K--RHI-Vp��KM,��4200�50".#N�Ē��
NCKKK��1�GbQQ��B@~IIjP��!T*F��� `_�      #   '   x�3��I�K�wt�����2����ɩD����� ۔
&      $      x�3�4�2�=... ��     