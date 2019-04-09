CREATE TABLE users(
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL unique ,
  password VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE rooms(
  id BIGINT NOT NULL AUTO_INCREMENT,
  category VARCHAR(50) NOT NULL,
  number BIGINT NOT NULL,
  price BIGINT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE additional_options(
  id BIGINT NOT NULL AUTO_INCREMENT,
  room_id BIGINT,
  type VARCHAR(50) NOT NULL,
  price BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_additional_options_rooms FOREIGN KEY (room_id) REFERENCES rooms (id)
);