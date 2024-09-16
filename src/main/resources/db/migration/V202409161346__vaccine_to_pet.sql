create table vaccineToPet
(
    id BIGSERIAL PRIMARY KEY,
    pet_id BIGINT NOT NULL,
    vaccination_date DATE NOT NULL
);