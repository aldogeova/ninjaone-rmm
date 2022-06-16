DROP SCHEMA IF EXISTS "inventory" CASCADE;

CREATE SCHEMA "inventory";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS device_status;
CREATE TYPE device_status AS ENUM ('PENDING', 'VALIDATED', 'INVALID');

-------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS "inventory".operative_system CASCADE;

CREATE TABLE "inventory".operative_system
(
    id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    created_date bigint NOT NULL,
    modified_date bigint NOT NULL,
    enabled boolean DEFAULT TRUE NOT NULL,
    CONSTRAINT operative_sytem_pkey PRIMARY KEY (id)
);

-------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS "inventory".device_classification CASCADE;

CREATE TABLE "inventory".device_classification
(
    id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    created_date bigint NOT NULL,
    modified_date bigint NOT NULL,
    enabled boolean DEFAULT TRUE NOT NULL,
    CONSTRAINT device_classification_pkey PRIMARY KEY (id)
);

-------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS "inventory".type_device CASCADE;

CREATE TABLE "inventory".type_device
(
    id uuid NOT NULL,
    operative_system_id uuid NOT NULL,
    device_classification_id uuid NOT NULL,
    description character varying COLLATE pg_catalog."default" NOT NULL,
    created_date bigint NOT NULL,
    modified_date bigint NOT NULL,
    enabled boolean DEFAULT TRUE NOT NULL,
    CONSTRAINT type_device_pkey PRIMARY KEY (id)
);

ALTER TABLE "inventory".type_device
    ADD CONSTRAINT "FK_OPERATIVE_SYSTEM_ID" FOREIGN KEY (operative_system_id)
    REFERENCES "inventory".operative_system (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

ALTER TABLE "inventory".type_device
    ADD CONSTRAINT "FK_DEVICE_CLASSIFICATION_ID" FOREIGN KEY (device_classification_id)
    REFERENCES "inventory".device_classification (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

-------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS "inventory".device CASCADE;

CREATE TABLE "inventory".device
(
    id uuid NOT NULL,
    customer_id uuid NOT NULL,
    tracking_id uuid NOT NULL,
    type_device_id uuid NOT NULL,
    system_name character varying COLLATE pg_catalog."default" NOT NULL,
    device_status device_status NOT NULL,
    failure_messages character varying COLLATE pg_catalog."default",
    created_date bigint NOT NULL,
    modified_date bigint NOT NULL,
    enabled boolean DEFAULT TRUE NOT NULL,
    CONSTRAINT device_pkey PRIMARY KEY (id)
);

ALTER TABLE "inventory".device
    ADD CONSTRAINT "FK_TYPE_DEVICE_ID" FOREIGN KEY (type_device_id)
    REFERENCES "inventory".type_device (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

-------------------------------------------------------------------------------------------


DROP TABLE IF EXISTS "inventory".device_service CASCADE;

CREATE TABLE "inventory".device_service
(
    id uuid NOT NULL,
    device_id uuid NOT NULL,
    service_type_id uuid NOT NULL,
    price numeric(10,2) NOT NULL,
    created_date bigint NOT NULL,
    modified_date bigint NOT NULL,
    enabled boolean DEFAULT TRUE NOT NULL,
    message character varying COLLATE pg_catalog."default",
    status character varying COLLATE pg_catalog."default",
    CONSTRAINT device_service_pkey PRIMARY KEY (id)
);

ALTER TABLE "inventory".device_service
    ADD CONSTRAINT "FK_DEVICE_ID" FOREIGN KEY (device_id)
    REFERENCES "inventory".device (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

ALTER TABLE IF EXISTS "inventory".device_service
    ADD CONSTRAINT uni_device_service_type UNIQUE (device_id, service_type_id);


-----------------------------------------------------------------------------------------

DROP MATERIALIZED VIEW IF EXISTS inventory.service_type_device_m_view;

CREATE MATERIALIZED VIEW inventory.service_type_device_m_view
TABLESPACE pg_default
AS
 SELECT id,
    operative_system_id,
    device_classification_id,
    enabled
   FROM inventory.type_device
WITH DATA;

refresh materialized VIEW inventory.service_type_device_m_view;

DROP function IF EXISTS inventory.refresh_service_type_device_m_view;

CREATE OR replace function inventory.refresh_service_type_device_m_view()
returns trigger
AS '
BEGIN
    refresh materialized VIEW inventory.service_type_device_m_view;
    return null;
END;
'  LANGUAGE plpgsql;

DROP trigger IF EXISTS refresh_service_type_device_m_view ON inventory.type_device;

CREATE trigger refresh_service_type_device_m_view
after INSERT OR UPDATE OR DELETE OR truncate
ON inventory.type_device FOR each statement
EXECUTE PROCEDURE inventory.refresh_service_type_device_m_view();