CREATE TABLE autores (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(300) NOT NULL,
    PRIMARY KEY (id)
);
