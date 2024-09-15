create table pets
(
    id BIGSERIAL primary key,
    name varchar(255) not null,
    type varchar(255) not null,
    company_id int not null,
    date_of_arrival date default CURRENT_DATE
);

CREATE INDEX idx_pets_company_id on pets(company_id);
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



