create table owner
(
    id bigserial primary key,
    name varchar NOT NULL,
    company_id BIGINT NOT NULL,
    employee_id varchar NOT NULL
);
CREATE UNIQUE INDEX idx_owners_company_id_employee_id ON owner (company_id, employee_id);