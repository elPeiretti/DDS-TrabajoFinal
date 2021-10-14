-- TABLAS TP DISENIO DE SISTEMAS
CREATE SCHEMA tpdds;


-- SE DEBE UTILIZAR INTEGER PARA TODAS LAS FK QUE APUNTAN A UN SERIAL
-- DECIMAL (12,2) UTILIZADO PARA LOS DOUBLE
-- VARCHAR(100) PARA LOS STRING POR DEFECTO
-- EXCEPCIONES EN LAS QUE SE PUEDA NECESITAR MÁS LONGITUD, UTILIZAR
-- VARCHAR(200) 


CREATE TABLE tpdds.pasajero
(id_pasajero SERIAL PRIMARY KEY,
nombres VARCHAR(100),
apellido VARCHAR(100),
nro_documento VARCHAR(100),
ocupacion VARCHAR(100),
email VARCHAR(100),
cuit VARCHAR(100),
nacionalidad INTEGER, 
telefono VARCHAR(100),
fecha_nacimiento DATE,
id_posicion_iva INTEGER,
id_direccion INTEGER,
id_tipo_documento INTEGER);


CREATE TABLE tpdds.servicio
(id_servicio SERIAL PRIMARY KEY,
precio_unitario DECIMAL(12,2),
cantidad_pagada INTEGER,
descripcion VARCHAR(200),
cantidad INTEGER,
fecha_realizacion DATE,
id_habitacion INTEGER);


CREATE TABLE tpdds.item_factura
(id_item_factura SERIAL PRIMARY KEY,
cantidad INTEGER,
id_factura INTEGER,
id_servicio INTEGER);


CREATE TABLE tpdds.nota_de_credito
(id_nota SERIAL PRIMARY KEY,
tipo_nota VARCHAR(100),
fecha_de_emision DATE,
monto DECIMAL(12,2),
total_iva DECIMAL(12,2),
id_responsable_tercero INTEGER,
id_pasajero INTEGER);


CREATE TABLE tpdds.ocupacion
(id_ocupacion SERIAL PRIMARY KEY,
fecha_ingreso DATE,
fecha_egreso DATE,
id_habitacion INTEGER,
id_pasajero_responsable INTEGER);


CREATE TABLE tpdds.costo_por_noche
(id_costo_por_noche SERIAL PRIMARY KEY,
costo DECIMAL(12,2),
fecha_inicio_vigencia DATE,
fecha_fin_vigencia DATE,
id_tipo_habitacion INTEGER);


CREATE TABLE tpdds.responsable_pago_tercero
(id_responsable_tercero SERIAL PRIMARY KEY,
cuit VARCHAR(100),
razon_social VARCHAR(100),
telefono VARCHAR(100),
id_direccion INTEGER);


CREATE TABLE tpdds.factura
(id_factura SERIAL PRIMARY KEY,
tipo_factura VARCHAR(100),
fecha_de_emision DATE,
importe_factura DECIMAL(12,2),
total_iva DECIMAL(12,2),
id_responsable_tercero INTEGER,
id_pasajero INTEGER,
id_nota INTEGER);


CREATE TABLE tpdds.tipo_documento
(id_tipo_documento SERIAL PRIMARY KEY,
tipo VARCHAR(100));


CREATE TABLE tpdds.responsable_reserva
(id_responsable_reserva SERIAL PRIMARY KEY,
nombre VARCHAR(100),
apellido VARCHAR(100),
telefono VARCHAR(100));


CREATE TABLE tpdds.pais
(id_pais SERIAL PRIMARY KEY,
nombre VARCHAR(100));


CREATE TABLE tpdds.provincia
(id_provincia SERIAL PRIMARY KEY,
nombre VARCHAR(100),
id_pais INTEGER);


CREATE TABLE tpdds.ciudad
(id_ciudad SERIAL PRIMARY KEY,
nombre VARCHAR(100),
id_provincia INTEGER);




CREATE TABLE tpdds.posicion_iva
(id_posicion_iva SERIAL PRIMARY KEY,
posicion VARCHAR(100));


CREATE TABLE tpdds.acompaniante
(id_acompaniante SERIAL PRIMARY KEY,
id_ocupacion INTEGER,
id_pasajero INTEGER, 
CONSTRAINT uk_acompaniante UNIQUE (id_ocupacion, id_pasajero));


CREATE TABLE tpdds.tipo_habitacion
(id_tipo_habitacion SERIAL PRIMARY KEY,
nombre VARCHAR(100),
capacidad INTEGER);


CREATE TABLE tpdds.habitacion
(id_habitacion SERIAL PRIMARY KEY,
estado VARCHAR(100),
numero INTEGER,
id_tipo_habitacion INTEGER);


CREATE TABLE tpdds.reserva
(id_reserva SERIAL PRIMARY KEY,
id_responsable_reserva INTEGER,
id_habitacion INTEGER, 
fecha_ingreso DATE,
fecha_egreso DATE,
estado VARCHAR(100),
CONSTRAINT uk_reserva UNIQUE (id_responsable_reserva, id_habitacion));


CREATE TABLE tpdds.direccion
(id_direccion SERIAL PRIMARY KEY,
piso INTEGER,
calle VARCHAR(100), 
dpto VARCHAR(100),
nro_calle INTEGER,
codigo_postal VARCHAR(100),
id_ciudad INTEGER);


CREATE TABLE tpdds.tipo_tarjeta
(id_tipo_tarjeta SERIAL PRIMARY KEY,
tipo VARCHAR(100));


CREATE TABLE tpdds.moneda
(id_moneda SERIAL PRIMARY KEY,
tipo VARCHAR(100));


CREATE TABLE tpdds.banco
(id_banco SERIAL PRIMARY KEY,
nombre VARCHAR(100));


CREATE TABLE tpdds.pago
(id_pago SERIAL PRIMARY KEY,
monto_acumulado DECIMAL(12,2),
vuelto DECIMAL(12,2),
fecha_de_realizacion DATE,
id_factura INTEGER);


CREATE TABLE tpdds.efectivo 
(id_medio SERIAL PRIMARY KEY,
monto DECIMAL(12,2),
cotizacion_en_pesos DECIMAL(12,2),
id_moneda INTEGER,
id_pago INTEGER);


CREATE TABLE tpdds.conserje
(id_conserje SERIAL PRIMARY KEY,
nombre VARCHAR(100),
apellido VARCHAR(100),
contrasenia VARCHAR(100),
usuario VARCHAR(100) UNIQUE);


CREATE TABLE tpdds.cheque 
(id_medio SERIAL PRIMARY KEY,
monto DECIMAL(12,2),
cotizacion_en_pesos DECIMAL(12,2),
nro_cheque INTEGER,
plaza VARCHAR(100),
fecha_cobro DATE,
estado VARCHAR(100),
id_banco INTEGER,
id_moneda INTEGER,
id_pago INTEGER);


CREATE TABLE tpdds.tarjeta_de_debito 
(id_medio SERIAL PRIMARY KEY,
monto DECIMAL(12,2),
cotizacion_en_pesos DECIMAL(12,2),
cvv VARCHAR(3),
nro_tarjeta VARCHAR(100),
fecha_vencimiento DATE,
id_tipo_tarjeta INTEGER,
id_moneda INTEGER,
id_pago INTEGER);


CREATE TABLE tpdds.tarjeta_de_credito 
(id_medio SERIAL PRIMARY KEY,
monto DECIMAL(12,2),
cotizacion_en_pesos DECIMAL(12,2),
cvv VARCHAR(3),
cuotas INTEGER,
nro_tarjeta VARCHAR(100),
fecha_vencimiento DATE,
id_tipo_tarjeta INTEGER,
id_moneda INTEGER,
id_pago INTEGER);


-- Agregando CONSTRAINT FK


-- Tabla Tarjeta Credito
ALTER TABLE tpdds.tarjeta_de_credito
ADD CONSTRAINT fk_tipo_tarjeta FOREIGN KEY (id_tipo_tarjeta) REFERENCES tpdds.tipo_tarjeta (id_tipo_tarjeta),
ADD CONSTRAINT fk_moneda FOREIGN KEY (id_moneda) REFERENCES tpdds.moneda(id_moneda),
ADD CONSTRAINT fk_pago FOREIGN KEY (id_pago) REFERENCES tpdds.pago(id_pago);


-- Tabla Tarjeta Débito


ALTER TABLE tpdds.tarjeta_de_debito
ADD CONSTRAINT fk_tipo_tarjeta FOREIGN KEY (id_tipo_tarjeta) REFERENCES tpdds.tipo_tarjeta (id_tipo_tarjeta),
ADD CONSTRAINT fk_moneda FOREIGN KEY (id_moneda) REFERENCES tpdds.moneda(id_moneda),
ADD CONSTRAINT fk_pago FOREIGN KEY (id_pago) REFERENCES tpdds.pago(id_pago);


-- Tabla Cheque


ALTER TABLE tpdds.cheque
ADD CONSTRAINT fk_banco FOREIGN KEY (id_banco) REFERENCES tpdds.banco (id_banco),
ADD CONSTRAINT fk_moneda FOREIGN KEY (id_moneda) REFERENCES tpdds.moneda(id_moneda),
ADD CONSTRAINT fk_pago FOREIGN KEY (id_pago) REFERENCES tpdds.pago(id_pago);


-- Tabla Efectivo
ALTER TABLE tpdds.efectivo
ADD CONSTRAINT fk_moneda FOREIGN KEY (id_moneda) REFERENCES tpdds.moneda(id_moneda),
ADD CONSTRAINT fk_pago FOREIGN KEY (id_pago) REFERENCES tpdds.pago(id_pago);


-- Tabla Pago


ALTER TABLE tpdds.pago
ADD CONSTRAINT fk_factura FOREIGN KEY (id_factura) REFERENCES tpdds.factura(id_factura);


-- Tabla Pasajero


ALTER TABLE tpdds.pasajero
ADD CONSTRAINT fk_posicion_iva FOREIGN KEY (id_posicion_iva) REFERENCES tpdds.posicion_iva (id_posicion_iva),
ADD CONSTRAINT fk_direccion FOREIGN KEY (id_direccion) REFERENCES tpdds.direccion (id_direccion),
ADD CONSTRAINT fk_tipo_documento FOREIGN KEY (id_tipo_documento) REFERENCES tpdds.tipo_documento (id_tipo_documento),
ADD CONSTRAINT fk_nacionalidad FOREIGN KEY (nacionalidad) REFERENCES tpdds.pais (id_pais);

-- Tabla Direccion


ALTER TABLE tpdds.direccion
ADD CONSTRAINT fk_ciudad FOREIGN KEY (id_ciudad) REFERENCES tpdds.ciudad (id_ciudad);


-- Tabla Ciudad


ALTER TABLE tpdds.ciudad
ADD CONSTRAINT fk_provincia FOREIGN KEY (id_provincia) REFERENCES tpdds.provincia (id_provincia);


-- Tabla Provincia


ALTER TABLE tpdds.provincia
ADD CONSTRAINT fk_pais FOREIGN KEY (id_pais) REFERENCES tpdds.pais (id_pais);


-- Tabla Responsable_Pago_Tercero


ALTER TABLE tpdds.responsable_pago_tercero
ADD CONSTRAINT fk_direccion FOREIGN KEY (id_direccion) REFERENCES tpdds.direccion (id_direccion);
-- Tabla Item_Factura


ALTER TABLE tpdds.item_factura
ADD CONSTRAINT fk_factura FOREIGN KEY (id_factura) REFERENCES tpdds.factura (id_factura),
ADD CONSTRAINT fk_servicio FOREIGN KEY (id_servicio) REFERENCES tpdds.servicio (id_servicio);


-- Tabla Factura


ALTER TABLE tpdds.factura
ADD CONSTRAINT fk_nota FOREIGN KEY (id_nota) REFERENCES tpdds.nota_de_credito (id_nota),
ADD CONSTRAINT fk_pasajero FOREIGN KEY (id_pasajero) REFERENCES tpdds.pasajero (id_pasajero),
ADD CONSTRAINT fk_responsable_tercero FOREIGN KEY (id_responsable_tercero) REFERENCES tpdds.responsable_pago_tercero (id_responsable_tercero);


-- Tabla Acompaniante


ALTER TABLE tpdds.acompaniante
ADD CONSTRAINT fk_ocupacion FOREIGN KEY (id_ocupacion) REFERENCES tpdds.ocupacion (id_ocupacion),
ADD CONSTRAINT fk_pasajero FOREIGN KEY (id_pasajero) REFERENCES tpdds.pasajero (id_pasajero);


-- Tabla Servicio


ALTER TABLE tpdds.servicio
ADD CONSTRAINT fk_habitacion FOREIGN KEY (id_habitacion) REFERENCES tpdds.habitacion (id_habitacion);


-- Tabla Habitacion


ALTER TABLE tpdds.habitacion
ADD CONSTRAINT fk_tipo_habitacion FOREIGN KEY (id_tipo_habitacion) REFERENCES tpdds.tipo_habitacion(id_tipo_habitacion);


-- Tabla Reserva


ALTER TABLE tpdds.reserva
ADD CONSTRAINT fk_id_responsable_reserva FOREIGN KEY (id_responsable_reserva) REFERENCES tpdds.responsable_reserva (id_responsable_reserva),
ADD CONSTRAINT fk_id_habitacion FOREIGN KEY (id_habitacion) REFERENCES tpdds.habitacion(id_habitacion);


-- Tabla Costo_por_noche


ALTER TABLE tpdds.costo_por_noche
ADD CONSTRAINT fk_tipo_habitacion FOREIGN KEY (id_tipo_habitacion) REFERENCES tpdds.tipo_habitacion(id_tipo_habitacion);








-- Tabla Ocupacion


ALTER TABLE tpdds.ocupacion
ADD CONSTRAINT fk_habitacion FOREIGN KEY (id_habitacion) REFERENCES tpdds.habitacion(id_habitacion),
ADD CONSTRAINT fk_id_pasajero_responsable FOREIGN KEY (id_pasajero_responsable) REFERENCES tpdds.pasajero(id_pasajero);




-- Tabla Nota_de_credito


ALTER TABLE tpdds.nota_de_credito
ADD CONSTRAINT fk_id_responsable_tercero FOREIGN KEY (id_responsable_tercero) REFERENCES tpdds.responsable_pago_tercero(id_responsable_tercero),
ADD CONSTRAINT fk_id_pasajero FOREIGN KEY (id_pasajero) REFERENCES tpdds.pasajero(id_pasajero);

--INSERT INTO tpdds.tipo_documento (tipo) VALUES 
--('DNI'),('LC'),('LE'),('Pasaporte'),('Otro');

INSERT INTO tpdds.pais(nombre) VALUES ('qsy');

INSERT INTO tpdds.provincia(nombre,id_pais) VALUES ('prov',1);

INSERT INTO tpdds.ciudad(nombre,id_provincia) VALUES ('kkk',1);

INSERT INTO tpdds.tipo_documento(tipo) VALUES
('DNI'),('LC'),('LE'),('Pasaporte'),('Otro');

INSERT INTO tpdds.posicion_iva(posicion) VALUES
('C.F.'),('R.I.'),('Monotributo');

INSERT INTO tpdds.direccion(piso,calle,dpto,nro_calle,codigo_postal,id_ciudad) VALUES
(1,'callesita linda','A24',225,'SF252',1);

INSERT INTO tpdds.pasajero(nombres,apellido,nro_documento,ocupacion,email,cuit,nacionalidad,telefono,fecha_nacimiento,id_posicion_iva,id_direccion,id_tipo_documento) VALUES 
('Pepito Pica','Piedra','52522525','Técnico especialista en refrigeración de materiales de construcción',
 'ppp@gmail.com','22-52522525-6',1,'3425151789','2001-05-04',1,1,1); 
 
INSERT INTO tpdds.conserje(nombre,apellido,usuario,contrasenia) VALUES
('Maria','Chucena','MCHUC1','PASSWORD');