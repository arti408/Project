create DATABASE IF NOT EXISTS UserDB;
use UserDB;

drop table IF EXISTS buyer;

create table buyer(
buyerid INTEGER,
name VARCHAR(30),
email VARCHAR(15),
phonenumber INTEGER,
password VARCHAR(20),
isprivileged CHAR(1), CONSTRAINT buy_is_privileged CHECK(isprivileged IN ('Y','N')),
rewardpoints INTEGER,
isactive CHAR(1), CONSTRAINT buy_is_active CHECK(isactive IN ('Y','N')),
CONSTRAINT buy_cid_pk PRIMARY KEY(buyerid)
);

drop table IF EXISTS seller;

create table seller(
sellerid INTEGER,
name VARCHAR(30),
email VARCHAR(15),
phonenumber INTEGER,
password VARCHAR(20),
isactive CHAR(1), CONSTRAINT buy_is_active CHECK(isactive IN ('Y','N'))
);


drop table IF EXISTS wishlist;

create table wishlist(
prodid INTEGER,
buyerid INTEGER,
CONSTRAINT wishlist_cid_pk PRIMARY KEY(prodid,buyerid)
);


drop table IF EXISTS cart;

create table cart(
prodid INTEGER,
buyerid INTEGER,
quantity INTEGER,
CONSTRAINT cart_cid_pk PRIMARY KEY(prodid,buyerid)
);