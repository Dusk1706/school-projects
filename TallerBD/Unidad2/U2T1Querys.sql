
-- 1.- Mostrar el nombre completo del empleado imprimiendo el nombre en un renglon y el apellido en otro
SELECT FIRSTNAME + CHAR(13) + LASTNAME
FROM EMPLOYEES

-- 2.- Mostrar los empleados que entraron a trabajar cuando tenían entre 30 y 50 años.
SELECT *
FROM EMPLOYEES
WHERE DATEDIFF(YEAR,BIRTHDATE, HIREDATE) BETWEEN 30 AND 50

-- 3.- Consulta con el nombre del empleado un texto de la siguiente forma cada empleado:
--     'JOSE PEREZ ENTREO A TRABAJAR UN MARTES 03 DE JUNIO DE 2000 CON UNA EDAD DE 35 AÑOS'
SELECT
	UPPER(
		CONCAT(
			FIRSTNAME, ' ', LASTNAME, ' ENTRO A TRABAJAR UN ',
			DATENAME(WEEKDAY, HIREDATE), ' ', FORMAT(HIREDATE, 'dd'), ' DE ',
			DATENAME(MONTH, HIREDATE), ' DE ', DATENAME(YEAR, HIREDATE),
			' CON UNA EDAD DE ', DATEDIFF(YEAR, BIRTHDATE, HIREDATE)
		)
	)
FROM EMPLOYEES

-- 4.- Consulta con la clave y fecha de la orden que se hayan realizado los días lunes del primer semestre de todos los años.
SELECT ORDERID, ORDERDATE
FROM ORDERS
WHERE DATEPART(WEEKDAY, ORDERDATE)=2 AND MONTH(ORDERDATE) BETWEEN 1 AND 6

-- 5.- Consulta con las clave de la orden y fecha de la orden.
--     Mostrar solamente las ordenes que se hayan realizadas los días viernes por los empleados 1,3 y 5.
SELECT ORDERID, ORDERDATE
FROM ORDERS
WHERE DATEPART(WEEKDAY,ORDERDATE)=6 AND EMPLOYEEID IN (1,3,5)

-- 6.- Mostrar en una sola columna la siguiente información de cada orden :
--     'la orden 1 fue realizada por el cliente ANTON y atendida por el empleado 1’
SELECT
CONCAT(
'la orden ', ORDERID, ' fue realizada por el cliente ', UPPER(CUSTOMERID),
' y atendida por el empleado ', EMPLOYEEID
)
FROM ORDERS

-- 7.- Consulta con los clientes cuyo nombre empiece con consonante y sea mayor a 10 caracteres.
SELECT CONTACTNAME
FROM CUSTOMERS
WHERE CONTACTNAME LIKE '[^aeiou]%' AND LEN(CONTACTNAME) > 10

-- 8.- Consulta con los productos que su nombre con A,B, N y termine con N
SELECT PRODUCTNAME
FROM PRODUCTS
WHERE PRODUCTNAME LIKE '[ABN]%' AND PRODUCTNAME LIKE '%N'

-- 9.- Consulta con los empleados que su nombre y apellido termine con consonante.
SELECT FIRSTNAME, LASTNAME
FROM EMPLOYEES
WHERE FIRSTNAME LIKE '%[^aeiou]' AND LASTNAME LIKE '%[^aeiou]'

-- 10.- Consulta con todas las ordenes que se hayan realizado en los meses que inicial con vocal.
SELECT *
FROM ORDERS
WHERE DATENAME(MONTH,ORDERDATE) LIKE '[aeiou]%'

-- 11.- Consulta con los nombres de producto que tengan solamente 3 vocales.
SELECT PRODUCTNAME
FROM PRODUCTS
WHERE PRODUCTNAME LIKE '%[aeiouáéíóúäëïöü]%[aeiouáéíóúäëïöü]%[aeiouáéíóúäëïöü]%'
AND PRODUCTNAME NOT LIKE
'%[aeiouáéíóúäëïöü]%[aeiouáéíóúäëïöü]%[aeiouáéíóúäëïöü]%[aeiouáéíóúäëïöü]%'

-- 12.- Consulta con las fechas de las ordenes cuyo año sea multiplo de 3.
SELECT ORDERDATE
FROM ORDERS
WHERE YEAR(ORDERDATE) % 3 = 0

-- 13.- Consulta con las ordenes que se hayan realizado en sábado y domingo, y que hayan sido realizadas por los empleados 1,2 y 5.
SELECT *
FROM ORDERS
WHERE DATEPART(WEEKDAY,ORDERDATE) IN (1,7) AND EMPLOYEEID IN (1,2,5)

-- 14.- Consulta con las ordenes que no tengan compañía de envío o que se hayan realizado en el mes de enero.
SELECT *
FROM ORDERS
WHERE SHIPNAME IS NULL OR MONTH(ORDERDATE)=1

-- 15.- Consulta con las 10 últimas ordenes de 1997.
SELECT TOP 10 *
FROM ORDERS
WHERE YEAR(ORDERDATE) = 1997
ORDER BY ORDERDATE DESC

-- 16.- Consulta con los 10 productos más caros del proveedor 1, 2 y 5.
SELECT TOP 10 *
FROM PRODUCTS
WHERE SUPPLIERID IN(1, 2, 5)
ORDER BY UNITPRICE DESC

-- 17.- Consulta con los 4 empleados con mas antigüedad.
SELECT *
FROM EMPLOYEES
ORDER BY HIREDATE ASC
-- 18.- Consulta con empleado con una antigüedad de 10,20 o 30 años y con una
--      edad mayor a 30, o con los empleados que vivan en un blvd y no tengan una región asignada.
SELECT *
FROM EMPLOYEES
WHERE
(DATEDIFF(YEAR, HIREDATE, GETDATE()) IN (10, 20, 30) AND
DATEDIFF(YEAR, BIRTHDATE, GETDATE()) > 30 ) OR
(ADDRESS LIKE '%Blvd%' AND REGION IS NULL)

-- 19.- Consulta con las ordenes el código postal de envío tenga solamente letras.
SELECT SHIPPOSTALCODE
FROM ORDERS
WHERE SHIPPOSTALCODE LIKE '%[a-z]%' AND SHIPPOSTALCODE NOT LIKE '%[^a-z]%'

-- 20.- Consulta con las ordenes que se hayan realizado en 1996 y en los meses que contengan la letra R.
SELECT *
FROM ORDERS
WHERE YEAR(ORDERDATE)=1996 AND DATENAME(MONTH,ORDERDATE) LIKE '%R%'