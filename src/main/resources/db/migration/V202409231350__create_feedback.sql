CREATE TABLE feedback
(
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT,
    content VARCHAR(225) NOT NULL,
    is_anonymous BOOLEAN NOT NULL,
    status VARCHAR(30) NOT NULL,
    company_id BIGINT NOT NULL,
    date DATE DEFAULT CURRENT_DATE
);

CREATE INDEX idx_feedback_id_company_id on pets(id, company_id);