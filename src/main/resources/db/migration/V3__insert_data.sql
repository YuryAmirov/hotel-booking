INSERT INTO rooms (id, category, number, price) values (1, 'SINGLE', 101, 110);
INSERT INTO rooms (id, category, number, price) values (2, 'DOUBLE', 102, 120);
INSERT INTO rooms (id, category, number, price) values (3, 'DOUBLE', 103, 120);
INSERT INTO rooms (id, category, number, price) values (4, 'TWIN', 104, 130);
INSERT INTO rooms (id, category, number, price) values (5, 'KING', 105, 150);
INSERT INTO rooms (id, category, number, price) values (6, 'SINGLE', 201, 110);
INSERT INTO rooms (id, category, number, price) values (7, 'DOUBLE', 202, 120);
INSERT INTO rooms (id, category, number, price) values (8, 'DOUBLE', 203, 120);
INSERT INTO rooms (id, category, number, price) values (9, 'TWIN', 204, 130);
INSERT INTO rooms (id, category, number, price) values (10, 'KING', 205, 150);
INSERT INTO rooms (id, category, number, price) values (11, 'SINGLE', 301, 110);
INSERT INTO rooms (id, category, number, price) values (12, 'DOUBLE', 302, 120);
INSERT INTO rooms (id, category, number, price) values (13, 'DOUBLE', 303, 120);
INSERT INTO rooms (id, category, number, price) values (14, 'TWIN', 304, 130);
INSERT INTO rooms (id, category, number, price) values (15, 'KING', 305, 150);

INSERT INTO additional_options (id, room_id, type, price) values (1, 1, 'BREAKFAST', 10);
INSERT INTO additional_options (id, room_id, type, price) values (2, 2, 'BREAKFAST', 10);
INSERT INTO additional_options (id, room_id, type, price) values (3, 3, 'BREAKFAST', 10);
INSERT INTO additional_options (id, room_id, type, price) values (4, 4, 'BREAKFAST', 10);
INSERT INTO additional_options (id, room_id, type, price) values (5, 5, 'BREAKFAST', 10);
INSERT INTO additional_options (id, room_id, type, price) values (6, 1, 'CLEANING', 15);
INSERT INTO additional_options (id, room_id, type, price) values (7, 2, 'CLEANING', 15);
INSERT INTO additional_options (id, room_id, type, price) values (8, 3, 'CLEANING', 15);
INSERT INTO additional_options (id, room_id, type, price) values (9, 4, 'CLEANING', 15);
INSERT INTO additional_options (id, room_id, type, price) values (10, 5, 'CLEANING', 15);

INSERT INTO users (id, first_name, last_name, email, password) values (1, 'user1', 'user1', 'user1@mail.com', 'AAaa!');
INSERT INTO users (id, first_name, last_name, email, password) values (2, 'user2', 'user2', 'user2@mail.com', 'AAaa!');
INSERT INTO users (id, first_name, last_name, email, password) values (3, 'user3', 'user3', 'user3@mail.com', 'AAaa!');
INSERT INTO users (id, first_name, last_name, email, password) values (4, 'user4', 'user4', 'user4@mail.com', 'AAaa!');
INSERT INTO users (id, first_name, last_name, email, password) values (5, 'user5', 'user5', 'user5@mail.com', 'AAaa!');

INSERT INTO bookings (id, room_id, user_id, start_date, end_date) values (1, 1, 1, CURRENT_DATE - 5, CURRENT_DATE - 3);
INSERT INTO bookings (id, room_id, user_id, start_date, end_date) values (2, 2, 2, CURRENT_DATE, CURRENT_DATE + 3);
INSERT INTO bookings (id, room_id, user_id, start_date, end_date) values (3, 11, 3, CURRENT_DATE + 1, CURRENT_DATE + 4);
INSERT INTO bookings (id, room_id, user_id, start_date, end_date) values (4, 12, 4, CURRENT_DATE + 5, CURRENT_DATE + 8);
INSERT INTO bookings (id, room_id, user_id, start_date, end_date) values (5, 15, 5, CURRENT_DATE + 10, CURRENT_DATE + 15);