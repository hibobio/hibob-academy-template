create table owner
(
    id UUID primary key default gen_random_uuid(),
    company_id UUID NOT NULL,
    employee_id UUID NOT NULL,
    name varchar(255) NOT NULL,
    type varchar(255) NOT NULL,
    date_of_arrival date DEFAULT CURRENT_DATE
);

CREATE INDEX idx_owner_company_id on owner(company_id);
CREATE INDEX idx_owner_employee_id on owner(employee_id)
