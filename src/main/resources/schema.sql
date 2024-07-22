drop database   parking_db;

create database if not exists parking_db;

use parking_db;

create table if not exists ticket(
    ID int PRIMARY KEY AUTO_INCREMENT,
    VEHICLE_NUMBER varchar(25) NOT NULL,
    VEHICLE_TYPE varchar(25) NOT NULL,
    IS_SUBSCRIBER bool NOT NULL,
    IN_TIME datetime NOT NULL,
    OUT_TIME datetime,
    PRICE double
);