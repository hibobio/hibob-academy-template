create table owner
(
    id UUID primary key default gen_random_uuid(),
    company_id BIGINT NOT NULL,
    employee_id varchar NOT NULL,
    name varchar(255) NOT NULL
);

CREATE INDEX idx_owner_company_id_employee_id on owner(company_id, employee_id);
