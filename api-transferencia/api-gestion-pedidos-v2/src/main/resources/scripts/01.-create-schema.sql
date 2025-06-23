-- Creación de base de datos
CREATE DATABASE IF NOT EXISTS db_gestion_pedidos;
USE db_gestion_pedidos;

-- Tabla: productos
CREATE TABLE productos (
    id              INT NOT NULL AUTO_INCREMENT,
    nombre          VARCHAR(255) NOT NULL,
    precio_unitario DECIMAL(15, 3) NOT NULL,
    stock           DECIMAL(15, 5) NOT NULL,
    fecha_creacion  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_productos PRIMARY KEY (id)
);

-- Tabla: clientes
CREATE TABLE clientes (
    id              INT NOT NULL AUTO_INCREMENT,
    nombres         VARCHAR(100) NOT NULL,
    apellidos       VARCHAR(100) NOT NULL,
    email           VARCHAR(100),
    direccion       VARCHAR(255),
    fecha_creacion  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_clientes PRIMARY KEY (id)
);

-- Tabla: pedidos
CREATE TABLE pedidos (
    id              INT NOT NULL AUTO_INCREMENT,
    cliente_id      INT NOT NULL,
    fecha           DATE NOT NULL,
    estado          ENUM('GENERADO', 'CANCELADO', 'STOCK_CONFIRMADO', 'PAGO_CONFIRMADO', 'PREPARANDO', 'LISTO', 'ENTREGADO') NOT NULL,
    total           DECIMAL(15, 5) NOT NULL DEFAULT 0.0,
    fecha_creacion  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_pedidos PRIMARY KEY (id),
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- Tabla: pedido_detalles
CREATE TABLE pedido_detalles (
    id              INT NOT NULL AUTO_INCREMENT,
    pedido_id       INT NOT NULL,
    producto_id     INT NOT NULL,
    cantidad        DECIMAL(15, 3) NOT NULL,
    subtotal        DECIMAL(15, 5) NOT NULL,
    fecha_creacion  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_pedido_detalles PRIMARY KEY (id),
    CONSTRAINT fk_detalle_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    CONSTRAINT fk_detalle_producto FOREIGN KEY (producto_id) REFERENCES productos(id)
);
