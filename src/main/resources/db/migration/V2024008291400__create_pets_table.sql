CREATE TABLE Pets
(
    id SERIAL primary key,
    name varchar(255),
    type varchar(255),
    company_id int,
    date_of_arrival date
);

CREATE INDEX idx_pet_company_id on Pets(company_id);
--

--INSERT INTO Pets( name, type, company_id, date_of_arrival ) VALUES ('mon', 'dog', 1, '2024-09-08' );
--INSERT INTO Pets( name, type, company_id, date_of_arrival ) VALUES ('don', 'dog', 2,'2024-09-08' );
--INSERT INTO Pets( name, type, company_id, date_of_arrival ) VALUES ('fon', 'cat', 1, '2024-09-08' );


--select
--from Pets
--where type = 'dog'

--DELETE FROM Pets where id = '2'

