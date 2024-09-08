create table owner
(
    id UUID primary key default gen_random_uuid(),
    company_id UUID,
    employee_id UUID,
    name varchar(255),
    type varchar(255),
    date_of_arrival date
);

CREATE INDEX idx_company_owner_id on owner(company_id);

/*
 Add migration for create owner table with index on company id and employee id
owner - id, name, company id, employee id

 */
