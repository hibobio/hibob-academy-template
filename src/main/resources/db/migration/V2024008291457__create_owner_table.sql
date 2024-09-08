CREATE TABLE Owner
(
    id SERIAL primary key,
    name varchar(255),
    type varchar(255),
    company_id int,
    employee_id int
);

CREATE INDEX idx_owner_company_id_employee_id on Owner(company_id, employee_id);

