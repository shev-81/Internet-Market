CREATE TABLE IF NOT EXISTS categories (
    id bigserial PRIMARY KEY,
    name VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
    );

CREATE TABLE IF NOT EXISTS products (
    id bigserial PRIMARY KEY,
    price numeric (8, 2) not null,
    name VARCHAR(255),
    category_id bigserial not null references categories (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
    );

CREATE TABLE IF NOT EXISTS orders (
                        id              bigserial primary key,
                        username        varchar(255) not null,
                        total_price     numeric (8, 2) not null,
                        address         varchar(255),
                        phone           varchar(255),
                        created_at      timestamp default current_timestamp,
                        updated_at      timestamp default current_timestamp
);

CREATE TABLE IF NOT EXISTS order_items (
                             id                      bigserial primary key,
                             product_id              bigserial not null references products (id),
                             order_id                bigserial not null references orders (id),
                             quantity                int not null,
                             price_per_product       numeric (8, 2) not null,
                             price                   numeric (8, 2) not null,
                             created_at        timestamp default current_timestamp,
                             updated_at        timestamp default current_timestamp
);

INSERT INTO categories (name) VALUES ('Technics'),
                                     ('Fruits');

INSERT INTO products (category_id, name, price) VALUES (2, 'Apple', 180.11),
                                          (2, 'Orange', 210.12),
                                          (2, 'Tomato', 220.22),
                                          (2, 'Melon', 230.11),
                                          (2, 'Pasta', 310.23),
                                          (1, 'Telephone', 320.23),
                                          (1, 'Laser Pencil', 330.55),
                                          (1, 'Note Book', 380.23),
                                          (2, 'Tomato1', 410.45),
                                          (2, 'Tomato2', 420.43),
                                          (2, 'Tomato3', 430.54),
                                          (2, 'Tomato4', 440.65),
                                          (2, 'Tomato5', 450.45),
                                          (2, 'Tomato6', 460.10),
                                          (2, 'Tomato7', 470.11),
                                          (1, 'Key Board', 480.23),
                                          (1, 'CPU', 490.22);