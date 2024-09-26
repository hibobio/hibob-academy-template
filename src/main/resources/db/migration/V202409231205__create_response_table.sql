create table if not exists response
(
    id          BIGSERIAL PRIMARY KEY,
    feedback_id BIGINT NOT NULL,
    response    TEXT   NOT NULL,
    employee_id BIGINT NOT NULL,
    date        DATE   NOT NULL DEFAULT CURRENT_DATE
);