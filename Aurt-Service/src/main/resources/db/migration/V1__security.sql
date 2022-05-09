create table users (
                       id                    bigserial primary key,
                       username              varchar(30) not null unique,
                       password              varchar(80) not null,
                       email                 varchar(50) unique,
                       created_at            timestamp default current_timestamp,
                       updated_at            timestamp default current_timestamp
);

create table roles (
                       id                    serial primary key,
                       name                  varchar(50) not null,
                       created_at            timestamp default current_timestamp,
                       updated_at            timestamp default current_timestamp
);

CREATE TABLE users_roles (
                             user_id               bigint not null,
                             role_id               int not null,
                             primary key (user_id, role_id),
                             foreign key (user_id) references users (id),
                             foreign key (role_id) references roles (id)
);

insert into roles (name)
values
    ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MANAGER'), ('READER_BOOK');

insert into users (username, password, email)
values
    ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user1@gmail.com'),
    ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user2@gmail.com'),
    ('manager', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user3@gmail.com');

insert into users_roles (user_id, role_id)
values
    (1, 1),
    (2, 1),
    (2, 2),
    (2, 3),
    (3, 1),
    (3, 3);