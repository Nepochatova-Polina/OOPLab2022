--liquibase formatted sql

CREATE TABLE IF NOT EXISTS public.userdb
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    username character varying COLLATE pg_catalog."default" UNIQUE NOT NULL,
    password character varying COLLATE pg_catalog."default" UNIQUE NOT NULL,
    CONSTRAINT user_id PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.userdb
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.role
(
    user_id integer UNIQUE NOT NULL,
    role character varying COLLATE pg_catalog."default"  NOT NULL,
    CONSTRAINT user_id FOREIGN KEY (user_id)
    REFERENCES public.userdb (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.role
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.apartments
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    number INTEGER UNIQUE NOT NULL,
    layout character varying COLLATE pg_catalog."default" NOT NULL,
    occupancy integer NOT NULL,
    price integer NOT NULL,
    CONSTRAINT apartments_id PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.apartments
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.reservations
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    apartment_id integer NOT NULL,
    check_in character varying COLLATE pg_catalog."default"  NOT NULL,
    check_out character varying COLLATE pg_catalog."default"  NOT NULL,
    bill character varying COLLATE pg_catalog."default"  NOT NULL,
    confirmation boolean NOT NULL,
    CONSTRAINT reservation_id PRIMARY KEY (id),
    CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.userdb (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE,
    CONSTRAINT apartment_id_fkey FOREIGN KEY (apartment_id)
    REFERENCES public.apartments (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE,
    UNIQUE (user_id, apartment_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.reservations
    OWNER to postgres;
