CREATE TABLE IF NOT EXISTS categories (
    id bigserial PRIMARY KEY,
    name VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
    );

CREATE TABLE IF NOT EXISTS products (
    id bigserial PRIMARY KEY,
    price int,
    name VARCHAR(255),
    category_id bigserial not null references categories (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
    );

CREATE TABLE orders (
                        id              bigserial primary key,
                        username        varchar(255) not null,
                        total_price     int not null,
                        address         varchar(255),
                        phone           varchar(255),
                        created_at      timestamp default current_timestamp,
                        updated_at      timestamp default current_timestamp
);

CREATE TABLE order_items (
                             id                      bigserial primary key,
                             product_id              bigserial not null references products (id),
                             order_id                bigserial not null references orders (id),
                             quantity                int not null,
                             price_per_product       int not null,
                             price                   int not null,
                             created_at        timestamp default current_timestamp,
                             updated_at        timestamp default current_timestamp
);

INSERT INTO categories (name) VALUES ('Technics'),
                                     ('Fruits');

INSERT INTO products (category_id, name, price) VALUES (2, 'Apple', 180),
                                          (2, 'Orange', 210),
                                          (2, 'Tomato', 220),
                                          (2, 'Melon', 230),
                                          (2, 'Pasta', 310),
                                          (1, 'Telephone', 320),
                                          (1, 'Laser Pencil', 330),
                                          (1, 'Note Book', 380),
                                          (2, 'Tomato1', 410),
                                          (2, 'Tomato2', 420),
                                          (2, 'Tomato3', 430),
                                          (2, 'Tomato4', 440),
                                          (2, 'Tomato5', 450),
                                          (2, 'Tomato6', 460),
                                          (2, 'Tomato7', 470),
                                          (1, 'Key Board', 480),
                                          (1, 'CPU', 490);