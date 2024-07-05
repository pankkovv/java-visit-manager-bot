INSERT INTO category (id, name)
VALUES (1000, 'кольцо диджитал');
INSERT INTO category (id, name)
VALUES (2000, 'кольцо красками');
INSERT INTO category (id, name)
VALUES (3000, 'кулон диджитал');
INSERT INTO category (id, name)
VALUES (4000, 'кулон красками');
INSERT INTO category (id, name)
VALUES (5000, 'серьги');
INSERT INTO category (id, name)
VALUES (6000, 'ожерелье');
INSERT INTO category (id, name)
VALUES (7000, 'бумажный мерч');

-- Profiles
INSERT INTO profile(id, username, description, path_file)
VALUES (1000, 'pankkovv', 'New admin this bot', 'img/start.jpg');
INSERT INTO profile(id, username, description, path_file)
VALUES (2000, 'eee_kisel', 'New designer this bot', 'img/start.jpg');

-- Pricelist
INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1000, 'Кольцо диджитал',
        'Изображение для этого колечка рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой. ' ||
        'Особенности: точность деталей, возможность создания нескольких украшений с одинаковым рисунком.',
        1500, 1000, 1000, 'ORDER', 'img/ring_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (2000, 'Кольцо красками',
        'Изображение для этого колечка наносится акриловыми красками прямо на изделие, затем покрывается ' ||
        'эпоксидной смолой. Особенности: живописность, рисунок выглядит «живым»',
        2000, 2000, 1000, 'ORDER', 'img/ring_paints_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (3000, 'Кулон диджитал',
        'Изображение для этого кулона рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой.Особенности: точность деталей, возможность создания нескольких ' ||
        'украшений с одинаковым рисунком',
        1700, 3000, 1000, 'ORDER', 'img/pendant_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (4000, 'Кулон красками',
        'Изображение для этого кулона наносится акриловыми красками прямо на изделие, затем покрывается эпоксидной смолой. ' ||
        'Особенности: живописность, рисунок выглядит «живым»',
        2200, 4000, 1000, 'ORDER', 'img/pendant_paints_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (5000, 'Серьги',
        'Изображение наносится акриловыми красками прямо на сережки, затем покрывается эпоксидной смолой. ' ||
        'Рисунки получаются достаточно миниатюрными, поэтому для них подойдут более простые сюжеты',
        2200, 5000, 1000, 'ORDER', 'img/earrings_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (6000, 'Ожерелье',
        'Изделие состоит из комбинации натуральных камней, деталей из нержавеющей стали, ' ||
        'а также кулона с авторским рисунком',
        3000, 6000, 1000, 'ORDER', 'img/necklace_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (7000, 'Бумага',
        'В этот раздел входит большое количество мерча:
- открытки
- стикеры
- постеры
- переводные татуировки
- отрывные блоки для заметок
- слайдеры для ногтей',
        100, 7000, 1000, 'ORDER', 'img/paper_bot.jpeg');

-- Feedbacks
INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1000, 'Снова отзывы на слайдеры 😍',
        'img/feedback_one_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (2000, 'Безумно люблю смотреть ваши фото-отзывы 🥰',
        'img/feedback_two_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (3000, 'Новый отзыв 🥰' ||
           'Ульяна, спасибочки огромное за такие клевые колечки! ' ||
           'В жизни они смотрятся ещё в 100 раз милее. Мне даже жалко их носить, настолько они классные 😃💖',
        'img/feedback_three_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (4000, 'Новый отзыв с фотографией пушистика 🥰
Только вчера появилось время забрать посылку, зато сегодня уже ходила хвасталась😇
спасибо большое за Вашу работу, нам очень нравится)
успехов в творчестве😌',
        'img/feedback_four_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (5000, 'Я хотела сказать тебе ещё раз спасибо за эти прекрасные колечки) ' ||
           'я смотрю на портреты моих девочек и глаз нарадоваться не может! ' ||
           'Получилось все как я хотела, спасибо большое❤️',
        'img/feedback_five_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (6000, 'Большое спасибо за этого мили кота 💋💋🥰',
        'img/feedback_six_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (7000, 'Пришли!!! Я влюблена 🤲
Теперь помогают мне на работе. Кулон - сплошное очарование, ' ||
           'найду цепочку и обязательно отпишусь в комментах
Ещё раз спасибо за труд и непременно берегите себя!',
        'img/feedback_seven_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (8000, 'Я в любви!💖💖 Кулончик на 100/10, кошечка очень похожа, ' ||
           'такая же нежная и любвеобильная. Моя Шмонечка оценила🥰💖',
        'img/feedback_eight_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (9000, 'Ульяна, спасибочки огромное за такие клевые колечки! ' ||
           'В жизни они смотрятся ещё в 100 раз милее. ' ||
           'Мне даже жалко их носить, настолько они классные 😃💖',
        'img/feedback_nine_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (10000, '🥹🥹🥹🥹
прелесть! очень переживала, что будет видно пиксели из-за печати, но качество шикарное! ' ||
            'сейчас до дома доберусь и еще пофоткаю',
        'img/feedback_ten_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (11000, 'Привет! Вчера забрала колечко. Вживую ещё лучше чем на фото😭❤️❤️❤️ ' ||
            'Просто невероятная милота, ожидания полностью оправданы',
        'img/feedback_eleven_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (12000, 'добрый день! замоталась, только дошли руки написать отзыв)

огромное Вам спасибо!! колечко просто чудесное, очень качественно все, ' ||
            'носить одно удовольствие 🥺 отдельная благодарность за возможность вносить свои правки/корректировки 🫶🏽

уже всем друзьям посоветовала Вас, да и уверена, что сама ещё ни раз обращусь 💗',
        'img/feedback_twelve_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (13000, 'Вот такое прекрасное колечко ❤️',
        'img/feedback_thirteen_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (14000, 'здравствуйте, сложно было малыша сфоткать, ' ||
            'он вообще не любитель фотографироваться, но вот что получилось) ' ||
            'колечко мне очень понравилось, спасибо вам большое ❤ ' ||
            'теперь память о моём питомце всегда со мной',
        'img/feedback_fourteen_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (15000, 'Забрала! Очень понравилось!! Сегодня подарю и попрошу сфоткать кулончик с котиком вместе) ' ||
            'Спасибо вам большое!!',
        'img/feedback_fifteen_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (16000, 'Здравствуйте, колечко забрала. очень мило и красиво сделано) идеально передали его некоторые черты❤️',
        'img/feedback_sixteen_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (17000, 'Здравствуйте, колечко забрала
Спасибо большое ❤️',
        'img/feedback_seventeen_bot.jpg', 1000);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (18000, 'я в полном ах*е какая же это красота🤩
я очень рада,что получила такую прекрасную мастерицу,с замечательными золотыми руками🍴 ' ||
            'буду носить ,хоть мои пальцы оказались меньше чем колечки, ' ||
            'но за то у меня теперь есть столько всего! спасибо этой прекрасной ' ||
            'солнечной девушке,пожалуйста не стесняйтесь! заказывайте больше! это того стоит! 😭',
        'img/feedback_eighteen (0).jpg', 1000);

