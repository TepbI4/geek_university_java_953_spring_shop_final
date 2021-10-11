create table orders
(
    id bigserial primary key,
    phone varchar(30) not null,
    address varchar(80) not null,
    user_id bigint references users (id),
    total integer,
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