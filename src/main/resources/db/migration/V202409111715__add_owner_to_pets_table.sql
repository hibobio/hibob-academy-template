ALTER TABLE Pets
ADD COLUMN if not exists owner_id BIGINT;
--

--INSERT INTO Pets( name, type, company_id, date_of_arrival ) VALUES ('mon', 'dog', 1, '2024-09-08' );
--INSERT INTO Pets( name, type, company_id, date_of_arrival ) VALUES ('don', 'dog', 2,'2024-09-08' );
--INSERT INTO Pets( name, type, company_id, date_of_arrival ) VALUES ('fon', 'cat', 1, '2024-09-08' );
--INSERT INTO Pets( name, type, company_id, date_of_arrival ) VALUES ('fon', 'cat', 1, '2022-09-08' );


--select *
--from Pets
--where type = 'dog';

--DELETE FROM Pets where id = '2';

--select *
--from Pets
--where date_of_arrival <= '2023-09-09';



