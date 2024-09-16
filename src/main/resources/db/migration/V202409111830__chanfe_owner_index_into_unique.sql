DROP INDEX IF EXISTS idx_owner_company_id_employee_id;

CREATE UNIQUE INDEX idx_owner_company_id_employee_id ON owner(company_id, employee_id);
