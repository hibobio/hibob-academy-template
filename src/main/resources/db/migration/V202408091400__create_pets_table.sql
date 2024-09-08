create table pets
(
    id SERIAL primary key ,
    name text,
    type varchar,
    company_id int,
    date_of_arrival date
)

-- INSERT INTO pets