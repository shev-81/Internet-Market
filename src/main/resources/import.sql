DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, price int, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO products (name, price) VALUES ('Apple', 180), ('Orange', 280), ('Tomato', 480);
