drop database if exists repository;
create database repository;
use repository;

create table feeds (
	id int not null auto_increment primary key,
	title varchar(100),
	link varchar(100)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;

create table items (
	id int not null auto_increment primary key,
	title varchar(100),
	link varchar(100),
	description text,
	create_date date,
	feed_id int,
	foreign key (feed_id) references feeds(id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;

create table reports (
	id int not null auto_increment primary key,
	feed_title varchar(100),
	items_cnt int,
	create_date date	
) DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;
