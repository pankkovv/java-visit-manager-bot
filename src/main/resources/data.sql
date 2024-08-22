INSERT INTO category (id, name)
VALUES (1001, 'кольцо диджитал');
INSERT INTO category (id, name)
VALUES (1002, 'кольцо красками');
INSERT INTO category (id, name)
VALUES (1003, 'кулон диджитал');
INSERT INTO category (id, name)
VALUES (1004, 'кулон красками');
INSERT INTO category (id, name)
VALUES (1005, 'серьги');
INSERT INTO category (id, name)
VALUES (1006, 'ожерелье');
INSERT INTO category (id, name)
VALUES (1007, 'бумажный мерч');

-- Profiles
INSERT INTO profile(id, username, description, path_file)
VALUES (1001, 'eee_kisel', 'Привет! Я Киселева Ульяна, рисую под ником «Кисель». Здесь ты можешь познакомиться с моим творчеством и сделать заказ!', null);
INSERT INTO profile(id, username, description, path_file)
VALUES (1002, 'pankkovv', 'New admin this bot', 'img/start.jpg');

-- Pricelist
INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1001, 'Кольцо диджитал',
        'Изображение для этого колечка рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой. ' ||
        'Особенности: точность деталей, возможность создания нескольких украшений с одинаковым рисунком.',
        1500, 1001, 1001, 'ORDER', 'img/ring_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1002, 'Кольцо красками',
        'Изображение для этого колечка наносится акриловыми красками прямо на изделие, затем покрывается ' ||
        'эпоксидной смолой. Особенности: живописность, рисунок выглядит «живым»',
        2000, 1002, 1001, 'ORDER', 'img/ring_paints_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1003, 'Кулон диджитал',
        'Изображение для этого кулона рисуется на графическом планшете, печатается и вставляется в основу, ' ||
        'затем покрывается эпоксидной смолой.Особенности: точность деталей, возможность создания нескольких ' ||
        'украшений с одинаковым рисунком',
        1700, 1003, 1001, 'ORDER', 'img/pendant_digital_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1004, 'Кулон красками',
        'Изображение для этого кулона наносится акриловыми красками прямо на изделие, затем покрывается эпоксидной смолой. ' ||
        'Особенности: живописность, рисунок выглядит «живым»',
        2200, 1004, 1001, 'ORDER', 'img/pendant_paints_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1005, 'Серьги',
        'Изображение наносится акриловыми красками прямо на сережки, затем покрывается эпоксидной смолой. ' ||
        'Рисунки получаются достаточно миниатюрными, поэтому для них подойдут более простые сюжеты',
        2200, 1005, 1001, 'ORDER', 'img/earrings_bot.jpeg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1006, 'Ожерелье',
        'Изделие состоит из комбинации натуральных камней, деталей из нержавеющей стали, ' ||
        'а также кулона с авторским рисунком',
        3000, 1006, 1001, 'ORDER', 'img/necklace_bot.jpeg');

-- Products
INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1007, 'Открытка «Люблю»', 'Односторонняя открытка 14*14 см, напечатанная на плотной бумаге (300 гр).

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_one_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1008, 'Открытка «Послание»', 'Односторонняя открытка формата А6, напечатанная на плотной бумаге (300 гр).

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_two_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1009, 'Открытка «Зеркало»', 'Односторонняя открытка формата А6, напечатанная на плотной бумаге (300 гр).

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_three_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1010, 'Открытка «Не злись»', 'Односторонняя открытка формата А6, напечатанная на плотной бумаге (300 гр).

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_four_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1011, 'Открытка «Хиличурл»', 'Двусторонняя открытка формата А6 по мотивам игры «Genshin Impact», напечатанная на плотной бумаге (300 гр).

❗️Изображения с небольшой обработкой; цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_five_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1012, 'Открытка «Лодка»', 'Односторонняя открытка формата А6, напечатанная на плотной бумаге (300 гр).

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_six_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1013, 'Открытка «Первый снег»', 'Односторонняя открытка формата А6, напечатанная на плотной бумаге (300 гр).

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_seven_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1014, 'Открытка «Меч»', 'Односторонняя открытка формата А6, напечатанная на плотной бумаге (300 гр).

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_eight_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1015, 'Комплект открыток «Winx»', 'В комплект входит 6 открыток формата А6 на плотной бумаге (300 г) с голографическим покрытием сверху.

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 400, 1007, 1001, 'STOCK', 'img/product_nine_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1016, 'Постер «Знак»', 'Постер напечатан на плотной бумаге (300 гр). Формат А4.

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 250, 1007, 1001, 'STOCK', 'img/product_ten_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1017, 'Постер «Ты любим»', 'Постер напечатан на плотной бумаге (300 гр). Формат А4.

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 250, 1007, 1001, 'STOCK', 'img/product_eleven_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1018, 'Постер «Гордись собой»', 'Постер напечатан на плотной бумаге (300 гр). Формат А4.

❗️Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 250, 1007, 1001, 'STOCK', 'img/product_twelve_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1019, 'Карточка «Рыжик»', 'Односторонняя открытка 63*88 мм, напечатанная на плотной бумаге (350 гр).

❗️ Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 50, 1007, 1001, 'STOCK', 'img/product_thirteen_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1020, 'Открытка «Зима»', 'Односторонняя открытка 10*10 см, напечатанная на плотной бумаге (300 гр) и покрытая дополнительной глянцевой ламинацией.

❗️ Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_fourteen_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1021, 'Открытка «Котик»', 'Односторонняя открытка 10*10 см, напечатанная на плотной бумаге (300 гр) и покрытая дополнительной глянцевой ламинацией.

❗️ Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_fifteen_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1022, 'Открытка «Цветы»', 'Двусторонняя открытка формата А6, напечатанная на плотной бумаге (300 гр) и покрытая дополнительной матовой ламинацией.

❗️ Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 100, 1007, 1001, 'STOCK', 'img/product_sixteen_bot.jpg');

INSERT INTO product(id, name, description, price, category_id, profile_id, type, path_file)
VALUES (1023, 'Осенний стикерпак', 'Уютный, приятный на ощупь стикерпак формата А5 на виниле с дополнительной матовой ламинацией. Такие наклеечки гораздо прочнее бумажных и не боятся воды. Можно смело клеить на ноутбук и чехол телефона!

❗️ Цвета в реальной жизни могут незначительно отличаться от фото.

Доставка оплачивается отдельно', 250, 1007, 1001, 'STOCK', 'img/product_seventeen_bot.jpg');

-- Feedbacks
INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1001, 'Отзыв на слайдеры для ногтей 💅🏻',
        'img/feedback_one_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1002, 'Спасибо Вам огромное!! Забрала колечки, они просто чудесные 🥺',
        'img/feedback_two_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1003, 'Ульяна, спасибочки огромное за такие клевые колечки! В жизни они смотрятся ещё в 100 раз милее. Мне даже жалко их носить, настолько они классные ☺️💖',
        'img/feedback_three_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1004, 'Только вчера появилось время забрать посылку, зато сегодня уже ходила хвасталась😇
спасибо большое за Вашу работу, нам очень нравится)
успехов в творчестве😌',
        'img/feedback_four_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1005, 'Я хотела сказать тебе ещё раз спасибо за эти прекрасные колечки) ' ||
              'я смотрю на портреты моих девочек и глаз нарадоваться не может! ' ||
              'Получилось все как я хотела, спасибо большое❤️',
        'img/feedback_five_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1006, 'Большое спасибо за этого мили кота 💋💋🥰',
        'img/feedback_six_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1007, 'Пришли!!! Я влюблена 🤲
Теперь помогают мне на работе. Кулон - сплошное очарование, ' ||
              'найду цепочку и обязательно отпишусь в комментах
   Ещё раз спасибо за труд и непременно берегите себя!',
        'img/feedback_seven_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1008, 'Я в любви!💖💖 Кулончик на 100/10, кошечка очень похожа, ' ||
              'такая же нежная и любвеобильная. Моя Шмонечка оценила🥰💖',
        'img/feedback_eight_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1009, 'Добрый день!! Колечко пришло, я в полном восторге! Вживую ещё милее и круче, чем на фото🥺
Качество рисунка просто огонь, оправа супер удобная, приятно носить. Спасибо большое за работу!',
        'img/feedback_nine_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1010, '🥹🥹🥹🥹
прелесть! очень переживала, что будет видно пиксели из-за печати, но качество шикарное! ' ||
              'сейчас до дома доберусь и еще пофоткаю',
        'img/feedback_ten_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1011, 'Привет! Вчера забрала колечко. Вживую ещё лучше чем на фото😭❤️❤️❤️ ' ||
              'Просто невероятная милота, ожидания полностью оправданы',
        'img/feedback_eleven_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1012, 'добрый день! замоталась, только дошли руки написать отзыв)

огромное Вам спасибо!! колечко просто чудесное, очень качественно все, ' ||
              'носить одно удовольствие 🥺 отдельная благодарность за возможность вносить свои правки/корректировки 🫶🏽

  уже всем друзьям посоветовала Вас, да и уверена, что сама ещё ни раз обращусь 💗',
        'img/feedback_twelve_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1013, 'Вот такое прекрасное колечко ❤️',
        'img/feedback_thirteen_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1014, 'здравствуйте, сложно было малыша сфоткать, ' ||
              'он вообще не любитель фотографироваться, но вот что получилось) ' ||
              'колечко мне очень понравилось, спасибо вам большое ❤ ' ||
              'теперь память о моём питомце всегда со мной',
        'img/feedback_fourteen_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1015, 'Забрала! Очень понравилось!! Сегодня подарю и попрошу сфоткать кулончик с котиком вместе) ' ||
              'Спасибо вам большое!!',
        'img/feedback_fifteen_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1016, 'Здравствуйте, колечко забрала. очень мило и красиво сделано) идеально передали его некоторые черты❤️',
        'img/feedback_sixteen_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1017, 'Здравствуйте, колечко забрала
Спасибо большое ❤️',
        'img/feedback_seventeen_bot.jpg', 1001);

INSERT INTO feedback(id, description, path_file, profile_id)
VALUES (1018, 'я в шоке, какая же это красота 🤩
я очень рада,что получила такую прекрасную мастерицу,с замечательными золотыми руками❤️
спасибо этой прекрасной солнечной девушке,пожалуйста не стесняйтесь! заказывайте больше! это того стоит! 😭',
        'img/feedback_eighteen.jpg', 1001);
