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

-- Profiles
INSERT INTO profile(id, username, description, path_file)
VALUES (1, 'pankkovv', 'New admin this bot', 'img/start.jpg');
INSERT INTO profile(id, username, description, path_file)
VALUES (2, 'eee_kisel', 'New designer this bot', 'img/start.jpg');

-- Pricelist
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


-- Products
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


-- Feedbacks
INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1, 'Снова отзывы на слайдеры 😍',
        'img/feedback_one_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (2, 'Безумно люблю смотреть ваши фото-отзывы 🥰',
        'img/feedback_two_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (3, 'Новый отзыв 🥰' ||
           'Ульяна, спасибочки огромное за такие клевые колечки! ' ||
           'В жизни они смотрятся ещё в 100 раз милее. Мне даже жалко их носить, настолько они классные 😃💖',
        'img/feedback_three_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (4, 'Новый отзыв с фотографией пушистика 🥰
Только вчера появилось время забрать посылку, зато сегодня уже ходила хвасталась😇
спасибо большое за Вашу работу, нам очень нравится)
успехов в творчестве😌',
        'img/feedback_four_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (5, 'Я хотела сказать тебе ещё раз спасибо за эти прекрасные колечки) ' ||
           'я смотрю на портреты моих девочек и глаз нарадоваться не может! ' ||
           'Получилось все как я хотела, спасибо большое❤️',
        'img/feedback_five_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (6, 'Большое спасибо за этого мили кота 💋💋🥰',
        'img/feedback_six_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (7, 'Пришли!!! Я влюблена 🤲
Теперь помогают мне на работе. Кулон - сплошное очарование, ' ||
           'найду цепочку и обязательно отпишусь в комментах
Ещё раз спасибо за труд и непременно берегите себя!',
        'img/feedback_seven_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (8, 'Я в любви!💖💖 Кулончик на 100/10, кошечка очень похожа, ' ||
           'такая же нежная и любвеобильная. Моя Шмонечка оценила🥰💖',
        'img/feedback_eight_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (9, 'Ульяна, спасибочки огромное за такие клевые колечки! ' ||
           'В жизни они смотрятся ещё в 100 раз милее. ' ||
           'Мне даже жалко их носить, настолько они классные 😃💖',
        'img/feedback_nine_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (10, '🥹🥹🥹🥹
прелесть! очень переживала, что будет видно пиксели из-за печати, но качество шикарное! ' ||
            'сейчас до дома доберусь и еще пофоткаю',
        'img/feedback_ten_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (11, 'Привет! Вчера забрала колечко. Вживую ещё лучше чем на фото😭❤️❤️❤️ ' ||
            'Просто невероятная милота, ожидания полностью оправданы',
        'img/feedback_eleven_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (12, 'добрый день! замоталась, только дошли руки написать отзыв)

огромное Вам спасибо!! колечко просто чудесное, очень качественно все, ' ||
            'носить одно удовольствие 🥺 отдельная благодарность за возможность вносить свои правки/корректировки 🫶🏽

уже всем друзьям посоветовала Вас, да и уверена, что сама ещё ни раз обращусь 💗',
        'img/feedback_twelve_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (13, 'Вот такое прекрасное колечко ❤️',
        'img/feedback_thirteen_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (14, 'здравствуйте, сложно было малыша сфоткать, ' ||
            'он вообще не любитель фотографироваться, но вот что получилось) ' ||
            'колечко мне очень понравилось, спасибо вам большое ❤ ' ||
            'теперь память о моём питомце всегда со мной',
        'img/feedback_fourteen_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (15, 'Забрала! Очень понравилось!! Сегодня подарю и попрошу сфоткать кулончик с котиком вместе) ' ||
            'Спасибо вам большое!!',
        'img/feedback_fifteen_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (16, 'Здравствуйте, колечко забрала. очень мило и красиво сделано) идеально передали его некоторые черты❤️',
        'img/feedback_sixteen_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (17, 'Здравствуйте, колечко забрала
Спасибо большое ❤️',
        'img/feedback_seventeen_bot.jpg', 1);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (18, 'я в полном ах*е какая же это красота🤩
я очень рада,что получила такую прекрасную мастерицу,с замечательными золотыми руками🍴 ' ||
            'буду носить ,хоть мои пальцы оказались меньше чем колечки, ' ||
            'но за то у меня теперь есть столько всего! спасибо этой прекрасной ' ||
            'солнечной девушке,пожалуйста не стесняйтесь! заказывайте больше! это того стоит! 😭',
        'img/feedback_eighteen (0).jpg', 1);

