create DATABASE IF NOT EXISTS ProductDB;

use ProductDB;
drop table IF EXISTS Product;

create table Product(
prodid VARCHAR(20),
productname VARCHAR(30),
price INTEGER,
stock INTEGER,
description VARCHAR(60),
image VARCHAR(120),
sellerid VARCHAR(20),
category VARCHAR(20),
subcategory VARCHAR(20),
productrating DOUBLE
);

INSERT INTO Product values('B1','Days Gone',30,25,'Days gone is an adventure survival horror game', 'img1','S10','Software','Games',4.5);
INSERT INTO Product values('B2','Equaliser',40,22,'Equaliser is an adventure survival horror game', 'img2','S11','FILMS','Movie',5);
INSERT INTO Product values('B3','Man on Fire',50,33,'Man on Fire is an adventure survival Film','img3', 'S12','FILMS','Movie',3.5);

drop table IF EXISTS subscribedproduct;
create table subscribedproduct(
   buyer_id varchar(10),
   prod_id varchar(10),
   quantity int
);

insert into subscribedproduct values('B101','P101', 10);
insert into subscribedproduct values('B102','P102', 2);