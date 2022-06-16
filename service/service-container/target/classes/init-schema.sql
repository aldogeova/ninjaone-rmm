DROP SCHEMA IF EXISTS "service" CASCADE;

CREATE SCHEMA "service";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS "service".service CASCADE;

CREATE TABLE "service".service
(
    id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    created_date bigint NOT NULL,
    modified_date bigint NOT NULL,
    enabled boolean DEFAULT TRUE NOT NULL,
    CONSTRAINT service_pkey PRIMARY KEY (id)
);

-------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS "service".service_type CASCADE;

CREATE TABLE "service".service_type
(
    id uuid NOT NULL,
    service_id uuid NOT NULL,
    type_device_id uuid NOT NULL,
    price numeric(10,2) NOT NULL,
    created_date bigint NOT NULL,
    modified_date bigint NOT NULL,
    enabled boolean DEFAULT TRUE NOT NULL,
    CONSTRAINT service_type_pkey PRIMARY KEY (id)
);

ALTER TABLE "service".service_type
    ADD CONSTRAINT "FK_SERVICE_ID" FOREIGN KEY (service_id)
    REFERENCES "service".service (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;
