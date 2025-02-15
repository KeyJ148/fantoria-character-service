--liquibase formatted sql
--changeset keyj148:001-create-character-table

CREATE TABLE character(
    id UUID PRIMARY KEY
);

