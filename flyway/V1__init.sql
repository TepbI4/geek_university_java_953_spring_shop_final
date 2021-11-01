CREATE TABLE products
(
    id    bigserial PRIMARY KEY,
    title VARCHAR(255),
    price numeric(6, 2)
);
INSERT INTO products (title, price)
VALUES ('Bread', 40.15),
       ('Milk', 80.99),
       ('Cheese', 150.25),
       ('Sausage', 250.98),
       ('Cookies', 90.00),
       ('Tea', 200.01),
       ('Yogurt', 45.02),
       ('Salmon', 500.00),
       ('Sugar', 70.50),
       ('Salt', 99.99),
       ('Eggs', 82.31),
       ('Chocolate', 149.64),
       ('Cream', 175.45),
       ('Butter', 66.99),
       ('Beer', 94.49),
       ('Coffee', 261.10),
       ('Corn', 56.50),
       ('Porridge', 138.74),
       ('Tomato', 49.50),
       ('Wine', 678.00);

create table users
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

CREATE TABLE product_comments
(
    id    bigserial PRIMARY KEY,
--     user_id bigint references users(id),
    product_id bigint references products(id),
    comment VARCHAR(255)
);

create table orders
(
    id bigserial primary key,
    phone varchar(30) not null,
    address varchar(80) not null,
    user_id bigint references users (id),
    total numeric(6, 2),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items
(
    id bigserial primary key,
    order_id bigint references orders (id),
    product_id bigint,
    quantity integer,
    price numeric(6, 2)
);