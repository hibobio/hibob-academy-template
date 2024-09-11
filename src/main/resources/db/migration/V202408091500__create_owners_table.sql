create table owners
(
    id serial primary key NOT NULL,
    name varchar NOT NULL,
    company_id int NOT NULL,
    employee_id varchar NOT NULL
)
CREATE INDEX idx_owners_company_id_employee_id ON owners (company_id, employee_id);