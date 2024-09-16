CREATE TABLE vaccine_to_pet
(
    vaccine_id BIGINT PRIMARY KEY NOT NULL,
    pet_id UUID NOT NULL,
    vaccination_date DATE NOT NULL
)