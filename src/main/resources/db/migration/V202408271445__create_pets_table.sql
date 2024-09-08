CREATE TABLE pets
(
    id SERIAL PRIMARY KEY,
    name varchar[64] NOT NULL,
    type varchar[64] NOT NULL,
    company_id INT NOT NULL,
    date_of_arrival DATE
);

CREATE INDEX idx_pets_company_id ON pets(company_id);

INSERT INTO pets(name, type, company_id, date_of_arrival)
VALUES ('Tom', 'Cat', 'Tom&Jerry', '2024-09-08');

