DROP SCHEMA IF EXISTS customer CASCADE;

CREATE SCHEMA customer;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customer.customer
(
    id uuid NOT NULL,
    identification character varying COLLATE pg_catalog."default" NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    mail character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);

DROP MATERIALIZED VIEW IF EXISTS customer.inventory_customer_m_view;

CREATE MATERIALIZED VIEW customer.inventory_customer_m_view
TABLESPACE pg_default
AS
 SELECT id,
    identification,
    name,
    mail
   FROM customer.customer
WITH DATA;

refresh materialized VIEW customer.inventory_customer_m_view;

DROP function IF EXISTS customer.refresh_inventory_customer_m_view;

CREATE OR replace function customer.refresh_inventory_customer_m_view()
returns trigger
AS '
BEGIN
    refresh materialized VIEW customer.inventory_customer_m_view;
    return null;
END;
'  LANGUAGE plpgsql;

DROP trigger IF EXISTS refresh_inventory_customer_m_view ON customer.customer;

CREATE trigger refresh_inventory_customer_m_view
after INSERT OR UPDATE OR DELETE OR truncate
ON customer.customer FOR each statement
EXECUTE PROCEDURE customer.refresh_inventory_customer_m_view();