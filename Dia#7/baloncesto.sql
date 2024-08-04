
create database baloncesto;

use baloncesto;

create table partido(
    id int AUTO_INCREMENT PRIMARY KEY,
    equipoLocal varchar(225) not null,
    equipoVisitante varchar(225) not null,
    cestasLocal int not null,
    cestasVisitante int not null,
    estado enum("Activo", "Finalizado"),
    fechaPartido date
);

create table liga (
    id int AUTO_INCREMENT PRIMARY KEY,
    id_partido int,
    Foreign Key (id_partido) REFERENCES partido(id),
    jornada int
);

create table playOffs (
    id int AUTO_INCREMENT PRIMARY KEY,
    id_partido int,
    Foreign Key (id_partido) REFERENCES partido(id),
    ronda enum("Octavos", "Cuartos", "Final")
);