create table orders
(
    id bigserial primary key,
    phone varchar(30) not null,
    address varchar(80) not null,
    user_id bigint references users (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);