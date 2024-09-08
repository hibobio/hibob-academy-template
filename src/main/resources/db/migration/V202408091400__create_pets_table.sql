create table pets
(
    id SERIAL primary key ,
    name varchar(255),
    type varchar(255),
    company_id integer,
    date_of_arrival date
);

INSERT INTO pets VALUES (1,"Murphy","dog",1,"2024-09-08");
INSERT INTO pets VALUES(2,"Pepe","dog",1,"2024-09-09");
INSERT INTO pets VALUES(1,"Garfield","cat",2,"2024-09-08");

-- SELECT * from pets where type = 'dog';

delete from pets where name='Garfield'

-- SELECT * from pets where date_of_arrival<'2023-09-08';