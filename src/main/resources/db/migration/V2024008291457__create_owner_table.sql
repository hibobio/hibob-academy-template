CREATE TABLE owner
(
    id SERIAL primary key,
    name varchar(255),
    type varchar(255),
    company_id integer,
    employee_id integer,
)

CREATE INDEX idx_company_id on owner(company_id);

CREATE INDEX idx_employee_id on owner(employee_id);

