DROP TABLE IF EXISTS profile, category, product CASCADE;

CREATE TABLE IF NOT EXISTS profile
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY,
    username    VARCHAR(50),
    description VARCHAR(50),
    path_file   VARCHAR(50),
    CONSTRAINT pk_profile PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS category
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(50),
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name        VARCHAR(50),
    description VARCHAR(50),
    price       BIGINT,
    category_id BIGINT,
    profile_id  BIGINT,
    type        VARCHAR(50),
    path_file   VARCHAR(50),
    CONSTRAINT pk_product PRIMARY KEY (id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category (id),
    CONSTRAINT fk_profile FOREIGN KEY (profile_id) REFERENCES profile (id)
);

