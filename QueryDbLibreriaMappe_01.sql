create database libreria;

use libreria;

create table libri
(
	id int primary key auto_increment,
    titolo varchar(100),
    autore varchar(100)
);

insert into libri
(titolo, autore)
values
("Dune","Frank Herbert"),
("Il ritorno del cavaliere oscuro","Frank Miller"),
("Flash","Geoff Johns");

select	*
from	libri;