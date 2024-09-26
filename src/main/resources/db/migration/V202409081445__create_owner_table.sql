create table owner
(
    id BIGSERIAL primary key,
    name varchar(100) not null,
    company_id BIGINT,
    employee_id varchar(100) not null
);

CREATE UNIQUE INDEX idx_owner_company_id_and_employee_id ON owner(company_id, employee_id);
/*INSERT INTO pets ( name, type, company_id, date_of_arrival) VALUES
('Buddy', 'Dog', 101, '2023-01-15'),
('Mittens', 'Cat', 102, '2022-06-20'),
('Charlie', 'Dog', 103, '2021-08-05'),
('Whiskers', 'Cat', 104, '2023-03-10'),
('Goldie', 'Fish', 105, '2022-09-25');*/

/*SELECT * FROM pets WHERE type = 'Dog';*/

/*DELETE FROM pets WHERE id = 3;*/

/*SELECT * FROM pets WHERE date_of_arrival < (CURRENT_DATE-INTERVAL '1 YEAR')*/