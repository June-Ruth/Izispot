drop database if exists parking_db_test;

create database if not exists parking_db_test;

use parking_db_test;

create table if not exists ticket(
    ID int PRIMARY KEY AUTO_INCREMENT,
    VEHICLE_NUMBER varchar(25) NOT NULL,
    VEHICLE_TYPE varchar(25) NOT NULL,
    IS_SUBSCRIBER bool NOT NULL,
    IN_TIME datetime NOT NULL,
    OUT_TIME datetime,
    PRICE double
);