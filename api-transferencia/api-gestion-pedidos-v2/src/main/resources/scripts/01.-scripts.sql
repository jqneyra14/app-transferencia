-- 1.- Creacion de base de datos
CREATE DATABASE db_gestion_pedidos;

-- 2.- Creacion de tabla productos
CREATE TABLE productos (
  id integer NOT NULL AUTO_INCREMENT,
  nombre varchar(255),
  precio_unitario double(15,3),
  stock integer,
  PRIMARY KEY (id)
);
