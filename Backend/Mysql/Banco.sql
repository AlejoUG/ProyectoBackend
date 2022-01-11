create database bd_Banco;
USE bd_Banco;

create table clientes(
	num_Identificacion bigint unique,
    nombres varchar(50) not null,
    apellidos varchar(50) not null,
    tp_Identificacion varchar(50) not null,
    email  varchar(50) not null unique,  
    fecha_Nacimiento date not null,
    fecha_Creacion  timestamp not null,
    contrasenia varchar(50),
    primary key(num_Identificacion)
);

create table productos(
	num_Cuenta bigint unique not null,
    tip_Cuenta varchar(50) not null,
    cl_num_Identificacion Bigint not null unique,
    estado varchar(50) not null,
    saldo double not null,
    fecha_apertura varchar(50) not null,
    primary key (num_Cuenta),
    FOREIGN KEY (cl_num_Identificacion) REFERENCES clientes (num_Identificacion)
);
alter table productos modify saldo double not null;

create table movimientos(
	id bigint auto_increment primary key,
    pronum_Cuenta bigint not null,
    tp_Movimiento varchar(50) not null,
    monto double not null,
    fecha_Movimiento TIMESTAMP not null,
    FOREIGN KEY (pronum_Cuenta) REFERENCES productos (num_Cuenta)
);

/*Insertar clientess*/
insert into clientes (nombres, apellidos, tp_Identificacion, num_Identificacion, 
email, fecha_Nacimiento, fecha_Creacion, contrasenia) 
values ('Alejandro', 'Utria Garcia', 'C.C', 1065829434, 'alejandroutriag@gmail.com',
 '1996-10-03', now(), '123456789aug');

insert into clientes (Nombres, Apellidos, tp_Identificacion, num_Identificacion, 
email, fecha_Nacimiento, fecha_Creacion, contrasenia) 
values ('Rafael Jose', 'Mercado Arias', 'C.C', 87945612, 'rafael@gmail.com',
 '1985-02-10', now(), '123456789fgh');

/*Insertar Productos*/


/*Insertar Movimientos*/
insert into movimientos (pronum_Cuenta, tp_Movimiento, monto, fecha_Movimiento) 
values (1987013452, 'Transferencia', 60000.021456, now());

/*Eliminar tablas*/
drop table movimientos;
drop table productos;
drop table clientes;

/* Consultar tablas*/
select * from clientes;
select * from movimientos;
select * from productos;
select now();

delete from clientes where num_Identificacion=1065829434 and exists (select estado from productos p
where p.cl_num_Identificacion=1065829434 and p.estado='Cancelado');
update productos set estado='Activo', saldo=20000 where num_cuenta=2546714204;
/*Actualizar campo de tabla*/
UPDATE clientes SET cl_email ='andres@hotmail.com' WHERE cl_numid = 106582944;

update productos, (select pronum_Cuenta, sum(monto) mysum from movimientos where tp_Movimiento='Consignacion' and pronum_Cuenta=4418795120) m set saldo=mysum where num_Cuenta=4418795120;
update productos, (select pronum_Cuenta, sum(monto) myrest from movimientos where (tp_Movimiento='Transferencia' or tp_Movimiento='Retiro') and pronum_Cuenta=4418795120) m set saldo=saldo-myrest where num_Cuenta=4418795120;

/*DELETE FROM clientes WHERE cl_numid=106582944;
select * from productos;
DELETE FROM productos WHERE pro_numcuenta=106582944;
ROUND((RAND() * (10 - 1)) + 1)*/