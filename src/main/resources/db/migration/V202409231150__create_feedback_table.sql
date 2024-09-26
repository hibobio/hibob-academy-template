create table if not exists feedback
(
    id          BIGSERIAL PRIMARY KEY,
    feedback    TEXT         NOT NULL,
    employee_id BIGINT,
    company_id  BIGINT       NOT NULL,
    date        DATE         NOT NULL DEFAULT CURRENT_DATE,
    department  VARCHAR(100) NOT NULL,
    status      VARCHAR(50)  NOT NULL
);

CREATE INDEX idx_company_id ON feedback (company_id);
CREATE INDEX idx_date ON feedback (date);
CREATE INDEX idx_department ON feedback (department);
CREATE INDEX idx_employee_id ON feedback (employee_id);

