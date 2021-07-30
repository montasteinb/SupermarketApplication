SET GLOBAL time_zone = '+2:00';

create database if not exists SupermarketApp;

use SupermarketApp;

create table if not exists products(
	id int not null auto_increment,
    name varchar(50),
    price double,
    quantity int,
    product_Type varchar(50),
    primary key(id)

);

SELECT * FROM products;