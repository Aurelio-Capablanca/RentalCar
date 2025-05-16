Create database Sistema_transporte
use Sistema_transporte
use master
drop database Sistema_transporte


create table EstadoPersona(
idEstadoPersonal int not null Primary key,
estadoPersonal varchar (25) not null 
)

create table TipoPersona(
idTipoPersonal int not null Primary key,
tipoPersonal varchar (25) not null
)

create table Empresa (
idEmpresa int not null Primary key,
nombre varchar (25) not null,
representanteLegal character(25) not null,
telefono varchar (9) not null,
ubicacion varchar (100) not null,
nit character (20) not null,
logo nvarchar (max) not null,
fechaRegistro date not null
)


create table Pregunta (
idPregunta int not null Primary key,
pregunta1 varchar(100) not null
)

Create table Licencia (
idLicencia int not null Primary key,
licencia Character (25) not null
)

create table Persona (
idPersona int not null Primary key,
nombres varchar (25) not null,
apellidos varchar(25) not null,
fechaNacimiento date not null,
direccion varchar (150) not null,
telefono varchar (9) not null,
dui varchar (15) Unique not null,
correo varchar (100) not null,
usuario varchar (40) not null,
clave varchar (40) not null,
nSeguro varchar (40) not null,
fechaRegistro date not null,
codigoConfirmacion varchar (15),
respuesta1 varchar (100),
respuesta2 varchar (100),
idLicencia int references Licencia(idLicencia),
idPregunta int  references Pregunta(idPregunta),
idPregunta2 int references Pregunta(idPregunta),
idEstadoPersonal int not null references EstadoPersona(idEstadoPersonal),
idTipoPersonal int not null references TipoPersona(idTipoPersonal),
idEmpresa int not null references Empresa(idEmpresa)
) 

create table TipoUnidad(
idTipoUni int not null Primary key,
tipoUnidad varchar (55) not null
) 

create table EstadoUnidad (
idEstadoUni int not null Primary key,
estadoUnidad varchar(55) not null
) 

create table Marca(
idMarca int not null Primary key,
nombreMarca varchar (55) not null
)


create table Recurso(
idRecurso int not null Primary key,
nombreRecurso varchar(100) not null,
cantidadRecurso numeric(9) not null,
fechaRecurso date not null
)

create table Unidad(
idUnidad int not null primary key ,
modelo varchar (25) not null,
capacidad numeric (35) not null,
codigo numeric (9) Unique not null,
placas varchar (25) Unique not null,
fechaLanzamiento date not null,
fechaRegistro date not null,
idMarca int not null references Marca(idMarca),
idTipoUni int not null references TipoUnidad(idTipoUni),
idEstadoUni int not null references EstadoUnidad(idEstadoUni)
) 

create table DetalleRecurso(
idDetalle int not null Primary key,
idRecurso int not null references Recurso(idRecurso),
idUnidad int not null references Unidad(idUnidad)
)


create table EstadoServicio(
idEstadoServicio int not null Primary key,
estadoServicio varchar (25) not null
)


create table TipoServicio(
idTipoServicio int not null primary key,
tipoServicio character (25) not null
)

create table Servicio(
idServicio int not null Primary key,
codigo varchar (25) not null,
nombreServicio varchar (255) not null,
descripcion varchar (250) not null,
fechaRegistro date not null,
idTipoServicio int not null references TipoServicio(idTipoServicio),
idEstadoServicio int not null references EstadoServicio (idEstadoServicio)
)


create table TipoCliente(
idTipoCliente int not null Primary key, 
tipoCliente varchar (25) not null
)

create table EstadoCliente(
idEstadoCliente int not null Primary key,
estadoCliente varchar(25) not null
)


create table Cliente (
idCliente int not null Primary key,
nombre varchar (150) not null,
apellido varchar (25) null,
representanteLegal varchar (30) not null,
telefono varchar (9) not null,
direccion varchar(100) not null,
nit varchar (60) Unique not null,
fechaRegistro date not null,
idTipoCliente int not null references TipoCliente (idTipoCliente),
idEstadoCliente int not null references EstadoCliente (idEstadoCliente)
) 


create table TipoPago(
idPago int not null Primary key,
tipoPago varchar (25) not null
)


create table Contrato (
idContrato int not null Primary key,
intereses numeric (3,2) not null,
monto numeric (6,2) not null,
fecha_inicio date not null,
fecha_fin date not null,
fechaRegistro date not null,
idServicio int not null references Servicio(idServicio),
idCliente int not null references Cliente(idCliente),
idPago int not null references TipoPago (idPago)
) 


create table TipoFactura(
idTipoFactura int not null Primary key,
tipoFactura character(30) not null
)


create table Departamento(
idDepartamento int not null Primary key,
departamento varchar(25) Unique not null
)

create table TipoViaje(
idTipoViaje int not null Primary key,
tipoViaje varchar(25) not null
)


create table Viaje(
idViaje int not null Primary key,
direccion varchar(225),
precio numeric(6,2) not null,
horaSalida date not null,
horaRetorno date not null,
fechaRegistro date not null,
idPersona int not null references Persona(idPersona),
idUnidad int not null references Unidad(idUnidad),
idCliente int not null references Cliente(idCliente),
idDepartamento int not null references Departamento (idDepartamento),
idTipoViaje int not null references TipoViaje (idTipoViaje)
) 

create table Bitacora(
idBitacora int not null identity (1,1) primary key,
persona varchar(25),
fecha datetime,
accion varchar(50)
)  

alter table Bitacora alter column persona varchar(55)

create table Factura(
idFactura int not null Primary key,
monto numeric (7,2) not null,
fechaRegistro date not null,
numeroTarjeta varchar (19),
cantidadEfectivo numeric (8,2),
cambio varchar (max),
giro varchar(55) null,
razonSocial character (45) Default 'Hermes y Asociados',
idTipoFactura int not null references TipoFactura(idTipoFactura),
idContrato int not null references Contrato(idContrato)
)
------------------------------------------------------Inserts -----------------------------------------------------------------------------------------------
insert into EstadoPersona  values
(1,'Activo'),
(2,'Inactivo'),
(3,'Incapacitado'),
(4,'Despedido'),
(5,'Suspendido')

insert into TipoPersona values
(1,'Root'),
(2,'Administrador'),
(3,'Coordinador'),
(4,'Operador'),
(5,'Motorista')


insert into Pregunta values 
(1,'¿Cual es tu color favorito?'),
(2,'¿Cual es la serie de tu infancia?'),
(3, '¿Cual fue el nombre de tu mascota?'),
(4, '¿Cual es el segundo nombre de nombre de tu madre?'),
(5,'¿Cual es tu comida favorita?') 

insert into Licencia values 
(1,''),
(2,'Liviana'),
(3,'Pesada'),
(4,'Particular'),
(5,'Pesada-T')

------------------------------ Para Probar Empresa no ejecutar Personal y Empresa -----------------------------------------------------------------------------------------------------------
insert into Empresa values
(1,'Hermes Software','-','4234-4568','San Salvador','12343-432465','-','20/07/2020')


insert into Persona (idPersona, nombres, apellidos, fechaNacimiento, direccion, telefono, dui, correo,usuario, clave ,nSeguro, fechaRegistro,respuesta1, respuesta2 , idLicencia,idPregunta,idPregunta2 ,idEstadoPersonal, idTipoPersonal, idEmpresa) 
 values
(1,'Adalberto Francisco','Aguilar Paredes','09/08/1999','San Salvador','9242-5945','67456567 - 6','adal@gmail','arwen','202cb962ac59075b964b07152d234b70','434375554','20/07/2020','rojo','starwars',1,1,2,1,1,1),
(2,'Alberto Antonio','Ramirez Majano','10/03/1999','San Salvador','3244-4234','07556567 - 8','alberto@gmail.com','alberto','202cb962ac59075b964b07152d234b70','94354353','20/07/2020','azul','ulises',1,1,3,1,2,1),
(3,'Fernando José','Aquino Valle','20/01/1999','San Salvador','5644-2345','89457567 - 1','fernando@gmail.com','fernando','202cb962ac59075b964b07152d234b70','645843532','20/07/2020','negro','arroz',1,1,5,1,3,1),
(4,'Guillermo David','Hernandez Merino','21/01/1999','San Salvador','5644-2345','69469560 - 9','david@gmail.com','david','202cb962ac59075b964b07152d234b70','641243532','20/07/2020','rosado','pizza',1,1,5,1,4,1),
(5,'Samuel Eduardo','Magaña Martinez','20/02/1999','San Salvador','5644-2345','60469560 - 7','samuel@gmail.com','samuel','202cb962ac59075b964b07152d234b70','645843532','20/07/2020','amarillo','scot',1,1,3,1,4,1),
(6,'Antonio José','Cordoba Jimenes','19/05/1998','San Salvador','9442-5195','05778555 - 5','jose@gmail','-','-','435345554','07/09/2020','-','-',3,1,2,1,5,1),
(7,'Marcelino Roberto','Castañeda Lopéz','20/03/2000','Sonsonate','9442-5195','05778565 - 9','roberto@gmail','-','-','435345554','07/09/2020','-','-',3,1,2,1,5,1),
(8,'Alejandro Joaquín','Gonzalez Perez','09/04/1999','Santa Ana','9772-5895','05788555 - 3','ale@gmail','-','-','435345554','07/09/2020','-','-',3,1,2,1,5,1),
(9,'Cesar Francisco','Sosa Mitre','05/10/1992','San Vicente','9442-5105','95768555 - 0','Frank@gmail','-','-','435345554','07/09/2020','-','-',3,1,2,1,5,1),
(10,'Angel Manuel','Pineda Hernandez','10/02/1999','Santa Ana','9042-5890','01738595 - 8','maunel@gmail','-','-','435345554','07/09/2020','-','-',3,1,2,4,5,1),
(11,'Andrés Leonardo','Perez Quintanilla','09/04/1999','La Libertad','7042-5195','05178535 - 2','andre@gmail','-','-','435345554','07/09/2020','-','-',3,1,2,5,5,1),
(12,'Juan Enrique','Vargas Solorzano','09/10/1989','Chalatenango','6402-5285','95878595 - 7','enrique@gmail','-','-','435345554','07/09/2020','-','-',3,1,2,3,5,1),
(13,'Omar Manuel','Fortin Turcios','09/02/1999','La Libertad','9740-5265','15678755 - 1','omar@gmail','-','-','435345554','07/09/2020','-','-',3,1,2,2,5,1),
(14,'Guillermo Armando','Perez Solorzano','09/04/1999','San Vicente','8462-5697','35778585 - 0','will@gmail','-','-','435345554','07/09/2020','-','-',3,1,2,2,5,1),
(15,'Francisco Javier','Muñoz Coronado','20/02/1999','San Salvador','8644-2748','89460560 - 0','samuel@gmail.com','javier','202cb962ac59075b964b07152d234b70','645813592','20/07/2020','amarillo','scot',1,1,3,2,4,1),
(16,'Jesús Rafael','Badía Costa','21/09/1998','San Salvador','6644-1245','90469560 - 5','samuel@gmail.com','rafa','202cb962ac59075b964b07152d234b70','745543530','20/07/2020','amarillo','scot',1,1,3,4,4,1),
(17,'José Luis','Hidalgo Puertas','23/07/1992','San Salvador','7644-2449','10469560 - 6','samuel@gmail.com','luis','202cb962ac59075b964b07152d234b70','842843531','20/07/2020','amarillo','scot',1,1,3,5,4,1),
(18,'Pedro José','García Ramos','01/02/1990','San Salvador','9644-2840','30469560 - 2','samuel@gmail.com','pedro','202cb962ac59075b964b07152d234b70','542843535','20/07/2020','amarillo','scot',1,1,3,4,4,1),
(19,'Miguel Ángel','Álvarez Párraga','12/02/1998','San Salvador','2674-2945','40469560 - 1','samuel@gmail.com','miguel','202cb962ac59075b964b07152d234b70','445743537','20/07/2020','amarillo','scot',1,1,3,5,4,1),
(20,'Luís Alfonso','Mena Galvan','20/01/2000','San Salvador','4634-2245','80469560 - 9','samuel@gmail.com','alfonso','202cb962ac59075b964b07152d234b70','845843536','20/07/2020','amarillo','scot',1,1,3,2,4,1),
(21,'Alberto Raúl','Sánchez Gutierrez','15/09/1993','San Salvador','3684-2045','00469560 - 8','samuel@gmail.com','raul','202cb962ac59075b964b07152d234b70','345043502','20/07/2020','amarillo','scot',1,1,3,2,4,1)
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
insert into TipoServicio values 
(1, '5 estrellas'), 
(2, '4 estrellas'), 
(3, '3 estrellas'), 
(4, '2 estrellas'), 
(5, '1 estrella')

insert into TipoUnidad values 
(1,'Automovil'),
(2,'Microbuses')

insert into EstadoUnidad values 
(1,'Activa'),
(2,'Fuera de servicio'),
(3,'En reparaciones'),
(4,'disponible'),
(5,'desechada')

insert into Marca values 
(1,'Toyota'),
(2,'Nissan'),
(3,'KIA')

insert into Recurso values
(1,'Cono',233,'20/07/2020'),
(2,'Llanta',455,'20/07/2020'),
(3,'Gato Hidraulico',566,'20/07/2020'),
(4,'Llaves para llantas',456,'20/07/2020'),
(5,'Alicates',56,'08/09/2020'),
(6,'Desatornilladores Philip',56,'08/09/2020'),
(7,'Linterna',45,'08/09/2020'),
(9,'Chaleco',67,'08/09/2020'),
(10,'Guantes',86,'08/09/2020')

insert into EstadoServicio values
(1,'Vigente'),
(2,'Expirado'),
(3,'en progreso'),
(4,'Cancelado'),
(5,'en tramite') 


insert into TipoCliente values
(1,'Entidad Corporativa'),
(2,'Persona Natural'),
(3,'Empresa'),
(4,'Hob'),
(5,'Firma corporativa')


insert into EstadoCliente values 
(1, 'Activo'),
(2, 'Inactivo')

insert into TipoPago values
(1,'Mensual'),
(2,'Anual'),
(3,'Semanal'),
(4,'Diario')

insert into TipoFactura values 
(1,'Factura Ordinaria'),
(2,'Factura Rectificativa'),
(3,'Factura proforma'),
(4,'Factura simplificada')

insert into TipoViaje values 
(1,'Empresarial'),
(2,'Turístico'),
(3,'Diaríos')

insert into Departamento values 
(1,'La Unión'),
(2,'La Libertad'),
(3,'Morazán'),
(4,'Chalatenango'),
(5,'La Paz')
------------------------------------ ejecutar al final ----------------------------------------------------
insert into Unidad values 
(1,'Nissan centra',12,435435434,'P 984 445','20/12/2010','20/07/2020',1,1,2),
(2,'Stonic',11,935284474,'P 352 485','20/07/2019','20/07/2020',3,1,1),
(3,'Rav4',5,495405430,'P 365 446','20/07/2019','20/07/2020',1,1,3),
(4,'Hilux',5,470536439,'P 345 447','20/08/2019','20/07/2020',1,1,4),
(5,'Kia rio',4,935435730,'P 946 795','08/01/2017','08/09/2020',3,1,5),
(6,'Kia ceed',4,03563577,'P 306 749','03/09/2018','08/09/2020',3,1,2),
(7,'Toyota Corolla',4,845037430,'P 965 749','06/04/2019','08/09/2020',1,1,2),
(8,'Toyota Prius',6,465405430,'P 849 510','09/06/2016','08/09/2020',1,1,3),
(9,'Nissan Urvan',13,635435436,'P 656 698','08/04/2018','08/09/2020',1,2,4),
(10,'Nissan Urvan',15,905930439,'P 609 700','09/10/2019','08/09/2020',1,2,2),
(11,'Nissan Urvan',15,335905436,'P 709 000','09/10/2017','08/09/2020',1,2,2),
(12,'Nissan Urvan',15,935985431,'P 007 761','09/10/2019','08/09/2020',1,2,2),
(13,'Nissan Urvan',15,935935432,'P 908 670','09/10/2017','08/09/2020',1,2,3),
(14,'Nissan Urvan',15,435975434,'P 101 890','09/10/2016','08/09/2020',1,2,3),
(15,'Nissan Urvan',15,835945431,'P 702 280','09/10/2017','08/09/2020',1,2,5),
(16,'Nissan Urvan',15,935555430,'P 400 790','09/10/2019','08/09/2020',1,2,4),
(17,'Nissan Urvan',15,235995439,'P 205 850','09/10/2020','08/09/2020',1,2,5),
(18,'Nissan Urvan',15,135565436,'P 107 450','09/10/2019','08/09/2020',1,2,4),
(19,'Nissan Urvan',15,635993543,'P 308 870','09/10/2017','08/09/2020',1,2,4),
(20,'Nissan Urvan',15,835934433,'P 489 230','09/10/2016','08/09/2020',1,2,2),
(21,'Nissan Urvan',15,035035035,'P 799 789','09/10/2019','08/09/2020',1,2,5)


insert into Cliente values 
(1,'Avianca Holdings','-','Anko van der werf','3424-3459','Santa elena','5345-490894-544-3','20/07/2020',1,1),
(2,'Napoleón Alexander','Aguilar Capablanca','-','7834-3444','Santa Ana','8945-470894-544-8','20/07/2020',2,1),
(3,'Kimberly Clark','-','Michael D. Hsu','5343-4322','Santa tecla','0375-460894-544-8','20/07/2020',5,1),
(4,'Termoencogibles, S.A. de C.V.','-','Rodrigo Tona','6345-4352','Antiguo Cuscaltán','5345-090894-594-7','20/07/2020',5,1),
(5,'Hanesbrands El Salvador, Ldta. de C.V.','-','Gerald W. Evans Jr. ','5422-5432','San Juan Opico','7345-497894-544-7','20/07/2020',1,1),
(6,'Grupo Q','-','Juán Federico Salaverría','4354-453','Ilopango','8845-498894-544-8','08/09/2020',1,1),
(7,'Diario El Mundo','-','Juan José Borja Papini','4535-444','San Salvador','5845-498894-584-3','08/09/2020',1,1),
(8,'AVX Industries Pte. Ltd','-','John Sarvis','8879-7999','San Salvador','5245-490894-544-1','08/09/2020',5,1),
(9,'Laboratorios Vijosa, S.A. de C.V.','-','Murray Mesa','4354-3544','Santa Ana','5045-490894-544-0','08/09/2020',3,1),
(10,'Livsmart Américas, S.A. de C.V.','-','Luis Murillo','9934-7343','San Salvador','7345-490894-544-8','08/09/2020',4,1),
(11,'Carlos Fernando','Alvarenga Solorzano','-','4355-7234','Sonsonate','1345-408894-544-6','08/09/2020',2,1),
(12,'Sonia Isabel','Rivera López','-','2345-6334','Santa Ana','5445-497894-544-9','08/09/2020',2,1),
(13,'José Armando','Vega Nochez','-','3245-6343','San Salvador','1325-499894-577-7','08/09/2020',2,1),
(14,'Francisco Alexander','Herrera Silva','-','2345-6333','Sonsonate','8745-499894-564-0','08/09/2002',2,1),
(15,'Vicente Andrés','Jimeno Oliver','-','3855-4045','San Miguel','9345-490894-566-9','09/09/2020',2,1),
(16,'María Carmen','García Luque','-','3759-4575','San Salvador','9345-590094-857-0','09/09/2020',2,1),
(17,'Juan Carlos','Gutiérrez Gómez','-','3953-4545','La Paz','9345-490894-516-9','09/09/2020',2,1),
(18,'Alejandro Daniel','Araujo Fernández','-','3056-0545','Cabañas','0345-490894-536-9','09/09/2020',2,1),
(19,'María Victoria','Hernández Revilla','-','3251-8585','San Vicente','9545-490894-356-9','09/09/2020',2,1),
(20,'David Adrián','Galán Cabeza','-','3153-2535','Santa Ana','9345-490894-556-9','09/09/2020',2,1),
(21,'Sergio','López Maestre','-','3559-1515','Santa Elena','9345-490890-557-9','09/09/2020',2,1),
(22,'María Teresa','Villar Navarro','-','3356-7575','Chalatenango','9341-490894-556-9','09/09/2020',2,1),
(23,'Vicente Andrés','Marín Ferrer','-','3655-9549','Cuscatlan','9345-470894-510-9','09/09/2020',2,1),
(24,'Juan Enrique','Ojeda Hernández','-','3744-1540','San Salvador','9345-490894-456-9','09/09/2020',2,1),
(25,'Francisco Javier','García Soria','-','3953-6545','Cabañas','9345-400894-656-9','09/09/2020',2,1),
(26,'Juan Pablo','López Carracedo','-','3256-3575','San Esteban','9345-590894-516-9','09/09/2020',2,1),
(27,'Nuria Raquel','Sala Maroto','-','3351-8545','San Miguel','9345-490994-456-9','09/09/2020',2,1),
(28,'David Adalberto','Ortega Guerrero','-','3150-0541','Santa Ana','9355-490894-576-9','09/09/2020',2,1)


insert into Viaje values
(1, 'Viaje turistica a la Costa del Sol',12.45,'05/12/2020 08:09:05','06/12/2020 16:01:05','20/07/2020',6,1,12,2,2),
(2,'Viaje a Instalaciones Avianca Holdings',25.55,'15/05/2020 07:00:00','15/05/2020 23:59:59','20/07/2020',7,1,1,4,1),
(3,'Viaje a Royal Decamerón Salinitas',50.60,'16/09/2020 14:05:00','16/09/2020 16:56:03','20/07/2020',8,1,2,5,1),
(4,'Viaje Empresarial a regus',36.99,'17/07/2020 07:05:00','17/07/2020 13:00:59','20/07/2020',9,1,5,1,3),
(5,'Viaje a Aeropuerto nacional',34.55,'07/04/2020 06:00:00','07/04/2020 07:59:00','20/07/2020',10,1,1,1,2),
(6, 'Viaje a World Trade Center',150.45,'09/12/2020 08:09:05','09/12/2020 12:01:05','20/07/2020',13,4,4,3,1),
(7,'Viaje a Joya de Ceren',24.56,'08/08/2020 08:00:00','08/08/2020 12:06:07','07/09/2020',14,1,2,2,2),
(8,'Viaje al Lago de Coatepeque',14.78,'07/10/2020 09:00:01','10/10/2020 16:00:00','07/09/2020',6,2,15,3,2),
(9,'Viaje de recogimiento de personal del turno matutino',156.55,'08/09/2020 14:00:00','08/09/2020 17:00:00','07/09/2020',9,1,1,2,3),
(10, 'Viaje de recogimiento de personal del turno vespertino',158.45,'09/12/2020 08:09:05','09/12/2021 12:01:05','20/07/2020',10,4,6,3,1),
(11,'Viaje de capacitación a plantel itca',40.78,'12/09/2020 08:00:00','12/09/2020 13:00:00','08/09/2020',7,4,7,3,1),
(12,'Viaje a San Vicente',40.00,'13/09/2020 08:00:00','13/09/2020 15:00:00','08/09/2020',9,3,13,4,2),
(13,'Viaje a San Andres',39.00,'15/09/2020','15/09/2020','08/09/2020',12,3,2,4,2),
(14,'Tour de 5 días en Antigua Guatemala',36.00,'17/09/2020','22/09/2020','08/09/2020',13,3,2,4,2),
(15,'Viaje por ruta de las flores',49.55,'01/10/2020','01/10/2020','08/09/2020',8,4,12,4,1),
(16,'Plantel de Capacitaciones', 159.99,'2020-09-30','2020-10-03','2020-09-21',7,1,8,1,1),
(17,'Residencias de Empleados',500.98,'2020-09-30','2020-09-30','2020-09-21',11,21,8,1,1),
(18,'Viaje para empleados administrativos',170.98,'2020-09-27','2020-09-27','2020-09-21',10,21,8,5,1),
(19,'Viaje a residencias de empleados',500.99,'2020-09-28','2020-09-28','2020-09-21',6,21,8,5,1),
(20,'Viaje a residencias de empleados',500.00,'2020-10-01','2020-10-01','2020-09-21',6,21,8,5,1),
(21,'Viaje a residencias de empleados',500.00,'2020-09-29','2020-09-29','2020-09-21',11,21,8,5,1),
(22,'Viaje a residencias de empleados',500.99,'2020-10-02','2020-10-02','2020-09-21',13,21,8,1,1)


insert into DetalleRecurso values 
(1,1,3),
(2,2,3),
(3,3,1),
(4,4,1)


insert into Servicio values 
(1, '123456789', 'Servicios de transporte al aeropuerto', 'Contamos con la mejor infraestructura del país para recibir a pasajeros en El Aeropuerto Internacional de El Salvador','20/07/2020',1,3),
(2, '852134678', 'Servicios de transporte para ejecutivos','Contamos con una flota bastante completa de unidades, dependiendo del tipo de servicio que el cliente solicite desde sedanes super lujosos hasta buses','20/07/2020',1,3),
(3, '741236952', 'Servicios de transporte para empleados', 'Este servicio es exclusivo para empleados de instituciones o empresas, que contraten por meses este servicio','20/07/2020',1,3),
(4, '699769669', 'Servicios de transporte para bodas', 'Ofrecemos el mejor servicio para su boda, en unidades sedan, micbrobuses etc, para brindar el traslado a Iglesia/Hotel etc.','20/07/2020',1,3),
(5, '609069881', 'Servicios de transporte turismo y tours', 'Contamos con unidades preparadas especialmente para la rama del turismo, tomando en cuenta tanto la seguridad de nuestros pasajeros como el confort de los mismos','20/07/2020',1,3),
(6,'43544544','Servicio de transporte a Royal Decameron Salinitas','Contamos con unidades preparadas para el transporte a lugares recreativos y vacacionales','08/09/2020',2,2),
(7,'45454555','Servicio de transporte a Sitios Arqueologicos','Contamos con una flota de unidades lista para brindar comodidad y confort al cliente','08/09/2020',1,1),
(8,'43534544','Servicio de transporte a personal','Este servicio es exclusivo para empleados de clientes empresariales','08/09/2020',4,4),
(9,'56456555','Servicio de transporte a ruta de las flores','Contamos con una flota preparada para viajes largos, pero con nosotros solo enfoquese en disfrutar','08/09/2020',5,2),
(10,'64546365','Servicio de transporte al mozote y museo historico','Contamos con una logistica preparada para viajes largos pero centrados en la comodidad del cliente','08/09/2020',1,5),
(11,'74565456','Servicio de transporte a lago de coatepeque','flota preparada para viajes a lugares turisticos','08/09/2020',3,1),
(12,'45354554','Servicio de transporte a cerro verde','flota preparada','08/09/2020',3,1),
(13,'23232333','Servicio de transporte para empleados de campo','flota lista para transportar a empleados que hacen trabajo de campo en casi cualquier area','08/09/2020',5,4),
(14,'45453445','Servicio de transporte para empleados de medio turno','flota de unidades lista para llevar al domicilio del empleado','08/09/2020',4,2),
(15,'95676656','Servicio de transporte para un día','Contamos con unidades listas para hacer viajes de un día al lugar que requiera el cliente','08/09/2020',4,3)


insert into Contrato values 
(1,1.5,134.43,'03/06/2020','04/09/2020','20/07/2020',4,2,2),
(2,2.5,1647.66,'04/04/2020','09/09/2020','20/07/2020',3,6,3),
(3,3.4,55.99,'02/06/2020','15/05/2020','20/07/2020',5,12,4), 
(4,1.4,125.89,'04/03/2020','05/04/2020','20/07/2020',1,5,1),
(5,1.5,133.56,'09/02/2020','07/02/2020','20/07/2020',2,1,1),
(6,1.5,133.56,'09/02/2020','07/02/2020','20/07/2020',2,4,1),
(7,1.5,133.56,'09/10/2020','09/10/2020','20/07/2020',2,13,1)
-------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------- No Ejecutar --------------------------------------------------------------------------
select * from TipoCliente
select * from EstadoCliente
Select * from Cliente 
select * from Contrato
Select * From Persona
Select * from Viaje
Select * From Unidad
Select * from Recurso 
Select * from Servicio
Select * from DetalleRecurso
Select * from Departamento
Select * from Marca
Select idDepartamento, departamento from Departamento Order by idDepartamento
select * from DetalleRecurso
select * from TipoPersona
Select * from Recurso
Select * from Factura
Select * from Bitacora

select idPersona, nombres, apellidos, fechaNacimiento, direccion, pr.telefono, dui, correo, usuario, clave, nSeguro, pr.fechaRegistro, codigoConfirmacion, respuesta1, respuesta2, pregunta1, estadoPersonal, tipoPersonal, nombre
from Persona pr, Pregunta pe, EstadoPersona ep,TipoPersona tp,  Empresa em
where pr.idPregunta=pe.idPregunta
and pr.idEstadoPersonal=ep.idEstadoPersonal
and pr.idTipoPersonal=tp.idTipoPersonal
and pr.idEmpresa =em.idEmpresa

select idPersona, nombres, apellidos, fechaNacimiento, direccion, pr.telefono, dui, correo, usuario, clave, nSeguro, pr.fechaRegistro, codigoConfirmacion, respuesta1, respuesta2, pregunta1, estadoPersonal, tipoPersonal, nombre
from Persona pr, Pregunta pe, EstadoPersona ep,TipoPersona tp,  Empresa em
where pr.idPregunta2=pe.idPregunta
and pr.idEstadoPersonal=ep.idEstadoPersonal
and pr.idTipoPersonal=tp.idTipoPersonal
and pr.idEmpresa =em.idEmpresa



Select vj.direccion, precio, horaSalida, horaRetorno, nombres , modelo, nombre, departamento , tipoViaje 
from Viaje vj, Persona ps, Unidad un, Cliente cl, Departamento dr,TipoViaje tv
where vj.idPersona=ps.idPersona
and vj.idUnidad=un.idUnidad
and vj.idCliente=cl.idCliente
and vj.idDepartamento=dr.idDepartamento
and vj.idTipoViaje=tv.idTipoViaje


Select intereses, monto, fecha_inicio, fecha_fin, nombreServicio, nombre, tipoPago 
From Contrato cr, Servicio sc, Cliente cl, TipoPago tp
where cr.idServicio=sc.idServicio
and cr.idCliente=cl.idCliente
and cr.idPago=tp.idPago


Select * from EstadoPersona
Select * from TipoPersona
Select * from Pregunta
--------------------------------------------
delete  from TipoServicio
delete  from TipoUnidad 
delete  from EstadoUnidad 
delete  from Marca
delete  from Recurso
delete  from EstadoServicio 
delete  from TipoCliente
delete  from EstadoCliente
delete  from TipoPago
delete  from TipoFactura
delete  from TipoFactura
delete  from TipoViaje
delete  from Departamento
delete from DetalleRecurso
Delete from Unidad
delete from Cliente
delete from Viaje
delete from Servicio
delete from Contrato
delete from Viaje Where idViaje=24
delete from Factura

delete from Cliente Where idCliente=34
----------------------------------------------
Select * from Viaje


Select sum(precio),  nombre
From  Viaje vj, Cliente cl 
Where vj.idCliente=cl.idCliente 
and nombre='Napoleón Alexander'
Group by  nombre

Select precio, vj.direccion from Viaje vj ,Cliente cl Where  vj.idCliente=cl.idCliente and cl.nombre='Napoleón Alexander'

Select idContrato, fecha_inicio, fecha_fin, cr.fechaRegistro, nombreServicio, nombre, tipoPago 
from Contrato cr, Servicio sr, Cliente cl, TipoPago tp
Where cr.idServicio=sr.idServicio 
And cr.idCliente=cl.idCliente  
and cr.idPago=tp.idPago
and cr.monto>0.0



Select * from Contrato
Select * from Servicio
update Servicio set idEstadoServicio=1 Where nombreServicio='Servicios de transporte para bodas'
Update Contrato set monto=100.00 Where idContrato=1

Select monto from Contrato cr, Cliente cl  Where cr.idCliente=cl.idCliente and cl.nombre='Napoleón Alexander'


Select vj.direccion ,precio, monto, nombre 
From Contrato cr, Cliente cl , Viaje vj
Where vj.idCliente=cl.idCliente
and cr.idCliente=cl.idCliente
and cl.nombre='Napoleón Alexander'
Group By monto, nombre 

SELECT idServicio, codigo, nombreServicio, descripcion, fechaRegistro, tipoServicio, estadoServicio
from Servicio s, TipoServicio ts, EstadoServicio es 
WHERE s.idTipoServicio = ts.idTipoServicio 
and s.idEstadoServicio = es.idEstadoServicio 
and ts.idTipoServicio=1

Select idTipoServicio from TipoServicio where tipoServicio='5 estrellas'
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Select idPersona, nombres, apellidos, fechaNacimiento, direccion, ps.telefono, dui, correo, usuario, nSeguro, licencia, estadoPersonal, tipoPersonal, nombre 
From Persona ps, EstadoPersona ep, TipoPersona tp, Licencia li, Empresa em 
Where ps.idEstadoPersonal=ep.idEstadoPersonal 
and ps.idTipoPersonal=tp.idTipoPersonal 
and ps.idLicencia=li.idLicencia
and ps.idEmpresa=em.idEmpresa 

Select idPersona, nombres, apellidos, fechaNacimiento, direccion,  dui, correo, usuario, nSeguro, licencia ,estadoPersonal, tipoPersonal, nombre From Persona pr, Licencia lc, EstadoPersona ep, TipoPersona te,  Empresa es Where  pr.idLicencia=lc.idLicencia and pr.idEstadoPersonal = ep.idEstadoPersonal and pr.idTipoPersonal=te.idTipoPersonal and pr.idEmpresa=es.idEmpresa Order by idPersona
update Persona set idLicencia=1 Where idPersona=3

select *  from Persona
------------------------------------------------------------------------------------------------------------------------------------------

Select nombre, apellido, representanteLegal, telefono, direccion, nit, tipoCliente, estadoCliente
from Cliente cl, TipoCliente tc , EstadoCliente ec
Where cl.idTipoCliente=tc.idTipoCliente
and cl.idEstadoCliente=ec.idEstadoCliente
Order by estadoCliente

------------------------------------------------------Triggers-----------------------------------------------------------------------------


create trigger insertarViaje
on  Viaje
after insert 
as
begin 
declare @fecha datetime 
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'Insercion a la tabla ')
end 



select nombres from persona Where idEstadopersonal=1

update persona set idEstadoPersonal = 1 Where nombres='Adalberto Francisco'
update persona set idEstadoPersonal =2 

select * from Bitacora


create  or alter trigger modificarViaje
on Viaje 
after update 
as 
begin 
declare @fecha datetime, @usuario varchar 
set @usuario=(select nombres from persona Where idEstadopersonal=1 )
set @fecha=(select getdate()); 
insert into Bitacora (persona, fecha, accion) values((select nombres from persona Where idEstadopersonal=1),@fecha,'La tabla ha sido actualizada')
end 


create trigger eliminarViaje 
on Viaje
after delete 
as 
begin 
declare @fecha datetime 
set @fecha=(select GETDATE());
insert into Bitacora values('Administrador',@fecha,'Se ha eliminado un elemento de la tabla ')
end 



create trigger insertarPersona
on  Persona
after insert 
as
begin 
declare @fecha datetime 
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'Insercion a la tabla ')
end 


create trigger modificarPersona
on Persona
after update 
as 
begin 
declare @fecha datetime  
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'La tabla ha sido actualizada')
end 


create trigger eliminarPersona 
on Persona
after delete 
as 
begin 
declare @fecha datetime 
set @fecha=(select GETDATE());
insert into Bitacora values('Administrador',@fecha,'Se ha eliminado un elemento de la tabla ')
end   



create trigger insertarCliente
on  Cliente
after insert 
as
begin 
declare @fecha datetime 
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'Insercion a la tabla ')
end 



create trigger modificarCliente
on Cliente
after update 
as 
begin 
declare @fecha datetime  
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'La tabla ha sido actualizada')
end 


create trigger eliminarCliente 
on Cliente
after delete 
as 
begin 
declare @fecha datetime 
set @fecha=(select GETDATE());
insert into Bitacora values('Administrador',@fecha,'Se ha eliminado un elemento de la tabla ')
end 



create trigger insertarContrato
on Contrato 
after insert 
as
begin 
declare @fecha datetime 
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'Insercion a la tabla ')
end 



create trigger modificarContrato
on Contrato
after update 
as 
begin 
declare @fecha datetime  
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'La tabla ha sido actualizada')
end 


create trigger eliminarContrato 
on Contrato
after delete 
as 
begin 
declare @fecha datetime 
set @fecha=(select GETDATE());
insert into Bitacora values('Administrador',@fecha,'Se ha eliminado un elemento de la tabla ')
end   


create trigger insertarUnidad
on  Unidad
after insert 
as
begin 
declare @fecha datetime 
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'Insercion a la tabla ')
end 



create trigger modificarUnidad
on Unidad
after update 
as 
begin 
declare @fecha datetime  
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'La tabla ha sido actualizada')
end 


create trigger eliminarUnidad 
on Unidad
after delete 
as 
begin 
declare @fecha datetime 
set @fecha=(select GETDATE());
insert into Bitacora values('Administrador',@fecha,'Se ha eliminado un elemento de la tabla ')
end 


create trigger insertarServicio
on  Servicio
after insert 
as
begin 
declare @fecha datetime 
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'Insercion a la tabla ')
end 



create trigger modificarServicio
on Servicio
after update 
as 
begin 
declare @fecha datetime  
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'La tabla ha sido actualizada')
end 


create trigger eliminarServicio 
on Servicio
after delete 
as 
begin 
declare @fecha datetime 
set @fecha=(select GETDATE());
insert into Bitacora values('Administrador',@fecha,'Se ha eliminado un elemento de la tabla ')
end 


create trigger insertarFactura
on Factura
after insert 
as
begin 
declare @fecha datetime 
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'Insercion a la tabla ')
end 



create trigger modificarFactura
on Factura
after update 
as 
begin 
declare @fecha datetime  
set @fecha=(select getdate()); 
insert into Bitacora values('Administrador',@fecha,'La tabla ha sido actualizada')
end 


create trigger eliminarFactura 
on Factura
after delete 
as 
begin 
declare @fecha datetime 
set @fecha=(select GETDATE());
insert into Bitacora values('Administrador',@fecha,'Se ha eliminado un elemento de la tabla ')
end 



