ALTER TABLE employees
    ADD COLUMN department VARCHAR(50) NOT NULL;

CREATE INDEX idx_employees_department ON employees (department);
