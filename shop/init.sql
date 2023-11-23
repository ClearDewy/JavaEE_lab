
create database if not exists `shop`;
use `shop`;

CREATE TABLE IF NOT EXISTS `user`(
id int primary key not null auto_increment,
username char (31) not null,
password char (31) not null
);


CREATE TABLE IF NOT EXISTS `commodity`(
id int primary key auto_increment,
name varchar(127) not null ,
price double(10,2) not null
);

CREATE TABLE IF NOT EXISTS `order`(
      id int primary key auto_increment,
      user_id int not null ,
      commodity_id int not null ,
      count int not null ,
      foreign key (`user_id`) references `user`(id),
      foreign key (`commodity_id`) references `commodity`(id)
);