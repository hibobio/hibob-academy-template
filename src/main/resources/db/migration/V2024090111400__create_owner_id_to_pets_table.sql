ALTER TABLE pets
    ADD owner_id BIGINT;

CREATE UNIQUE INDEX idx_pets_company_id ON pets(company_id);