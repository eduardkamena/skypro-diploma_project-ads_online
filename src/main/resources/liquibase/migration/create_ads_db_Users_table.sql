-- liquibase formatted sql

--changeset gitWestender:1
CREATE SEQUENCE IF NOT EXISTS user_id_seq START 1 INCREMENT 1;

CREATE TABLE users
(
    id       BIGINT PRIMARY KEY DEFAULT nextval('user_id_seq'),
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    phone    VARCHAR(255) UNIQUE NOT NULL,
    role     VARCHAR(50)         NOT NULL,
    image    VARCHAR(255)
);