create table pets
(
    id BIGSERIAL primary key,
    name varchar(100) not null ,
    type varchar(100) not null ,
    company_id BIGINT,
    date_of_arrival date DEFAULT CURRENT_DATE
);