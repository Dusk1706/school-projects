--Creacion de Base de datos
CREATE DATABASE HospitalDB
GO
USE HospitalDB

GO

--Creacion de tablas
CREATE TABLE ZONAS (
    ZONAID INT NOT NULL,
    ZONANOMBRE NVARCHAR(50) NOT NULL
)
GO
CREATE TABLE HOSPITALES (
    HOSPID INT NOT NULL,
    HOSPNOMBRE NVARCHAR(100) NOT NULL,
    ZONAID INT NOT NULL
)
GO
CREATE TABLE CONSULTORIOS (
    CONID INT NOT NULL,
    CONNOMBRE NVARCHAR(100) NOT NULL,
    HOSPID INT NOT NULL,
)
GO
CREATE TABLE CITAS (
    CITID INT NOT NULL,
    CITFECHA DATE NOT NULL,
    CITPESO NUMERIC(10,2) NOT NULL,
    CITESTATURA NUMERIC(5,2) NOT NULL,
    CITPRESION INT NOT NULL,
    CITOBSERVACIONES NVARCHAR(150) NOT NULL,
    CONID INT NOT NULL
)

GO

--Primary Keys
ALTER TABLE ZONAS ADD CONSTRAINT PK_ZONAS PRIMARY KEY (ZONAID)
ALTER TABLE HOSPITALES ADD CONSTRAINT PK_HOSPITALES PRIMARY KEY (HOSPID)
ALTER TABLE CONSULTORIOS ADD CONSTRAINT PK_CONSULTORIOS PRIMARY KEY (CONID)
ALTER TABLE CITAS ADD CONSTRAINT PK_CITAS PRIMARY KEY (CITID)

GO

--Foreign Keys
ALTER TABLE HOSPITALES ADD CONSTRAINT FK_HOSPITALES_ZONAS FOREIGN KEY (ZONAID) REFERENCES ZONAS(ZONAID)
ALTER TABLE CONSULTORIOS ADD CONSTRAINT FK_CONSULTORIOS_HOSPITALES FOREIGN KEY (HOSPID) REFERENCES HOSPITALES(HOSPID)
ALTER TABLE CITAS ADD CONSTRAINT FK_CITAS_CONSULTORIOS FOREIGN KEY (CONID) REFERENCES CONSULTORIOS(CONID)
GO
--Inserciones Zonas
INSERT INTO ZONAS VALUES (1,'CENTRO'),(2,'NORTE'),(3,'SUR')

GO
--Insercciones Hospital
INSERT INTO HOSPITALES VALUES(1, 'Los Angeles', 1)
INSERT INTO HOSPITALES VALUES(2, 'IMSS', 2)
INSERT INTO HOSPITALES VALUES(3, 'General', 3)
INSERT INTO HOSPITALES VALUES(4, 'Angel Flores', 1)
INSERT INTO HOSPITALES VALUES(5, 'Cañadas', 2)

GO
--Insercciones Consultorios
INSERT INTO CONSULTORIOS VALUES(1, 'Urgencias', 1)
INSERT INTO CONSULTORIOS VALUES(2, 'Medico General', 2)
INSERT INTO CONSULTORIOS VALUES(3, 'Dermatologia', 3)
INSERT INTO CONSULTORIOS VALUES(4, 'Odontologia', 4)
INSERT INTO CONSULTORIOS VALUES(5, 'Medicina Familiar', 5)

GO

--Inserciones Citas
INSERT INTO CITAS VALUES(1, '10-15-2023', 79.50, 1.82, 87, 'EL PACIENTE SUFRE DE UN DERRAME CEREBRAL', 1)
INSERT INTO CITAS VALUES(2, '11-23-2024', 70.40, 1.70, 98, 'Gripe Comun', 2)
INSERT INTO CITAS VALUES(3, '5-12-2020', 87.80, 1.90, 56, 'Acne grado 3', 3)
INSERT INTO CITAS VALUES(4, '9-2-2021', 91.20, 1.65, 84, 'Operacion de muela del juicio', 4)
INSERT INTO CITAS VALUES(5, '5-30-2022', 67.20, 1.90, 80, 'Antidepresivos', 5)

GO
-- Vista VW_HOSPITALES
CREATE VIEW VW_HOSPITALES AS
SELECT
	H.HOSPID, H.HOSPNOMBRE,
	Z.*
FROM HOSPITALES H
INNER JOIN ZONAS Z ON H.ZONAID = Z.ZONAID
GO

-- Vista VW_CONSULTORIOS
CREATE VIEW VW_CONSULTORIOS AS
SELECT
	C.CONID, C.CONNOMBRE,
	H.*
FROM CONSULTORIOS C
INNER JOIN VW_HOSPITALES H ON C.HOSPID = H.HOSPID
GO

-- Vista VW_CITAS
CREATE VIEW VW_CITAS AS
SELECT
	CI.CITID, CI.CITFECHA, CI.CITPESO, CI.CITESTATURA,
	CI.CITPRESION, CI.CITOBSERVACIONES,
	C.*
FROM CITAS CI
INNER JOIN VW_CONSULTORIOS C ON CI.CONID = C.CONID
GO

--Consultas
--1.- NOMBRE DE LA ZONA Y TOTAL DE HOSPITALES DE LA ZONA.
SELECT
	Z.ZONANOMBRE, 
	ISNULL(COUNT(H.HOSPID),0) AS 'TotalHospitales'
FROM ZONAS Z
LEFT JOIN VW_Hospitales H ON H.ZONAID=Z.ZONAID
GROUP BY Z.ZONANOMBRE

GO

--2.- NOMBRE DEL CONSULTORIO Y TOTAL DE CITAS REALIZADAS.
SELECT
	C.CONNOMBRE, 
	ISNULL(COUNT(CI.CITID),0) AS 'TotalCitas'
FROM CONSULTORIOS C
LEFT JOIN VW_CITAS CI ON C.CONID=CI.CONID
GROUP BY C.CONNOMBRE

GO

--3.- AÑO Y TOTAL DE CITAS REALIZADAS.
SELECT
	YEAR(CITFECHA) AS 'Año',
	COUNT(CITID) AS 'TotalCitas'
FROM VW_CITAS
GROUP BY YEAR(CITFECHA)

GO

--4.- MES Y TOTAL DE CITAS REALIZADAS. MOSTRAR TODOS LOS MESES, SI NO TIENE CITAS, MOSTAR EN CERO.
SELECT
	'ENERO' = COUNT(CASE WHEN MONTH(CITFECHA) = 1 THEN CITID END),
	'FEBRERO' = COUNT(CASE WHEN MONTH(CITFECHA) = 2 THEN CITID END),
	'MARZO' = COUNT(CASE WHEN MONTH(CITFECHA) = 3 THEN CITID END),
	'ABRIL' = COUNT(CASE WHEN MONTH(CITFECHA) = 4 THEN CITID END),
	'MAYO' = COUNT(CASE WHEN MONTH(CITFECHA) = 5 THEN CITID END),
	'JUNIO' = COUNT(CASE WHEN MONTH(CITFECHA) = 6 THEN CITID END),
	'JULIO' = COUNT(CASE WHEN MONTH(CITFECHA) = 7 THEN CITID END),
	'AGOSTO' = COUNT(CASE WHEN MONTH(CITFECHA) = 8 THEN CITID END),
	'SEPTIEMBRE' = COUNT(CASE WHEN MONTH(CITFECHA) = 9 THEN CITID END),
	'OCTUBRE' = COUNT(CASE WHEN MONTH(CITFECHA) = 10 THEN CITID END),
	'NOVIEMBRE' = COUNT(CASE WHEN MONTH(CITFECHA) = 11 THEN CITID END),
	'DICIEMBRE' = COUNT(CASE WHEN MONTH(CITFECHA) = 12 THEN CITID END)
FROM VW_CITAS

GO

--5.- NOMBRE DEL HOSPITAL Y TOTAL DE CONSULTORIOS QUE CONTIENE.
SELECT
	H.HOSPNOMBRE,
	ISNULL(COUNT(C.CONID),0) AS 'TotalConsultorios'
FROM HOSPITALES H
LEFT JOIN VW_CONSULTORIOS C ON H.HOSPID=C.HOSPID
GROUP BY H.HOSPNOMBRE

GO

--6.- NOMBRE DEL CONSULTORIO Y PESO TOTAL DE LOS PACIENTES ATENDIDOS EN LAS CITAS.
SELECT
	C.CONNOMBRE AS 'NombreConsultorio',
	ISNULL(SUM(CI.CITPESO),0) AS 'PesoTotal'
FROM CONSULTORIOS C
LEFT JOIN VW_CITAS CI ON C.CONID=CI.CONID 
GROUP BY C.CONNOMBRE

GO

--7.- NOMBRE DEL HOSPITAL Y TOTAL DE CITAS REALIZADAS POR MES DEL AÑO 2020
SELECT
	H.HOSPNOMBRE AS 'NombreHospital',
	'ENERO'=COUNT(CASE WHEN MONTH(CITFECHA)=1 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'FEBRERO'=COUNT(CASE WHEN MONTH(CITFECHA)=2 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'MARZO'=COUNT(CASE WHEN MONTH(CITFECHA)=3 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'ABRIL'=COUNT(CASE WHEN MONTH(CITFECHA)=4 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'MAYO'=COUNT(CASE WHEN MONTH(CITFECHA)=5 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'JUNIO'=COUNT(CASE WHEN MONTH(CITFECHA)=6 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'JULIO'=COUNT(CASE WHEN MONTH(CITFECHA)=7 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'AGOSTO'=COUNT(CASE WHEN MONTH(CITFECHA)=8 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'SEPTIEMBRE'=COUNT(CASE WHEN MONTH(CITFECHA)=9 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'OCTUBRE'=COUNT(CASE WHEN MONTH(CITFECHA)=10 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'NOVIEMBRE'=COUNT(CASE WHEN MONTH(CITFECHA)=11 AND YEAR(CITFECHA)=2020 THEN CITID END),
	'DICIEMBRE'=COUNT(CASE WHEN MONTH(CITFECHA)=12 AND YEAR(CITFECHA)=2020 THEN CITID END)
FROM HOSPITALES H
LEFT JOIN VW_CITAS C ON C.HOSPID=H.HOSPID
GROUP BY H.HOSPNOMBRE

GO

--8.- AÑO, Y TOTAL DE CITAS REALIZADAS POR DIA DE LA SEMANA.
SELECT
	YEAR(CitFecha) AS 'Año',
	'DOMINGO'=COUNT(CASE WHEN DATEPART(WEEKDAY,CITFECHA)=1 THEN CITID END),
	'LUNES'=COUNT(CASE WHEN DATEPART(WEEKDAY,CITFECHA)=2 THEN CITID END),
	'MARTES'=COUNT(CASE WHEN DATEPART(WEEKDAY,CITFECHA)=3 THEN CITID END),
	'MIERCOLES'=COUNT(CASE WHEN DATEPART(WEEKDAY,CITFECHA)=4 THEN CITID END),
	'JUEVES'=COUNT(CASE WHEN DATEPART(WEEKDAY,CITFECHA)=5 THEN CITID END),
	'VIERNES'=COUNT(CASE WHEN DATEPART(WEEKDAY,CITFECHA)=6 THEN CITID END),
	'SABADO'=COUNT(CASE WHEN DATEPART(WEEKDAY,CITFECHA)=7 THEN CITID END)
FROM VW_CITAS
GROUP BY YEAR(CitFecha)

--9.- AÑO Y TOTAL DE CITAS POR ZONA.
SELECT
    YEAR(CITFECHA) AS 'Año',
    'CENTRO'=COUNT(CASE WHEN ZONANOMBRE='CENTRO' THEN CITID END),
	'NORTE'=COUNT(CASE WHEN ZONANOMBRE='NORTE' THEN CITID END),
	'SUR'=COUNT(CASE WHEN ZONANOMBRE='SUR' THEN CITID END)
FROM VW_CITAS
GROUP BY YEAR(CITFECHA)

-- 10.- NOMBRE DE LA ZONA, TOTAL DE HOSPITALES QUE EXISTEN, TOTAL DE CONSULTORIOS QUE EXISTEN EN LA ZONA.
SELECT
  ZONANOMBRE,
  COUNT(HOSPID) AS 'TOTALHOSPITALES',
  COUNT(CONID) AS 'TOTALCONSULTORIOS'
FROM VW_CONSULTORIOS
GROUP BY ZONANOMBRE
