DROP TABLE users IF EXISTS;

CREATE TABLE users
(
  user_id           INTEGER IDENTITY PRIMARY KEY,
  email_id          VARCHAR(255)            NOT NULL,
  password          VARCHAR(255)            NOT NULL,
  first_name        VARCHAR(255)            NOT NULL,
  last_name         VARCHAR(255)            NOT NULL
);