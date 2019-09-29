CREATE DATABASE IF NOT EXISTS SHOPS;
USE SHOPS;
CREATE SCHEMA IF NOT EXISTS SHOPS;
DROP TABLE IF EXISTS PRODUCTS;

CREATE TABLE IF NOT EXISTS PRODUCTS (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    price VARCHAR (150),
    status VARCHAR(20),
    category_id INT
);

INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 1);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 1);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 1);

INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 2);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 2);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 2);

INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 3);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 3);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 3);

INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 4);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 4);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 4);

INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 5);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 5);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 5);

INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 6);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 6);
INSERT INTO PRODUCTS (title, price, status, category_id) values ("Title", "100.500", "AVAILABLE", 6);