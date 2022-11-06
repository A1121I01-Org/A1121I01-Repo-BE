create database demologin;

use `demologin`;

create table if not exists `account` (
	account_id bigint primary key auto_increment,
    username varchar(20) unique not null,
    `password` varchar(255) not null
) ENGINE=MyISAM AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table if not exists `role` (
	role_id bigint primary key auto_increment,
    role_name varchar(30) null
) ENGINE=MyISAM AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table if not exists account_role (
	account_role_id bigint primary key auto_increment,
    account_id bigint not null,
    role_id bigint not null,
    foreign key (account_id) references `account`(account_id) on update cascade,				
	foreign key (role_id) references `role`(role_id) on update cascade	 	
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

select * from account_role;

create table if not exists customer (
	customer_id bigint primary key auto_increment,						
	customer_name varchar(50) not null,				
	customer_address varchar(255)		
);
select * from customer;
