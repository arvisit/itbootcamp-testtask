/*
DROP DATABASE IF EXISTS itbootcamp;
 */

/*
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
*/

CREATE DATABASE IF NOT EXISTS itbootcamp;

CREATE TABLE IF NOT EXISTS itbootcamp.roles (
    id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name VARCHAR(25) UNIQUE NOT NULL
);

INSERT INTO itbootcamp.roles (name) 
VALUES ('ADMINISTRATOR'),
    ('SALE_USER'),
    ('CUSTOMER_USER'),
    ('SECURE_API_USER');

CREATE TABLE IF NOT EXISTS itbootcamp.users (
    id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    last_name VARCHAR(40) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    second_name VARCHAR(40)NOT NULL,
    email VARCHAR(50) NOT NULL,
    role_id INTEGER UNSIGNED NOT NULL,
    FOREIGN KEY(role_id) REFERENCES itbootcamp.roles(id)
);

INSERT INTO itbootcamp.users (last_name, first_name, second_name, email, role_id)
VALUES ('Black', 'Jack', 'Alan', 'bljk@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'ADMINISTRATOR')),
    ('Green', 'Wolf', 'William', 'grwlf@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'CUSTOMER_USER')),
    ('Freeman', 'Christopher', 'Brian', 'frchr@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'CUSTOMER_USER')),
    ('Peterson', 'Ann', 'Virginia', 'petan@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'CUSTOMER_USER')),
    ('Elizando', 'Maria', 'Taina', 'melizando@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'SECURE_API_USER')),
    ('Wilson', 'Daren', 'Frank', 'darenwilson@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'CUSTOMER_USER')),
    ('Grey', 'Edmund', 'Joseph', 'edgrey@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'CUSTOMER_USER')),
    ('Castle', 'Jean', 'Vanessa', 'jvcastle@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'CUSTOMER_USER')),
    ('Kurofski', 'Lillian', 'Rose', 'lilkuroffski@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'CUSTOMER_USER')),
    ('Kelevra', 'Slevin', 'Boun', 'slklvra@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'CUSTOMER_USER')),
    ('White', 'Walter', 'Hartwell', 'brbd@mail.com', (SELECT id FROM itbootcamp.roles r WHERE r.name = 'SALE_USER'));
