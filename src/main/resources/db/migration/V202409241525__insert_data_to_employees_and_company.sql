-- Insert companies
INSERT INTO company (name)
VALUES ('Friends'),
       ('Seinfeld');

-- Insert employees for Friends company (id=1)
-- Insert employees for Friends company (id=1)
INSERT INTO employees (first_name, last_name, role, company_id, department)
VALUES ('Rachel', 'Green', 'admin', 1, 'development'),
       ('Ross', 'Geller', 'manager', 1, 'it'),
       ('Monica', 'Geller', 'manager', 1, 'data_analytics'),
       ('Chandler', 'Bing', 'manager', 1, 'ux'),
       ('Joey', 'Tribbiani', 'hr', 1, 'development'),
       ('Phoebe', 'Buffay', 'employee', 1, 'it'),
       ('Gunther', 'Central', 'employee', 1, 'data_analytics'),
       ('Janice', 'Hosenstein', 'hr', 1, 'ux'),
       ('Mike', 'Hannigan', 'employee', 1, 'development'),
       ('David', 'Scientist', 'employee', 1, 'it'),
       ('Ben', 'Geller', 'employee', 1, 'data_analytics'),
       ('Carol', 'Willick', 'employee', 1, 'ux'),
       ('Susan', 'Bunch', 'employee', 1, 'development'),
       ('Emily', 'Waltham', 'hr', 1, 'it'),
       ('Richard', 'Burke', 'employee', 1, 'data_analytics');

-- Insert employees for Seinfeld company (id=2)
INSERT INTO employees (first_name, last_name, role, company_id, department)
VALUES ('Jerry', 'Seinfeld', 'admin', 2, 'development'),
       ('George', 'Costanza', 'manager', 2, 'it'),
       ('Elaine', 'Benes', 'manager', 2, 'data_analytics'),
       ('Cosmo', 'Kramer', 'manager', 2, 'ux'),
       ('Newman', 'Postman', 'hr', 2, 'development'),
       ('Frank', 'Costanza', 'employee', 2, 'it'),
       ('Estelle', 'Costanza', 'employee', 2, 'data_analytics'),
       ('David', 'Puddy', 'employee', 2, 'ux'),
       ('Kenny', 'Bania', 'hr', 2, 'development'),
       ('Jackie', 'Chiles', 'employee', 2, 'it'),
       ('Sue', 'Ellen', 'employee', 2, 'data_analytics'),
       ('Morty', 'Seinfeld', 'employee', 2, 'ux'),
       ('Helen', 'Seinfeld', 'hr', 2, 'development'),
       ('Uncle', 'Leo', 'employee', 2, 'it'),
       ('Babu', 'Bhatt', 'employee', 2, 'data_analytics');



