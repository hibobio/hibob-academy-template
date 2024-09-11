CREATE TABLE pet
(
    id SERIAL PRIMARY KEY,
    name varchar(64) NOT NULL,
    type varchar(64) NOT NULL,
    company_id BIGINT NOT NULL,
    date_of_arrival DATE DEFAULT CURRENT_DATE
);

CREATE UNIQUE INDEX idx_pets_company_id ON pet(company_id);

-- INSERT INTO pets(name, type, company_id, date_of_arrival)
-- VALUES ('Tom', 'Cat', 1, '2024-09-08');

-- INSERT INTO pets(name, type, company_id, date_of_arrival)
-- VALUES ('Ralph', 'Dog', 2, '2023-08-08');

-- INSERT INTO pets(name, type, company_id, date_of_arrival)
-- VALUES ('Garfield', 'Cat', 1, '2024-09-08');

-- SELECT DISTINCT type
-- FROM pets

-- DELETE FROM pets
-- WHERE id = 2

-- SELECT *
-- FROM pets
-- WHERE date_of_arrival < NOW() - INTERVAL '1 year'

