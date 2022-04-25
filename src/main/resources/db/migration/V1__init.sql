CREATE TABLE IF NOT EXISTS products (
    id bigserial,
    price int,
    name VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
    );

INSERT INTO products (name, price) VALUES ('Apple', 180),
                                          ('Orange', 210),
                                          ('Tomato', 220),
                                          ('Melon', 230),
                                          ('Pasta', 310),
                                          ('Telephone', 320),
                                          ('Pencil', 330),
                                          ('Book', 380),
                                          ('Tomato1', 410),
                                          ('Tomato2', 420),
                                          ('Tomato3', 430),
                                          ('Tomato4', 440),
                                          ('Tomato5', 450),
                                          ('Tomato6', 460),
                                          ('Tomato7', 470),
                                          ('Tomato8', 480),
                                          ('Tomato9', 490);