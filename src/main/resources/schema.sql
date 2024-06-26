DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users
(
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  username VARCHAR(50),
  description VARCHAR(50),
  photo VARCHAR(50),
  CONSTRAINT pk_user PRIMARY KEY (id)
);