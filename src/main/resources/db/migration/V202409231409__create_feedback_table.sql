create table IF NOT EXISTS feedback
(
    id UUID primary key default gen_random_uuid(),
    employee_id UUID,
    creation_date DATE default CURRENT_DATE,
    company_id UUID NOT NULL,
    status varchar(15) NOT NULL,
    feedback_message TEXT NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_creation_date on feedback(creation_date);
CREATE INDEX IF NOT EXISTS idx_employee_id on feedback(employee_id);
