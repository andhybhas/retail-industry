INSERT INTO Users (Id, user_name, password, active) VALUES
('u001', 'andhy', 'admin', true),
('u002', 'bhaskoro', 'admin', true);

INSERT INTO User (user_id, user_name, user_balance) VALUES
(1,'andhy', 25000),
(2,'bhaskoro', 50000);

INSERT INTO Product (product_id, product_price, product_name, product_stock) VALUES
(1, 10000, 'Coca Cola', 20),
(2, 10000, 'Sprite', 20),
(3, 10000, 'Fanta', 25),
(4, 5000, 'Nutri Boost', 30);

insert into Transactions (transaction_id, user_id, user_name, product_id, product_name, transaction_total, transaction_date, transaction_qty, user_balance) VALUES
(1, 1, 'andhy', 1, 'Coca Cola', 20000, '2019-06-17', 2, 25000),
(2, 2, 'bhaskoro', 4, 'Nutri Boost', 25000, '2019-06-17', 6, 50000);