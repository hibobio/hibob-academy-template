create table pets
(
    id UUID primary key default gen_random_uuid(),
    name varchar(255),
    type varchar(255),
    company_id UUID,
    date_of_arrival date
);


CREATE INDEX idx_company_pets_id on pets(company_id);

/*
 INSERT INTO pets (name, type, company_id, date_of_arrival)
VALUES ('Jerry', 'Belgian Shepherd', '2a63b47e-b536-454d-8db0-9be5f30cffc8', '09-08-2024')

INSERT INTO pets (name, type, company_id, date_of_arrival)
VALUES ('Johans', 'Dutch Shepherd', 'dda6bebd-46c0-4528-bf63-77ae5cb437e5', '09-08-2024')
 ------
SELECT * FROM pets where type = 'Belgian Shepherd'

 ---------
delete from pets VWHERE id = 2e667763-6e68-41f7-9c7f-52dfca3e6a7c
----------

 SELECT * FROM pets where date_of_arrival < '09-08-2023'

 */