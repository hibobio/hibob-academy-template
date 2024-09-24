-- Insert companies
INSERT INTO company (name)
VALUES ('Friends'),
       ('Seinfeld');

-- Insert employees for Friends company (id=1)
INSERT INTO employees (first_name, last_name, role, company_id)
VALUES ('Rachel', 'Green', 'ADMIN', 1),
       ('Ross', 'Geller', 'MANAGER', 1),
       ('Monica', 'Geller', 'MANAGER', 1),
       ('Chandler', 'Bing', 'MANAGER', 1),
       ('Joey', 'Tribbiani', 'EMPLOYEE', 1),
       ('Phoebe', 'Buffay', 'EMPLOYEE', 1),
       ('Gunther', 'Central', 'EMPLOYEE', 1),
       ('Janice', 'Hosenstein', 'EMPLOYEE', 1),
       ('Mike', 'Hannigan', 'EMPLOYEE', 1),
       ('David', 'Scientist', 'EMPLOYEE', 1),
       ('Ben', 'Geller', 'EMPLOYEE', 1),
       ('Carol', 'Willick', 'EMPLOYEE', 1),
       ('Susan', 'Bunch', 'EMPLOYEE', 1),
       ('Emily', 'Waltham', 'EMPLOYEE', 1),
       ('Richard', 'Burke', 'EMPLOYEE', 1);

-- Insert employees for Seinfeld company (id=2)
INSERT INTO employees (first_name, last_name, role, company_id)
VALUES ('Jerry', 'Seinfeld', 'ADMIN', 2),
       ('George', 'Costanza', 'MANAGER', 2),
       ('Elaine', 'Benes', 'MANAGER', 2),
       ('Cosmo', 'Kramer', 'MANAGER', 2),
       ('Newman', 'Postman', 'EMPLOYEE', 2),
       ('Frank', 'Costanza', 'EMPLOYEE', 2),
       ('Estelle', 'Costanza', 'EMPLOYEE', 2),
       ('David', 'Puddy', 'EMPLOYEE', 2),
       ('Kenny', 'Bania', 'EMPLOYEE', 2),
       ('Jackie', 'Chiles', 'EMPLOYEE', 2),
       ('Sue', 'Ellen', 'EMPLOYEE', 2),
       ('Morty', 'Seinfeld', 'EMPLOYEE', 2),
       ('Helen', 'Seinfeld', 'EMPLOYEE', 2),
       ('Uncle', 'Leo', 'EMPLOYEE', 2),
       ('Babu', 'Bhatt', 'EMPLOYEE', 2);

-- Insert employees for Friends company (id=1, role=hr)
INSERT INTO employees (first_name, last_name, role, company_id)
VALUES ('Liam', 'Smith', 'HR', 1),
       ('Noah', 'Johnson', 'HR', 1),
       ('Emma', 'Williams', 'HR', 1),
       ('Olivia', 'Brown', 'HR', 1);

-- Insert employees for Seinfeld company (id=2, role=hr)
INSERT INTO employees (first_name, last_name, role, company_id)
VALUES ('Ava', 'Jones', 'HR', 2),
       ('Sophia', 'Garcia', 'HR', 2),
       ('Isabella', 'Martinez', 'HR', 2),
       ('Mason', 'Miller', 'HR', 2);