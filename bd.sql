create database farmaguia;

use farmaguia;

create table usuarios(
    nome varchar(100) not null unique,
    senha text not null
);

insert into usuarios(nome, senha)
values
("admin", sha1("ADM@123"));

create table dermatologia(
	produtos varchar(100) not null unique,
    preço_sugerido text not null
);
