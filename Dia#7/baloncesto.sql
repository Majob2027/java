
create database baloncesto;

use baloncesto;

create table partido(
    id_partido int AUTO_INCREMENT PRIMARY KEY,
    equipo_local varchar(225) not null,
    equipo_visitante varchar(225) not null,
    cestas_local int not null,
    cestas_visitante int not null,
    estado enum("Activo", "Finalizado"),
    fecha_partido date
);

create table liga (
    id_partido_liga int AUTO_INCREMENT PRIMARY KEY,
    id_partido int,
    Foreign Key (id_partido) REFERENCES partido(id_partido),
    jornada int
);

create table playOffs (
    id_partido int AUTO_INCREMENT PRIMARY KEY,
    id_partido_playoffs int,
    Foreign Key (id_partido) REFERENCES partido(id_partido),
    ronda enum("Octavos", "Cuartos", "Final")
);
