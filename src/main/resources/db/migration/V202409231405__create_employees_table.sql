create table IF NOT EXISTS employees
(
    id UUID primary key default gen_random_uuid(),
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    role varchar(15) NOT NULL,
    department varchar(50) NOT NULL,
    company_id UUID NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_department on employees(department);