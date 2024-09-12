create table owner
(
    id UUID primary key default gen_random_uuid(),
    company_id BIGINT NOT NULL,
    employee_id varchar NOT NULL,
    name varchar(255) NOT NULL
);

