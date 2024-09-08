CREATE TABLE pet
(
    id SERIAL primary key,
    name varchar(255),
    type varchar(255),
    company_id integer,
    date_of_arrival date,
)

CREATE INDEX idx_company_id on pet(company_id);

INSERT INTO pet( name, type, company_id, date_of_arrival ) VALUES ("mon", "dog", 1, "2024-09-08" );
INSERT INTO pet( name, type, company_id, date_of_arrival ) VALUES ("don", "dog", 2, "2024-09-08" );
INSERT INTO pet( name, type, company_id, date_of_arrival ) VALUES ("fon", "cat", 1, "2024-09-08" );

/*select
from pet
where type = "dog"

DELETE FROM PET where id = "2"

