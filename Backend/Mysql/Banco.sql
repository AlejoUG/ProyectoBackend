create database bd_Banco;
USE bd_Banco;

create table clientes(
	idcliente bigint auto_increment,
    num_Identificacion bigint unique not null,
    nombres varchar(50) not null,
    apellidos varchar(50) not null,
    tp_Identificacion varchar(50) not null,
    email  varchar(50) not null,  
    fecha_Nacimiento Varchar(50) not null,
    fecha_Creacion  varchar(50) not null,
    primary key(idcliente)
);

create table productos(
	num_Cuenta bigint unique not null,
    tip_Cuenta varchar(50) not null,
    clidcliente Bigint not null,
    estado varchar(50) not null,
    saldo double not null,
    fecha_apertura varchar(50) not null,
    primary key (num_Cuenta)
   /* FOREIGN KEY (clnum_Identificacion) REFERENCES clientes (num_Identificacion)*/
);

create table movimientos(
	idmovimiento bigint auto_increment primary key,
    pronum_Cuenta bigint not null,
    pronum_Cuenta2 bigint,
    tp_Movimiento varchar(50) not null,
    monto double not null,
    gmf double,
    fecha_Movimiento varchar(50) not null,
    descripcion varchar(255)
    
    /*FOREIGN KEY (pronum_Cuenta) REFERENCES productos (num_Cuenta)*/
);

/*Insertar clientess*/
insert into clientes (nombres, apellidos, tp_Identificacion, num_Identificacion, 
email, fecha_Nacimiento, fecha_Creacion) 
values ('Alejandro', 'Utria Garcia', 'C.C', 1065829434, 'alejandroutriag@gmail.com',
 '1996-10-03', now());

insert into clientes (Nombres, Apellidos, tp_Identificacion, num_Identificacion, 
email, fecha_Nacimiento, fecha_Creacion) 
values ('Rafael Jose', 'Mercado Arias', 'C.C', 87945612, 'rafael@gmail.com',
 '1985-02-10', now());

/*Insertar Productos*/
 insert into productos (num_cuenta, tip_Cuenta, clidcliente, estado,
saldo, fecha_apertura) 
values (78260183, 'Ahorro', 1, 'Activo', 0,'2022-01-23 19:40:56');

  insert into productos (num_cuenta, tip_Cuenta, clidcliente, estado,
saldo, fecha_apertura) 
values ((RAND() * (1000000000 - 1)) + 1, 'Corriente', 1, 'Activo', 0,
 now());
 
 insert into productos (num_cuenta, tip_Cuenta, clidcliente, estado,
saldo, fecha_apertura) 
values (1987013452, 'Corriente', 2, 'Activo', 0,
 '2022-01-21 14:35:20');
 
  insert into productos (num_cuenta, tip_Cuenta, clidcliente, estado,
saldo, fecha_apertura) 
values (1987013453, 'Ahorro', 2, 'Activo', 0,
 '2022-01-12 07:35:20');

/*Insertar Movimientos*/
insert into movimientos (pronum_Cuenta, tp_Movimiento, monto, fecha_Movimiento,descripcion,gmf) 
values (1987013452, 'Consignacion', 60000, now(),'Consignacion',240);
update productos set saldo=60000 where num_Cuenta=1987013452;
insert into movimientos (pronum_Cuenta, tp_Movimiento, monto, fecha_Movimiento,descripcion) 
values (78260183, 'Consignacion', 160000, now(),'Consignacion');
update productos set saldo=160000 where num_Cuenta=78260183;

/*Eliminar tablas*/
drop table movimientos;
drop table productos;
drop table clientes;

/* Consultar tablas*/
select * from clientes;
select * from movimientos;
select * from productos;
select now();

DELETE FROM productos WHERE num_cuenta=813475990;
delete from Clientes where (idcliente=1 and exists (select estado from Productos p where p.clidcliente=1 and p.estado=('Activo' or 'Inactivo')));
select if(estado=('Activo' or 'Inactivo'), 'true', 'false') from productos where clidcliente=1;
set sql_safe_updates=0;
update productos set estado='Cancelado' where num_Cuenta=690337057;
delete from Clientes where num_Identificacion=1065829434 and !exists (select estado from Productos p where p.clnum_Identificacion=1065829434 and p.estado=('Activo' or 'Inactivo'));
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