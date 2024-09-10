CREATE TABLE owner
(
    id SERIAL PRIMARY KEY,
    name varchar(64) NOT NULL,
    company_id INT NOT NULL,
    employee_id varchar(64) NOT NULL
);

CREATE INDEX idx_owner_company_employee_id ON owner(company_id, employee_id);