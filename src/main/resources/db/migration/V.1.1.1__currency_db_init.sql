CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS currency
(
    id    BIGINT NOT NULL,
    code_key   VARCHAR(20),
    table_value VARCHAR(1),
    CONSTRAINT pk_currency PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS rates
(
    id   BIGINT NOT NULL,
    no   VARCHAR(255),
    effectiveDate VARCHAR(60),
    mid DOUBLE PRECISION,
    CONSTRAINT pk_rates PRIMARY KEY (id)
);