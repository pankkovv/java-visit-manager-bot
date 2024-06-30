INSERT INTO category (id, name)
VALUES (1, 'Кольцо диджитал');
INSERT INTO category (id, name)
VALUES (2, 'Кольцо красками');
INSERT INTO category (id, name)
VALUES (3, 'Кулон диджитал');
INSERT INTO category (id, name)
VALUES (4, 'Кулон красками');
INSERT INTO category (id, name)
VALUES (5, 'Серьги');
INSERT INTO category (id, name)
VALUES (6, 'Ожерелье');
INSERT INTO category (id, name)
VALUES (7, 'Бумажный мерч');

INSERT INTO profile(id, username, description, path_file)
VALUES (1, 'pankkovv', 'New admin this bot', 'img/start.jpg');
INSERT INTO profile(id, username, description, path_file)
VALUES (2, 'eee_kisel', 'New designer this bot', 'img/start.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1, 'Кольцо', 'Кольцо цифровое для заказа', 100, 1, 1, 'ORDER', 'img/start.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (2, 'Кольцо', 'Кольцо красками для заказа', 100, 2, 1, 'ORDER', 'img/start.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (3, 'Кулон', 'Кулон диджитал для заказа', 100, 3, 1, 'ORDER', 'img/start.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (4, 'Кулон', 'Кулон красками для заказа', 100, 4, 1, 'ORDER', 'img/start.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (5, 'Серьги', 'Серьги для заказа', 100, 5, 1, 'ORDER', 'img/start.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (6, 'Ожерелье', 'Ожерелье для заказа', 100, 6, 1, 'ORDER', 'img/start.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (7, 'Бумага', 'Бумажный мерч для заказа', 100, 7, 1, 'ORDER', 'img/start.jpg');