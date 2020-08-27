CREATE TABLE `users` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `username` varchar(250) DEFAULT NULL,
  `passwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX IDX_USER_001 ON USERS (username);

CREATE TABLE `carts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT 0,
  `is_done` bit(1) NOT NULL,
  `target_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(255) DEFAULT NULL,
  `itemnm` varchar(255) DEFAULT NULL,
  `ipaddr` varchar(20) DEFAULT NULL, 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;


CREATE INDEX IDX_CART_001 ON CARTS (username);
CREATE INDEX IDX_CART_002 ON CARTS (username, id desc);

















//Oracle
create tablespace tbs_webapp datafile '/oradata/tbs_webapp01.dbf' size 10G autoextend on maxsize 30G;
SELECT TABLESPACE_NAME, STATUS, CONTENTS FROM USER_TABLESPACES;


CREATE USER webuser IDENTIFIED BY qwert12345 DEFAULT TABLESPACE tbs_webapp TEMPORARY TABLESPACE TEMP;
GRANT CONNECT TO webuser;
GRANT RESOURCE TO webuser;
GRANT UNLIMITED TABLESPACE TO webuser;




CREATE TABLE users (
id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
first_name varchar(20),
last_name varchar(20),
username varchar(250),
passwd varchar(20),
CONSTRAINT PK_users PRIMARY KEY (ID)
);



CREATE TABLE carts (
  id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
  price float,
  is_done varchar(1),
  target_date date DEFAULT SYSDATE,
  username varchar(255),
  itemnm varchar(255),
  ipaddr varchar(20) ,
  CONSTRAINT PK_carts PRIMARY KEY (ID)
 );
 
  
 CREATE UNIQUE INDEX IDX_USER_001 ON USERS (ID ASC);
 CREATE UNIQUE INDEX IDX_carts_001 ON carts (ID ASC);
 
 