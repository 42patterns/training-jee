drop database if exists reports;
create database reports;
use reports;

create table reports (
	id int not null auto_increment primary key,
	feed_title varchar(100),
	items_cnt int,
	create_date date	
) DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;
