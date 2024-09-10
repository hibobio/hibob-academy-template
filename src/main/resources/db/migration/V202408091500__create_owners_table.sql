create table owners
(
    id serial primary key,
    name varchar,
    company_id int,
    employee_id varchar
)

-- CREATE INDEX idx_company_id ON owners (company_id);
-- CREATE INDEX idx_employee_id ON pets (employee_id);