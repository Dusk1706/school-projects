--Tarea 4: Familias de vistas de la base de datos Ferreterias

-- Vista VW_CLIENTES
CREATE VIEW VW_CLIENTES AS
SELECT 
	C.CTEID, C.CTENOMBRE, C.CTEAPEPAT,C.CTEAPEMAT,
	C.CTEDOMICILIO, C.CTETELEFONO, C.CTECELULAR,
	C.CTERFC, C.CTECURP, C.CTEFECHANACIMIENTO, C.CTESEXO,

	COL.COLID, COL.COLNOMBRE, COL.COLCP,

	M.MUNID, M.MUNNOMBRE
FROM CLIENTES C
INNER JOIN COLONIAS COL ON C.COLID=COL.COLID
INNER JOIN MUNICIPIOS M ON M.MUNID=COL.MUNID

GO

-- Vista VW_EMPLEADOS
CREATE VIEW VW_EMPLEADOS AS
SELECT
	E.EMPID, E.EMPNOMBRE, E.EMPAPEPAT, E.EMPAPEMAT,
	E.EMPDOMICILIO, E.EMPTELEFONO, E.EMPCELULAR,
	E.EMPRFC, E.EMPCURP, E.EMPFECHAINGRESO, 
	E.EMPFECHANACIMIENTO, E.JEFEID,

	Z.ZONAID, Z.ZONANOMBRE, Z.ZONADESCRIPCION
FROM EMPLEADOS E
INNER JOIN ZONAS Z ON E.ZONAID=Z.ZONAID

GO

-- Vista VW_VENTAS
CREATE VIEW VW_VENTAS AS
SELECT
	V.FOLIO, V.FECHA,

	F.FERRID, F.FERRNOMBRE, 
	F.FERRDOMICILIO, F.FERRTELEFONO,

	C.CTEID, C.CTENOMBRE, C.CTEAPEPAT,C.CTEAPEMAT,
	C.CTEDOMICILIO, C.CTETELEFONO, C.CTECELULAR,
	C.CTERFC, C.CTECURP, C.CTEFECHANACIMIENTO, C.CTESEXO,

	C.COLID, C.COLNOMBRE, C.COLCP,

	C.MUNID, C.MUNNOMBRE,

	E.EMPID, E.EMPNOMBRE, E.EMPAPEPAT, E.EMPAPEMAT,
	E.EMPDOMICILIO, E.EMPTELEFONO, E.EMPCELULAR,
	E.EMPRFC, E.EMPCURP, E.EMPFECHAINGRESO, 
	E.EMPFECHANACIMIENTO, E.JEFEID,

	E.ZONAID, E.ZONANOMBRE, E.ZONADESCRIPCION
FROM VENTAS V
INNER JOIN FERRETERIAS F ON F.FERRID = V.FERRID
INNER JOIN VW_CLIENTES C ON C.CTEID = V.CTEID
INNER JOIN VW_EMPLEADOS E ON E.EMPID = V.EMPID

GO

--Vista VW_ARTICULOS
CREATE VIEW VW_ARTICULOS AS
SELECT
	A.ARTID, A.ARTNOMBRE, A.ARTDESCRIPCION, A.ARTPRECIO,

	F.FAMID, F.FAMNOMBRE
FROM ARTICULOS A
INNER JOIN FAMILIAS F ON A.FAMID=F.FAMID

GO

-- Vista VW_DETALLE
CREATE VIEW VW_DETALLE AS
SELECT
	D.CANTIDAD, D.PRECIO,

	A.ARTID, A.ARTNOMBRE, A.ARTDESCRIPCION, A.ARTPRECIO,

	A.FAMID, A.FAMNOMBRE,

	V.FOLIO, V.FECHA,

	V.FERRID, V.FERRNOMBRE, 
	V.FERRDOMICILIO, V.FERRTELEFONO,

	V.CTEID, V.CTENOMBRE, V.CTEAPEPAT,V.CTEAPEMAT,
	V.CTEDOMICILIO, V.CTETELEFONO, V.CTECELULAR,
	V.CTERFC, V.CTECURP, V.CTEFECHANACIMIENTO, V.CTESEXO,

	V.COLID, V.COLNOMBRE, V.COLCP,

	V.MUNID, V.MUNNOMBRE,

	V.EMPID, V.EMPNOMBRE, V.EMPAPEPAT, V.EMPAPEMAT,
	V.EMPDOMICILIO, V.EMPTELEFONO, V.EMPCELULAR,
	V.EMPRFC, V.EMPCURP, V.EMPFECHAINGRESO, 
	V.EMPFECHANACIMIENTO, V.JEFEID,

	V.ZONAID, V.ZONANOMBRE, V.ZONADESCRIPCION
FROM DETALLE D
INNER JOIN VW_ARTICULOS A ON A.ARTID = D.ARTID
INNER JOIN VW_VENTAS V ON V.FOLIO = D.FOLIO
GO
-- Tarea 5: Familias de vistas de la base de datos Congreso de estudiantes

-- Vista VW_ESTUDIANTES
CREATE VIEW VW_ESTUDIANTES AS
SELECT
	E.ESTID, E.ESTNOMBRE, E.ESTAPELLIDOS, 
	E.ESTDOMICILIO, E.ESTCORREO, E.ESTCELULAR,

	ESC.ESCID, ESC.ESCNOMBRE, ESC.ESCDOMICILIO,

	M.MUNID, M.MUNNOMBRE
FROM ESTUDIANTES E
INNER JOIN ESCUELAS ESC ON ESC.ESCID = E.ESCID
INNER JOIN MUNICIPIOS M ON M.MUNID = ESC.MUNID
GO
-- Vista VW_EVENTOS
CREATE VIEW VW_EVENTOS AS
SELECT
	E.EVEID, E.EVENOMBRE, E.EVEDESCRIPCION, 
	E.EVEFECHA, E.EVELUGAR, E.EVECOSTO,
	
	EX.EXPID, EX.EXPNOMBRE, EX.EXPAPELLIDOS, 
	EX.EXPCORREO,EX.EXPCELULAR
FROM EVENTOS E
INNER JOIN EXPOSITORES EX ON EX.EXPID = E.EXPID
GO
-- Vista VW_REGISTRO
CREATE VIEW VW_REGISTRO AS
SELECT
	R.FOLIO, R.FECHA, 
	
	C.CONID, C.CONNOMBRE,C.CONDESCRIPCION,
	C.CONFECHAINI,C.CONFECHAFIN, C.CONLUGAR, 
	
	E.ESTID, E.ESTNOMBRE, E.ESTAPELLIDOS, 
	E.ESTDOMICILIO, E.ESTCORREO, E.ESTCELULAR,

	E.ESCID, E.ESCNOMBRE, E.ESCDOMICILIO,

	E.MUNID, E.MUNNOMBRE
FROM REGISTRO R
INNER JOIN CONGRESOS C ON C.CONID = R.CONID
INNER JOIN VW_ESTUDIANTES E ON E.ESTID = R.ESTID
GO
-- Vista VW_REGISTROXEVENTO
CREATE VIEW VW_REGISTROXEVENTO AS
SELECT 
	E.EVEID, E.EVENOMBRE, E.EVEDESCRIPCION, 
	E.EVEFECHA, E.EVELUGAR, E.EVECOSTO,
	
	E.EXPID, E.EXPNOMBRE, E.EXPAPELLIDOS, 
	E.EXPCORREO,E.EXPCELULAR,

	R.FOLIO, R.FECHA, 
	
	R.CONID, R.CONNOMBRE,R.CONDESCRIPCION,
	R.CONFECHAINI,R.CONFECHAFIN, R.CONLUGAR, 
	
	R.ESTID, R.ESTNOMBRE, R.ESTAPELLIDOS, 
	R.ESTDOMICILIO, R.ESTCORREO, R.ESTCELULAR,

	R.ESCID, R.ESCNOMBRE, R.ESCDOMICILIO,

	R.MUNID, R.MUNNOMBRE
FROM REGISTROXEVENTO RE
INNER JOIN VW_EVENTOS E ON E.EVEID = RE.EVEID
INNER JOIN VW_REGISTRO R ON R.FOLIO = RE.FOLIO
