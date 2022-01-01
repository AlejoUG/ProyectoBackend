create database bd_Banco;
USE bd_Banco;

create table clientes(
	num_Cuenta integer not null,
    tip_Cuenta varchar(50) not null,
    nombres varchar(50) not null,
    apellidos varchar(50) not null,
    tp_Identificacion varchar(50) not null,
    num_Identificacion integer UNIQUE,
    email  varchar(50) not null unique,  
    fecha_Nacimiento varchar(50) not null,
    fecha_Ingreso  varchar(50) not null,
    estado varchar(50) not null,
    saldo integer,
    primary key (num_Cuenta)
);

create table movimientos(
	id int auto_increment primary key,
    numCuenta int not null,
    tpMovimiento varchar(50) not null,
    valorMonto int not null,
    fechaMovimiento TIMESTAMP not null,
    FOREIGN KEY (numCuenta) REFERENCES clientes (numCuenta)
);

/*Insertar clientess*/
insert into clientes (num_Cuenta,tip_Cuenta, Nombres, Apellidos, tp_Identificacion, num_Identificacion, 
email, fecha_Nacimiento, fecha_Ingreso, estado) 
values (FLOOR(1+ RAND() * 9999), 'Ahorro', 'Alejandro', 'Utria Garcia', 'C.C', 1065829434,
'alejandroutriag@gmail.com', '1996-10-03',now(), 'Activo');

insert into clientes (num_Cuenta,tip_Cuenta, Nombres, Apellidos, tp_Identificacion, num_Identificacion, 
email, fecha_Nacimiento, fecha_Ingreso, estado) 
values (FLOOR(1+ RAND() * 9999), 'Ahorro', 'Alejandro', 'Utria Garcia', 'C.C', 106582944,
'alejandroutriag@hotmail.com', '1996-10-03',(now()), 'Activo');

/*Insertar Movimientos*/
insert into movimientos (numCuenta, tpMovimiento, valorMonto, fechaMovimiento) 
values (2094, 'Transferencia', 60000, now());
insert into movimientos (numCuenta, tpMovimiento, valorMonto, fechaMovimiento) 
values (5174, 'consignacion',100000, now());

/*Eliminar tablas*/
drop table movimientos;
drop table clientes;

/* Consultar tablas*/
select * from clientes;
select * from movimientos;
select now();
/*Actualizar campo de tabla*/
UPDATE clientes SET cl_email ='andres@hotmail.com' WHERE cl_numid = 106582944;
/*DELETE FROM clientes WHERE cl_numid=106582944;
select * from productos;
DELETE FROM productos WHERE pro_numcuenta=106582944;
ROUND((RAND() * (10 - 1)) + 1)*/