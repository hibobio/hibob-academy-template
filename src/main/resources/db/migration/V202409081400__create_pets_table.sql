create table pets
(
    id UUID primary key default gen_random_uuid(),
    name varchar(255) NOT NULL,
    type varchar(255) NOT NULL,
    company_id UUID NOT NULL,
    date_of_arrival date DEFAULT CURRENT_DATE
);


CREATE INDEX idx_pets_company_id on pets(company_id);

/*
 INSERT INTO pets (name, type, company_id, date_of_arrival)
VALUES ('Jerry', 'Dog', '2a63b47e-b536-454d-8db0-9be5f30cffc8', '09-08-2024')

INSERT INTO pets (name, type, company_id, date_of_arrival)
VALUES ('Johans', 'Dog', 'dda6bebd-46c0-4528-bf63-77ae5cb437e5', '09-08-2024')
 ------
SELECT * FROM pets where type = 'Dog'

 ---------
delete from pets WHERE id = "2e667763-6e68-41f7-9c7f-52dfca3e6a7c"
----------

SELECT * FROM pets where date_of_arrival < CURRENT_DATE - INTERVAL '1 YEAR'
 */