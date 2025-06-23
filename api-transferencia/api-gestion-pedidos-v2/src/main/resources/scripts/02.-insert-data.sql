use db_gestion_pedidos;

-- Tabla: productos
INSERT INTO productos (nombre, precio_unitario, stock, fecha_creacion) VALUES ('Shoes', 276.230, 10, current_timestamp);
INSERT INTO productos (nombre, precio_unitario, stock, fecha_creacion) VALUES ('Clock', 136.990, 20, current_timestamp);
INSERT INTO productos (nombre, precio_unitario, stock, fecha_creacion) VALUES ('Watch', 195.840, 10, current_timestamp);
INSERT INTO productos (nombre, precio_unitario, stock, fecha_creacion) VALUES ('Laptop', 499.990, 10, current_timestamp);
INSERT INTO productos (nombre, precio_unitario, stock, fecha_creacion) VALUES ('Phone', 325.500, 10, current_timestamp);
INSERT INTO productos (nombre, precio_unitario, stock, fecha_creacion) VALUES ('Tablet', 220.300, 12, current_timestamp);
INSERT INTO productos (nombre, precio_unitario, stock, fecha_creacion) VALUES ('Headphones', 89.750, 18, current_timestamp);
INSERT INTO productos (nombre, precio_unitario, stock, fecha_creacion) VALUES ('Monitor', 130.200, 15, current_timestamp);
INSERT INTO productos (nombre, precio_unitario, stock, fecha_creacion) VALUES ('Keyboard', 55.990, 10, current_timestamp);
INSERT INTO productos (nombre, precio_unitario, stock, fecha_creacion) VALUES ('Mouse', 34.890, 10, current_timestamp);

-- Tabla: clientes
INSERT INTO clientes (nombres, apellidos, direccion, fecha_creacion) VALUES ('Laura', 'Gomez', 'Calle 123, Lima', '2024-01-12 17:22:55');
INSERT INTO clientes (nombres, apellidos, direccion, fecha_creacion) VALUES ('Jose', 'Lopez', 'Av. Independencia 456, Cusco', '2023-11-22 10:41:33');
INSERT INTO clientes (nombres, apellidos, direccion, fecha_creacion) VALUES ('Ana', 'Sanchez', 'Jr. Arequipa 789, Trujillo', '2024-04-15 19:30:04');
INSERT INTO clientes (nombres, apellidos, direccion, fecha_creacion) VALUES ('Marco', 'Perez', 'Urb. Santa Anita, Arequipa', '2023-09-30 08:50:11');
INSERT INTO clientes (nombres, apellidos, direccion, fecha_creacion) VALUES ('Lucia', 'Vargas', 'Mz B Lote 15, Chiclayo', '2023-07-24 13:23:08');
INSERT INTO clientes (nombres, apellidos, direccion, fecha_creacion) VALUES ('Carlos', 'Ramirez', 'Av. Salaverry 101, Tacna', '2024-03-03 10:33:52');
INSERT INTO clientes (nombres, apellidos, direccion, fecha_creacion) VALUES ('Diana', 'Torres', 'Calle Las Flores, Huancayo', '2023-10-19 09:18:43');
INSERT INTO clientes (nombres, apellidos, direccion, fecha_creacion) VALUES ('Luis', 'Diaz', 'Jr. Amazonas 202, Iquitos', '2023-08-07 12:09:01');
INSERT INTO clientes (nombres, apellidos, direccion, fecha_creacion) VALUES ('Patricia', 'Reyes', 'Calle Los Rosales, Tumbes', '2024-01-28 14:44:19');
INSERT INTO clientes (nombres, apellidos, direccion, fecha_creacion) VALUES ('Miguel', 'Herrera', 'Av. Grau 77, Cajamarca', '2024-02-12 16:00:00');
