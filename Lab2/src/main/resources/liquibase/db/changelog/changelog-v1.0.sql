--liquibase formatted sql

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
    user_id character varying COLLATE pg_catalog."default" NOT NULL,
    apartment_id integer NOT NULL,
    check_in character varying COLLATE pg_catalog."default"  NOT NULL,
    check_out character varying COLLATE pg_catalog."default"  NOT NULL,
    bill character varying COLLATE pg_catalog."default"  NOT NULL,
    confirmation boolean NOT NULL,
    CONSTRAINT reservation_id PRIMARY KEY (id),
    CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.user_entity (id) MATCH SIMPLE
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
