CREATE TABLE bookings(
  id BIGINT NOT NULL AUTO_INCREMENT,
  room_id BIGINT,
  user_id BIGINT,
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_rooms_bookings FOREIGN KEY (room_id) REFERENCES rooms (id),
  CONSTRAINT FK_users_bookings FOREIGN KEY (user_id) REFERENCES users (id)
);