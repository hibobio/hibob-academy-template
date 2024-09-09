create table Owner
(
    id SERIAL primary key,
    name varchar(255) not null,
    company_id int not null ,
    employee_id varchar(255) not null
);

CREATE INDEX idx_owner_company_id_employee_id on Owner(company_id, employee_id);

