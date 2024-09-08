CREATE TABLE owner
(
    id SERIAL PRIMARY KEY,
    name varchar(64) NOT NULL,
    company_id INT NOT NULL,
    employee_id INT NOT NULL
);

CREATE INDEX idx_owner_company_id ON owner(company_id);
CREATE INDEX idx_owner_employee_id ON owner(employee_id);
