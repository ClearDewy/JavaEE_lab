CREATE TABLE IF NOT EXISTS `user`(
username char (31) not null primary key,
password char (31) not null
);


CREATE TABLE IF NOT EXISTS `message`(
username char (31) not null primary key,
message varchar (511) not null,
foreign key (`username`) references `user`(`username`) ON DELETE cascade on update cascade
);