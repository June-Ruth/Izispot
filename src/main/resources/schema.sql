create database parking_db;

use parking_db;

create table ticket(
    ID int PRIMARY KEY AUTO_INCREMENT,
    VEHICLE_TYPE varchar(25) NOT NULL,
    IS_SUBSCRIBER bool NOT NULL,
    IN_TIME datetime NOT NULL,
    OUT_TIME datetime,
    PRICE double
);