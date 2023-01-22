CREATE TABLE user_profile
(
    id       SERIAL,
    nickname VARCHAR(128) UNIQUE,
    email    VARCHAR(128) UNIQUE,
    password VARCHAR(128),
    PRIMARY KEY (id)
);


INSERT INTO user_profile (nickname, email, password)
VALUES ('nikky34', 'some432@gmail.com', 'noteToday23');
INSERT INTO user_profile (nickname, email, password)
VALUES ('mortyRick', 'letsgo763@gmail.com', 'somePass32');
INSERT INTO user_profile (nickname, email, password)
VALUES ('locky89', 'manhello543@gmail.com', 'helpMe43');
INSERT INTO user_profile (nickname, email, password)
VALUES ('heyheyhey666', 'dude345@gmail.com', 'frontEnd666');