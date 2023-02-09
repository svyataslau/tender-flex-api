CREATE TABLE user_role
(
    id    SERIAL,
    role VARCHAR(128) UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE permission
(
    id    SERIAL,
    title VARCHAR(128) UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE user_profile
(
    id       SERIAL,
    username VARCHAR(128) UNIQUE,
    email    VARCHAR(128) UNIQUE,
    password VARCHAR(128),
    user_role_id INTEGER REFERENCES user_role (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

CREATE TABLE user_role_access
(
    id          SERIAL,
    user_role_id   INTEGER REFERENCES user_role  (id) ON DELETE CASCADE,
    permission_id  INTEGER REFERENCES permission (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

INSERT INTO user_role (role) VALUES ('BIDDER');
INSERT INTO user_role (role) VALUES ('CONTRACTOR');
INSERT INTO user_role (role) VALUES ('ADMIN');

INSERT INTO permission (title) VALUES ('user:read');
INSERT INTO permission (title) VALUES ('userList:read');
INSERT INTO permission (title) VALUES ('tender:read');
INSERT INTO permission (title) VALUES ('tenderList:read');
INSERT INTO permission (title) VALUES ('tender:write');

INSERT INTO user_profile(username, email, password, user_role_id) VALUES ('nikky34', 'some432@gmail.com', 'noteToday23',1);
INSERT INTO user_profile(username, email, password, user_role_id) VALUES ('mortyRick', 'letsgo763@gmail.com', 'somePass32',2);
INSERT INTO user_profile(username, email, password, user_role_id) VALUES ('locky89', 'manhello543@gmail.com', 'helpMe43',3);

INSERT INTO user_role_access (user_role_id, permission_id) VALUES (1,4);
INSERT INTO user_role_access (user_role_id, permission_id) VALUES (1,3);
INSERT INTO user_role_access (user_role_id, permission_id) VALUES (2,5);
INSERT INTO user_role_access (user_role_id, permission_id) VALUES (2,3);
INSERT INTO user_role_access (user_role_id, permission_id) VALUES (2,4);
INSERT INTO user_role_access (user_role_id, permission_id) VALUES (3,2);