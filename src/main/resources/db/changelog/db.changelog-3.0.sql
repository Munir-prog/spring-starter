--liquibase formatted sql

--changeset munir:1
ALTER TABLE users
    ADD COLUMN image VARCHAR(64);
--changeset munir:2
ALTER TABLE users_aud
    ADD COLUMN image VARCHAR(64);