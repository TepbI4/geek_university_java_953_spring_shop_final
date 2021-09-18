CREATE TABLE products
(
    id    bigserial PRIMARY KEY,
    title VARCHAR(255),
    price numeric(6, 2)
);
INSERT INTO products (title, price)
VALUES ('Bread', 40),
       ('Milk', 80),
       ('Cheese', 150),
       ('Sausage', 250),
       ('Cookies', 90),
       ('Tea', 200),
       ('Yogurt', 45),
       ('Salmon', 500),
       ('Sugar', 70),
       ('Salt', 99),
       ('Eggs', 82),
       ('Chocolate', 149),
       ('Cream', 175),
       ('Butter', 66),
       ('Beer', 94),
       ('Coffee', 261),
       ('Corn', 56),
       ('Porridge', 138),
       ('Tomato', 49),
       ('Wine', 678);