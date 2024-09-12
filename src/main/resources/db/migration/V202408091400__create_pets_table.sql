create table pet
(
    id SERIAL primary key,
    company_id BIGINT NOT NULL,
    name varchar(255) NOT NULL,
    type varchar(255) NOT NULL,
    date_of_arrival date default current_date,
    owner_id BIGINT
);
CREATE INDEX idx_pets_company_id ON pet (company_id);