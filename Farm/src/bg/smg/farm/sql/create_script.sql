create database farm_ms;

create table users (
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username varchar(32),
    password varchar(32),
    created timestamp,
    is_active boolean
);

CREATE TABLE cow(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(23),
    weight int,
    pictureName varchar(23)
    dateOfBirth date
);