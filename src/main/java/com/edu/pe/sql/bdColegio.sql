DROP DATABASE IF EXISTS bd_Colegio;

CREATE DATABASE bd_Colegio;

USE bd_Colegio;

CREATE TABLE Grado(
	id_grado INT AUTO_INCREMENT PRIMARY KEY, 
    nivel VARCHAR(50),
    grado int
);

CREATE TABLE Tipo_Usuario(
	id_tipo INT AUTO_INCREMENT PRIMARY KEY, 
    usuario VARCHAR(150)
);

CREATE TABLE Usuario(
	id_usuario INT AUTO_INCREMENT PRIMARY KEY, 
    id_tipo INT,
    username VARCHAR(60) UNIQUE,
    pass VARCHAR(50),
    correo VARCHAR(50),
    estado BOOLEAN,
    FOREIGN KEY(id_tipo) REFERENCES Tipo_Usuario(id_tipo)
);

CREATE TABLE Estudiante(
	id_estudiante INT AUTO_INCREMENT PRIMARY KEY, 
    id_usuario INT,
    id_grado INT,
    nombres VARCHAR(70),
    apellidos VARCHAR(70),
    genero CHAR(1),
    telefono char(9) NULL,
    fecha_nacimiento date,
	FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY(id_grado) REFERENCES Grado(id_grado)
);

CREATE TABLE Profesor(
	id_profesor INT AUTO_INCREMENT PRIMARY KEY, 
    id_usuario INT,
    nombres VARCHAR(70),
    apellidos VARCHAR(70),
    genero CHAR(1),
    telefono char(9) NULL,
    fecha_nacimiento date,
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Curso(
	id_curso INT AUTO_INCREMENT PRIMARY KEY, 
    nombre_curso VARCHAR(60)
);

CREATE TABLE Periodo(
	id_periodo INT AUTO_INCREMENT PRIMARY KEY, 
    nombre_periodo VARCHAR(50) UNIQUE,
    fecha_inicio DATE,
    fecha_fin DATE,
    cantidad_semanas int
);

CREATE TABLE Seccion(
	nro_seccion INT AUTO_INCREMENT PRIMARY KEY, 
    id_profesor INT ,
    id_curso INT,
    id_periodo INT,
    FOREIGN KEY(id_profesor) REFERENCES Profesor(id_profesor),
    FOREIGN KEY(id_curso) REFERENCES Curso(id_curso),
    FOREIGN KEY(id_periodo) REFERENCES Periodo(id_periodo)
)AUTO_INCREMENT = 10000;

CREATE TABLE Inscripcion(
	id_inscripcion INT AUTO_INCREMENT PRIMARY KEY, 
    nro_seccion INT,
    id_estudiante INT,
    FOREIGN KEY(nro_seccion) REFERENCES Seccion(nro_seccion),
    FOREIGN KEY(id_estudiante) REFERENCES Estudiante(id_estudiante)
);

CREATE TABLE Semana_Academica(
	nro_semana INT AUTO_INCREMENT PRIMARY KEY, 
    semana VARCHAR(50)
);

CREATE TABLE Contenido(
	id_contenido INT AUTO_INCREMENT PRIMARY KEY, 
    nro_semana INT,
    nro_seccion INT,
    archivo VARCHAR(200),
    FOREIGN KEY(nro_semana) REFERENCES Semana_Academica(nro_semana) ON DELETE CASCADE,
    FOREIGN KEY(nro_seccion) REFERENCES Seccion(nro_seccion) ON DELETE CASCADE
);

CREATE TABLE Foro(
	id_foro INT AUTO_INCREMENT PRIMARY KEY, 
	nro_semana INT,
    nro_seccion INT,
    titulo VARCHAR(60),
    fechaInicio DATE,
    fechaCierre DATE,
    descripcion VARCHAR(2000),
    FOREIGN KEY(nro_semana) REFERENCES Semana_Academica(nro_semana)  ON DELETE CASCADE,
    FOREIGN KEY(nro_seccion) REFERENCES Seccion(nro_seccion)  ON DELETE CASCADE
);

CREATE TABLE Comentario(
	id_comentario INT AUTO_INCREMENT PRIMARY KEY, 
    id_foro INT,
    id_usuario INT,
	desc_comentario VARCHAR(2000),
    FOREIGN KEY(id_foro) REFERENCES Foro(id_foro) ON DELETE CASCADE,
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario) ON DELETE CASCADE
);

DELIMITER @@
DROP PROCEDURE IF EXISTS sp_listarComentarios @@
CREATE PROCEDURE sp_listarComentarios(
_id_foro int
)  
BEGIN
    
	select obtener_datos(id_usuario) , desc_comentario
    from comentario
    where id_foro = _id_foro;
END @@
DELIMITER ;

DELIMITER @@
DROP FUNCTION IF EXISTS obtener_datos @@
CREATE FUNCTION obtener_datos(_id_usuario int) 
RETURNS VARCHAR(80)
BEGIN
    DECLARE _DATOS VARCHAR(80);
    
    IF EXISTS (SELECT * FROM PROFESOR WHERE ID_USUARIO = _id_usuario) THEN
        SELECT CONCAT(NOMBRES ,' ' ,APELLIDOS) INTO _DATOS FROM PROFESOR WHERE ID_USUARIO = _id_usuario;
    ELSE
		SELECT CONCAT(NOMBRES ,' ' ,APELLIDOS) INTO _DATOS FROM ESTUDIANTE WHERE ID_USUARIO = _id_usuario;
    END IF;

    RETURN _DATOS;
END @@
DELIMITER ;


INSERT INTO Curso(id_curso , nombre_curso) VALUES(1 , 'Matematica Basica I');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(2 , 'Matematica Basica II');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(3 , 'Matematica Basica III');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(4 , 'Ingles I');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(5 , 'Ingles II');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(6 , 'Ingles III');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(7 , 'Ingles IV');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(8 , 'Comunicacion de Textos I');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(9 , 'Comunicacion de Textos II');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(10 , 'Quimica General');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(11 , 'Historia del Peru');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(12 , 'Religion');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(13 , 'Historia Universal');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(14 , 'Arte');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(15 , 'Ciencias Sociales');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(16 , 'Educacion Civica');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(17 , 'Informatica');
INSERT INTO Curso(id_curso , nombre_curso) VALUES(18 , 'Musica');

INSERT INTO Tipo_Usuario(id_tipo,usuario) VALUES(1 , 'Docente');
INSERT INTO Tipo_Usuario(id_tipo,usuario) VALUES(2 , 'Estudiante');

INSERT INTO Grado(id_grado,nivel,grado) VALUES(1 , 'Primaria' , 1);
INSERT INTO Grado(id_grado,nivel,grado) VALUES(2 , 'Primaria' , 2);
INSERT INTO Grado(id_grado,nivel,grado) VALUES(3 , 'Primaria' , 3);
INSERT INTO Grado(id_grado,nivel,grado) VALUES(4 , 'Primaria' , 4);
INSERT INTO Grado(id_grado,nivel,grado) VALUES(5 , 'Primaria' , 5);
INSERT INTO Grado(id_grado,nivel,grado) VALUES(6 , 'Primaria' , 6);

INSERT INTO Grado(id_grado,nivel,grado) VALUES(7 , 'Secundaria' , 1);
INSERT INTO Grado(id_grado,nivel,grado) VALUES(8 , 'Secundaria' , 2);
INSERT INTO Grado(id_grado,nivel,grado) VALUES(9 , 'Secundaria' , 3);
INSERT INTO Grado(id_grado,nivel,grado) VALUES(10 , 'Secundaria' , 4);
INSERT INTO Grado(id_grado,nivel,grado) VALUES(11 , 'Secundaria' , 5);

INSERT INTO Periodo(id_periodo,nombre_periodo,fecha_inicio,fecha_fin,cantidad_semanas)VALUES(1 , '2021-I' , '2021-03-22' , '2021-07-18' , 18);

-- ESTUDIANTE

INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(1 , 2 ,'U17206135', '{noop}123456', 'U17206135@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(2 , 2 ,'U18301301', '{noop}123456', 'U18301301@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(3 , 2 ,'1331038', '{noop}123456', '1331038@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(4 , 2 ,'1522534', '{noop}123456', '1522534@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(5 , 2 ,'U17303098', '{noop}123456', 'U17303098@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(6 , 2 ,'U19209684', '{noop}123456', 'U19209684@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(7 , 2 ,'U19221050', '{noop}123456', 'U19221050@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(8 , 2 ,'U17209055', '{noop}123456', 'U17209055@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(9 , 2 ,'1621474', '{noop}123456', '1621474@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(10 , 2 ,'U17105191', '{noop}123456', 'U17105191@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(11 , 2 ,'U19209675', '{noop}123456', 'U19209675@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(12 , 2 ,'U19208815', '{noop}123456', 'U19208815@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(13 , 2 ,'U18311389', '{noop}123456', 'U18311389@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(14 , 2 ,'U18311381', '{noop}123456', 'U18311381@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(15 , 2 ,'U18101111', '{noop}123456', 'U18101111@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(16 , 2 ,'1432342', '{noop}123456', '1432342@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(17 , 2 ,'1634323', '{noop}123456', '1634323@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(18 , 2 ,'1623342', '{noop}123456', '1623342@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(19 , 2 ,'1542365', '{noop}123456', '1542365@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(20 , 2 ,'1632354', '{noop}123456', '1632354@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(21 , 2 ,'1521244', '{noop}123456', '1521244@utp.edu.pe' , 1);

-- PROFESOR
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(22 , 1 ,'C07100', '{noop}123456', 'C07100@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(23 , 1 ,'C17120', '{noop}123456', 'C17120@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(24 , 1 ,'C10801', '{noop}123456', 'C10801@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(25 , 1 ,'C10151', '{noop}123456', 'C10151@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(26 , 1 ,'C11256', '{noop}123456', 'C11256@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(27 , 1 ,'C10201', '{noop}123456', 'C10201@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(28 , 1 ,'C11533', '{noop}123456', 'C11533@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(29 , 1 ,'C00302', '{noop}123456', 'C00302@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(30 , 1 ,'C17530', '{noop}123456', 'C17530@utp.edu.pe' , 1);
INSERT INTO Usuario(id_usuario,id_tipo,username,pass,correo,estado) VALUES(31 , 1 ,'C12541', '{noop}123456', 'C12541@utp.edu.pe' , 1);

INSERT INTO Estudiante(id_estudiante,id_usuario,id_grado,nombres,apellidos,genero,telefono,fecha_nacimiento) VALUES
(1 , 1 ,2, 'GianFranco Mauricio','García Becerra','M','997595599' , '2001-11-14') ,
(2 , 2 ,2, 'Yelibeth Chirinos','Chirinos Sánchez','F','976783411' , '2000-12-24') ,
(3 , 3 ,3, 'Victor Alexander','Villegas Fernández','M','977976577' , '2000-12-24'),
(4 , 4 ,4, 'Armando','López Chong','M','967660609' , '2000-12-24'),
(5 , 5 ,1, 'Jenifer Maybe','Montenegro Camarena','F','978555585' , '2000-12-24'),
(6 , 6 ,2, 'Marta','Noriega Vásquez','F','946746434' , '2000-12-24'),
(7 , 7 ,2, 'Milagros Luciana','Rodriguez Guzman','F','975654234' , '2000-12-24'),
(8 , 8 ,2, 'Shirley Luciana','Castillo Velasquez','F','987453233' , '2000-12-24'),
(9 , 9 ,2, 'Aldo Simon','Escalante Rodriguez','M','946233354' , '2000-12-24'),
(10 , 10,2 , 'Daisy','Fernandez Baca','F','999874111' , '2000-12-24'),
(11 , 11,3, 'Britney Danuska','Pilar Huamani','F','987452324' , '2000-12-24'),
(12 , 12,4, 'Stephanie','Del Pilar Quispe','F','964243434' , '2000-12-24'),
(13 , 13,2, 'Juan Carlos','Aguilar Puente','M','912354777' , '2000-12-24'),
(14 , 14,2, 'Enrique','Guzman Cardenas','M','987456234' , '2000-12-24'),
(15 , 15,2, 'Ingrid Sugey','Velasquez Chavez','F','987342111' , '2000-12-24'),
(16 , 16,2, 'Dayana','Sullca Salazar','F','999875678' , '2000-12-24'),
(17 , 17,2, 'Carlos','Bardales Carbajal','M','987888342' , '2000-12-24'),
(18 , 18,2, 'Adely','Sotomayor Lopez','F','9123222643' , '2000-12-24'),
(19 , 19,2, 'Alan','Paucar Caceres','M','999123112' , '2000-12-24'),
(20 , 20,2, 'Luis Alonzo','Dextre Sanchez','M','987666231' , '2000-12-24'),
(21 , 21,2, 'Luis Alberto','Huaman Vera','M','999345111' , '2000-12-24');

INSERT INTO Profesor(id_profesor,id_usuario,nombres,apellidos,genero,telefono,fecha_nacimiento) VALUES
(1 , 22 , 'Luis','Palacios Sanchez','M','912423111' , '1992-01-17'),
(2 , 23 , 'Carlos Humberto','Mori Moscoso','M','900087123' , '1992-01-17'),
(3 , 24 , 'Francisco','Mori Quiroz','M','912435123' , '1992-01-17'),
(4 , 25 , 'Luis','Palacios Sanchez','M','912423111' , '1992-01-17'),
(5 , 26 , 'Erick Ramon','Nuñez Tarillo','M','934462675' , '1992-01-17'),
(6 , 27 , 'Carmen Rosa','Olivera Delgado','F','994721222' , '1992-01-17'),
(7 , 28 , 'Silvana Rocio','Pacora Bazalar','F','934352223' , '1992-01-17'),
(8 , 29 , 'Manuel Jesus','Palomino Berrios','M','945112121' , '1992-01-17'),
(9 , 30 , 'Alberto','Pereyra Parra','M','993564656' , '1992-01-17'),
(10 , 31 , 'Fernando Jose','Pozo Gonzales','M','999345111' , '1992-01-17');


INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10000,1,1,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10001,1,2,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10002,1,3,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10003,1,4,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10004,2,4,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10005,2,5,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10006,2,6,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10007,3,8,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10008,3,9,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10009,4,11,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10010,5,12,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10011,5,14,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10012,6,15,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10013,7,16,1);
INSERT INTO Seccion(nro_seccion,id_profesor,id_curso,id_periodo) VALUES(10014,8,17,1);


INSERT INTO Semana_Academica(nro_semana,semana)VALUES(1,'Semana 01');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(2,'Semana 02');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(3,'Semana 03');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(4,'Semana 04');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(5,'Semana 05');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(6,'Semana 06');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(7,'Semana 07');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(8,'Semana 08');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(9,'Semana 09');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(10,'Semana 10');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(11,'Semana 11');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(12,'Semana 12');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(13,'Semana 13');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(14,'Semana 14');
INSERT INTO Semana_Academica(nro_semana,semana)VALUES(15,'Semana 15');



INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(1 ,10000,1);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(2 ,10000,2);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(3 ,10000,3);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(4 ,10000,4);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(5 ,10000,5);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(6 ,10000,6);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(7 ,10000,7);


INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(8 ,10003,1);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(9 ,10003,3);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(10 ,10003,4);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(11 ,10003,5);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(12 ,10003,8);

INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(13 ,10007,1);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(14 ,10007,2);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(15 ,10007,7);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(16 ,10007,8);
INSERT INTO INSCRIPCION(ID_INSCRIPCION,NRO_SECCION,ID_ESTUDIANTE)VALUES(17 ,10007,9);

SELECT * FROM Seccion;

select * from Usuario;
select * from profesor;
SELECT * FROM Estudiante;
SELECT * FROM PERIODO;
select * from curso;
select * from seccion;
select * from inscripcion;


INSERT INTO CONTENIDO VALUES(1 , 1 , 10000 , 'AnguloSuplementarios.jpg');
INSERT INTO CONTENIDO VALUES(2 , 2 , 10000 , 'Ejercicios-de-Método-de-Deducción-e-Inducción.doc');

