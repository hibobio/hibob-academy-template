create table pets
(
    id SERIAL primary key ,
    name varchar(255),
    type varchar(255),
    company_id integer,
    date_of_arrival date default current_date
);

CREATE INDEX idx_company_id ON pets (company_id);

-- INSERT INTO pets VALUES (1,"Murphy","dog",1,"2024-09-08");
-- INSERT INTO pets VALUES(2,"Pepe","dog",1,"2024-09-09");
-- INSERT INTO pets VALUES(1,"Garfield","cat",2,"2024-09-08");

-- SELECT * from pets where type = 'dog';
-- delete from pets where id=1
-- SELECT *
-- FROM pets
-- WHERE date_of_arrival < CURDATE() - INTERVAL 1 YEAR;