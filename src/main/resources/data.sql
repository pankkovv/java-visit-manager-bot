INSERT INTO category (id, name)
VALUES (1, 'кольцо диджитал');
INSERT INTO category (id, name)
VALUES (2, 'кольцо красками');
INSERT INTO category (id, name)
VALUES (3, 'кулон диджитал');
INSERT INTO category (id, name)
VALUES (4, 'кулон красками');
INSERT INTO category (id, name)
VALUES (5, 'серьги');
INSERT INTO category (id, name)
VALUES (6, 'ожерелье');
INSERT INTO category (id, name)
VALUES (7, 'бумажный мерч');

INSERT INTO profile(id, username, description, path_file)
VALUES (1, 'pankkovv', 'New admin this bot', 'img/start.jpg');
INSERT INTO profile(id, username, description, path_file)
VALUES (2, 'eee_kisel', 'New designer this bot', 'img/start.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1, 'Кольцо диджитал',
        'Изображение для этого колечка рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой. ' ||
        'Особенности: точность деталей, возможность создания нескольких украшений с одинаковым рисунком.',
        1500, 1, 1, 'ORDER', 'img/ring_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (2, 'Кольцо красками',
        'Изображение для этого колечка наносится акриловыми красками прямо на изделие, затем покрывается ' ||
        'эпоксидной смолой. Особенности: живописность, рисунок выглядит «живым»',
        2000, 2, 1, 'ORDER', 'img/ring_paints_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (3, 'Кулон диджитал',
        'Изображение для этого кулона рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой.Особенности: точность деталей, возможность создания нескольких ' ||
        'украшений с одинаковым рисунком',
        1700, 3, 1, 'ORDER', 'img/pendant_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (4, 'Кулон красками',
        'Изображение для этого кулона наносится акриловыми красками прямо на изделие, затем покрывается эпоксидной смолой. ' ||
        'Особенности: живописность, рисунок выглядит «живым»',
        2200, 4, 1, 'ORDER', 'img/pendant_paints_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (5, 'Серьги',
        'Изображение наносится акриловыми красками прямо на сережки, затем покрывается эпоксидной смолой. ' ||
        'Рисунки получаются достаточно миниатюрными, поэтому для них подойдут более простые сюжеты',
        2200, 5, 1, 'ORDER', 'img/earrings_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (6, 'Ожерелье',
        'Изделие состоит из комбинации натуральных камней, деталей из нержавеющей стали, ' ||
        'а также кулона с авторским рисунком',
        3000, 6, 1, 'ORDER', 'img/necklace_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (7, 'Бумага',
        'В этот раздел входит большое количество мерча:
- открытки
- стикеры
- постеры
- переводные татуировки
- отрывные блоки для заметок
- слайдеры для ногтей',
        100, 7, 1, 'ORDER', 'img/paper_bot.jpeg');



INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (8, 'Кольцо диджитал',
        'Изображение для этого колечка рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой. ' ||
        'Особенности: точность деталей, возможность создания нескольких украшений с одинаковым рисунком.',
        1500, 1, 1, 'STOCK', 'img/ring_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (9, 'Кольцо красками',
        'Изображение для этого колечка наносится акриловыми красками прямо на изделие, затем покрывается ' ||
        'эпоксидной смолой. Особенности: живописность, рисунок выглядит «живым»',
        2000, 2, 1, 'STOCK', 'img/ring_paints_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (10, 'Кулон диджитал',
        'Изображение для этого кулона рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой.Особенности: точность деталей, возможность создания нескольких ' ||
        'украшений с одинаковым рисунком',
        1700, 3, 1, 'STOCK', 'img/pendant_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (11, 'Кулон красками',
        'Изображение для этого кулона наносится акриловыми красками прямо на изделие, затем покрывается эпоксидной смолой. ' ||
        'Особенности: живописность, рисунок выглядит «живым»',
        2200, 4, 1, 'STOCK', 'img/pendant_paints_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (12, 'Серьги',
        'Изображение наносится акриловыми красками прямо на сережки, затем покрывается эпоксидной смолой. ' ||
        'Рисунки получаются достаточно миниатюрными, поэтому для них подойдут более простые сюжеты',
        2200, 5, 1, 'STOCK', 'img/earrings_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (13, 'Ожерелье',
        'Изделие состоит из комбинации натуральных камней, деталей из нержавеющей стали, ' ||
        'а также кулона с авторским рисунком',
        3000, 6, 1, 'STOCK', 'img/necklace_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (14, 'Бумага',
        'В этот раздел входит большое количество мерча:
- открытки
- стикеры
- постеры
- переводные татуировки
- отрывные блоки для заметок
- слайдеры для ногтей',
        100, 7, 1, 'STOCK', 'img/paper_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (15, 'Кольцо диджитал',
        'Изображение для этого колечка рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой. ' ||
        'Особенности: точность деталей, возможность создания нескольких украшений с одинаковым рисунком.',
        1500, 1, 1, 'STOCK', 'img/ring_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (16, 'Кольцо красками',
        'Изображение для этого колечка наносится акриловыми красками прямо на изделие, затем покрывается ' ||
        'эпоксидной смолой. Особенности: живописность, рисунок выглядит «живым»',
        2000, 2, 1, 'STOCK', 'img/ring_paints_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (17, 'Кулон диджитал',
        'Изображение для этого кулона рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой.Особенности: точность деталей, возможность создания нескольких ' ||
        'украшений с одинаковым рисунком',
        1700, 3, 1, 'STOCK', 'img/pendant_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (18, 'Кулон красками',
        'Изображение для этого кулона наносится акриловыми красками прямо на изделие, затем покрывается эпоксидной смолой. ' ||
        'Особенности: живописность, рисунок выглядит «живым»',
        2200, 4, 1, 'STOCK', 'img/pendant_paints_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (19, 'Серьги',
        'Изображение наносится акриловыми красками прямо на сережки, затем покрывается эпоксидной смолой. ' ||
        'Рисунки получаются достаточно миниатюрными, поэтому для них подойдут более простые сюжеты',
        2200, 5, 1, 'STOCK', 'img/earrings_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (20, 'Ожерелье',
        'Изделие состоит из комбинации натуральных камней, деталей из нержавеющей стали, ' ||
        'а также кулона с авторским рисунком',
        3000, 6, 1, 'STOCK', 'img/necklace_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (21, 'Бумага',
        'В этот раздел входит большое количество мерча:
- открытки
- стикеры
- постеры
- переводные татуировки
- отрывные блоки для заметок
- слайдеры для ногтей',
        100, 7, 1, 'STOCK', 'img/paper_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (22, 'Кольцо диджитал',
        'Изображение для этого колечка рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой. ' ||
        'Особенности: точность деталей, возможность создания нескольких украшений с одинаковым рисунком.',
        1500, 1, 1, 'STOCK', 'img/ring_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (23, 'Кольцо красками',
        'Изображение для этого колечка наносится акриловыми красками прямо на изделие, затем покрывается ' ||
        'эпоксидной смолой. Особенности: живописность, рисунок выглядит «живым»',
        2000, 2, 1, 'STOCK', 'img/ring_paints_bot.jpeg');