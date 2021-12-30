create database bd_Banco;
USE bd_Banco;

create table clientes(
	cl_numid int primary key,
    cl_name varchar(30) not null,
    cl_lastnames varchar(30) not null,
    cl_tpid varchar(15) not null,
    cl_email  varchar(50) not null,  
    cl_fNacto date not null,
    cl_fingreso  TIMESTAMP
);

create table productos(
	pro_numcuenta int primary key,
    cl_numid int not null,
    pro_tipcuenta varchar(10) not null,
    pro_status varchar(10) not null,
	pro_saldo int,
    pro_fapertura TIMESTAMP not null,
    FOREIGN KEY (cl_numid) REFERENCES clientes (cl_numid)
);

create table movimientos(
	mov_id int auto_increment primary key,
    pro_numcuenta int not null,
    mov_tipmov varchar(15) not null,
    monto int not null,
    cl_numid int not null,
    mov_fechamov TIMESTAMP not null,
    FOREIGN KEY (cl_numid) REFERENCES clientes (cl_numid),
    FOREIGN KEY (pro_numcuenta) REFERENCES productos (pro_numcuenta)
);

/*Insertar Clientes*/
insert into clientes (cl_numid, cl_name, cl_lastnames, cl_tpid, cl_email, cl_fNacto, cl_fingreso) 
values (106582944, 'Alejandro', 'Utria Garcia', 'T.I','cgarcia@mail.com', '1996-10-03',now());
insert into clientes (cl_numid, cl_name, cl_lastnames, cl_tpid, cl_email, cl_fNacto, cl_fingreso) 
values (1065829434, 'Alejandro', 'Utria Garcia', 'C.C','garcia@gmail.com', '1996-11-03',now());

/*Insertar Productos*/
insert into productos (pro_numcuenta, cl_numid, pro_tipcuenta, pro_status, pro_saldo, pro_fapertura) 
values (FLOOR(1+ RAND() * 9999), 106582944, 'ahorros', 'inactivo', 2000000, now());
insert into productos (pro_numcuenta, cl_numid, pro_tipcuenta, pro_status, pro_saldo, pro_fapertura) 
values (FLOOR(1+ RAND() * 9999), 1065829434, 'debito', 'activo', 200000, now());

/*Insertar Movimientos*/
insert into movimientos (pro_numcuenta, mov_tipmov, monto, cl_numid, mov_fechamov) 
values (7193, 'Transferencia', 60000, 106582944, now());
insert into movimientos (pro_numcuenta, mov_tipmov, monto, cl_numid, mov_fechamov) 
values (1679, 'Retiro', 10000, 1065829434, now());

/*Eliminar tablas*/
drop table movimientos;
drop table productos; 
drop table clientes;

/*DELETE FROM clientes WHERE cl_numid=106582944;
select * from productos;
DELETE FROM productos WHERE pro_numcuenta=106582944;
ROUND((RAND() * (10 - 1)) + 1)*/