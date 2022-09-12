CREATE SCHEMA IF NOT EXISTS conferences DEFAULT CHARACTER SET utf8;
USE conferences;

DROP TABLE IF EXISTS events;
CREATE TABLE IF NOT EXISTS events
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    place VARCHAR(90) NOT NULL,
    time TIME NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS reports;
CREATE TABLE reports
(
    id INT NOT NULL AUTO_INCREMENT,
    topic VARCHAR(45) NOT NULL,
    event_id INT NOT NULL,
    speaker_id INT NOT NULL,
    PRIMARY KEY (id),
    KEY event_id_idx (event_id),
    KEY speaker_id_idx (speaker_id),
    CONSTRAINT event_id
        FOREIGN KEY (event_id)
            REFERENCES events (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT speaker_id
        FOREIGN KEY (speaker_id)
            REFERENCES users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS reports_users;
CREATE TABLE reports_users
(
    report_id INT NOT NULL,
    user_id INT NOT NULL,
    KEY report_id_idx (report_id),
    KEY user_id_idx (user_id),
    CONSTRAINT report_id
        FOREIGN KEY (report_id)
            REFERENCES reports (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT user_id
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    id INT NOT NULL,
    role VARCHAR(45) NOT NULL,
    PRIMARY KEY (id),
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    firstname VARCHAR(45) DEFAULT NULL,
    lastname VARCHAR(45) DEFAULT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (id),
    KEY role_id_idx (role_id),
    CONSTRAINT role_id
        FOREIGN KEY (role_id)
            REFERENCES roles (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;