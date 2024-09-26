create table if not exists company
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);