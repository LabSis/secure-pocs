DROP DATABASE IF EXISTS fuerza_bruta;

CREATE DATABASE IF NOT EXISTS fuerza_bruta;
USE fuerza_bruta;

CREATE TABLE usuarios (
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(10) NOT NULL,
	clave VARCHAR(10) NOT NULL,
	UNIQUE(nombre),
	PRIMARY KEY(id)
)ENGINE InnoDB DEFAULT CHARACTER SET=utf8;

INSERT INTO usuarios (nombre, clave) 
	VALUES 
	("admin","cxfr")
;

DROP USER IF EXISTS 'fb'@'localhost';
CREATE USER 'fb'@'localhost' IDENTIFIED BY '741852963';

GRANT SELECT ON fuerza_bruta.usuarios TO 'fb'@'localhost';
