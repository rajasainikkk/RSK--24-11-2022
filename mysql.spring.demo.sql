drop database Springdb;
create database Springdb;
use Springdb;

create table 
categories(
	id int(11) unsigned not null,
    name varchar(50) not null,
    description varchar(100) default null,
    primary key (id)
 )Engine=InnoDB Default charset=utf8;
 
 create table  products(
	id int(11) unsigned not null,
    name varchar(50) not null,
    price double not null,
    unitsInStock int ,
    discontinued boolean,
    primary key (id)
 )Engine=InnoDB Default charset=utf8;
 
drop table products;
drop table categories;

select * from products;
select * from categories;

INSERT INTO categories 
   (id,name,description)
VALUES 
   (1,'Toys','The best on the market'),
   (2,'Books','The best on the market');

insert into products 
   (id,name,price,unitsInStock,discontinued)
values 
   (1,'Smart Watch',450.8,400,true),
   (2,'Simple Watch ',280.80,200,false),
   (3,'Magazine',960.80,150,false),
   (4,'Bible Book',500.60,150,true);
   
select * from categories where name like 'To%';
select * from products where discontinued = true;



