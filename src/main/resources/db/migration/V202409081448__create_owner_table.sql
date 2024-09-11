CREATE TABLE owner
(
    id SERIAL PRIMARY KEY,
    name varchar(64) NOT NULL,
    company_id BIGINT NOT NULL,
    employee_id varchar(64) NOT NULL
);

CREATE UNIQUE INDEX idx_owner_company_employee_id ON owner(company_id, employee_id);