CREATE TABLE TIEMPO_DIM (
	FECHA DATE PRIMARY KEY,
	DIA_SEMANA VARCHAR(255),
	DIA_MES INT,
	DIA_AÑO INT,
	SEMANA_MES VARCHAR(255),
	MES VARCHAR(255),
	AÑO INT,
	ESTACION VARCHAR(255),
	BIMESTRE VARCHAR(255),
	TRIMESTRE VARCHAR(255),
	CUATRIMESTRE VARCHAR(255),
	SEMESTRE VARCHAR(255),
	BISIESTO VARCHAR(255)
)
GO
CREATE PROCEDURE LlenarTablaTiempo AS
BEGIN
    DECLARE @FECHA_INICIO DATE = '2019-01-01';  
    DECLARE @FECHA_FIN DATE = '2023-12-31'; 

    WHILE @FECHA_INICIO <= @FECHA_FIN
    BEGIN
        DECLARE @MES_ INT = MONTH(@FECHA_INICIO);
        DECLARE @DIA_SEMANA VARCHAR(255) = 
            CASE DATEPART(WEEKDAY, @FECHA_INICIO)
                WHEN 1 THEN 'DOMINGO'
                WHEN 2 THEN 'LUNES'
                WHEN 3 THEN 'MARTES'
                WHEN 4 THEN 'MIERCOLES'
                WHEN 5 THEN 'JUEVES'
                WHEN 6 THEN 'VIERNES'
                ELSE 'SABADO'
            END;
        DECLARE @DIA_MES INT = DATEPART(DD, @FECHA_INICIO);
        DECLARE @DIA_AÑO INT = DATEPART(DY, @FECHA_INICIO);
        DECLARE @SEMANA_MES VARCHAR(255) = 
            CONCAT('Semana ', 
                CASE 
                    WHEN @DIA_MES <= 7 THEN '1'
                    WHEN @DIA_MES <= 14 THEN '2'
                    WHEN @DIA_MES <= 21 THEN '3'
                    ELSE '4'
                END
            );
        DECLARE @MES VARCHAR(255) = 
            CASE 
                WHEN @MES_ = 1 THEN 'ENERO'
                WHEN @MES_ = 2 THEN 'FEBRERO'
                WHEN @MES_ = 3 THEN 'MARZO'
                WHEN @MES_ = 4 THEN 'ABRIL'
                WHEN @MES_ = 5 THEN 'MAYO'
                WHEN @MES_ = 6 THEN 'JUNIO'
                WHEN @MES_ = 7 THEN 'JULIO'
                WHEN @MES_ = 8 THEN 'AGOSTO'
                WHEN @MES_ = 9 THEN 'SEPTIEMBRE'
                WHEN @MES_ = 10 THEN 'OCTUBRE'
                WHEN @MES_ = 11 THEN 'NOVIEMBRE'
                ELSE 'DICIEMBRE'
            END; 
        DECLARE @AÑO INT = YEAR(@FECHA_INICIO);
        
        DECLARE @FECHA_PRIMAVERA DATE = DATEFROMPARTS(@AÑO, 3, 21);
        DECLARE @FECHA_VERANO DATE = DATEFROMPARTS(@AÑO, 6, 21);
        DECLARE @FECHA_OTONO DATE = DATEFROMPARTS(@AÑO, 9, 23);
        DECLARE @FECHA_INVIERNO DATE = DATEFROMPARTS(@AÑO, 12, 21);

        DECLARE @ESTACION VARCHAR(255) = 
            CASE 
                WHEN @FECHA_INICIO >= @FECHA_PRIMAVERA AND @FECHA_INICIO < @FECHA_VERANO THEN 'Primavera'
                WHEN @FECHA_INICIO >= @FECHA_VERANO AND @FECHA_INICIO < @FECHA_OTONO THEN 'Verano'
                WHEN @FECHA_INICIO >= @FECHA_OTONO AND @FECHA_INICIO < @FECHA_INVIERNO THEN 'Otoño'
                ELSE 'Invierno'
            END;

        DECLARE @BIMESTRE VARCHAR(255) = 
            CASE 
                WHEN @MES_ <= 2 THEN 'BIMESTRE ENERO-FEBRERO'
                WHEN @MES_ <= 4 THEN 'BIMESTRE MARZO-ABRIL'
                WHEN @MES_ <= 6 THEN 'BIMESTRE MAYO-JUNIO'
                WHEN @MES_ <= 8 THEN 'BIMESTRE JULIO-AGOSTO'
                WHEN @MES_ <= 10 THEN 'BIMESTRE SEPTIEMBRE-OCTUBRE'
                ELSE 'BIMESTRE NOVIEMBRE-DICIEMBRE'
            END;
                
        DECLARE @TRIMESTRE VARCHAR(255) =
            CASE 
                WHEN @MES_ <= 3 THEN 'TRIMESTRE ENERO-MARZO'
                WHEN @MES_ <= 6 THEN 'TRIMESTRE ABRIL-JUNIO'
                WHEN @MES_ <= 9 THEN 'TRIMESTRE JULIO-SEPTIEMBRE'
                ELSE 'TRIMESTRE OCTUBRE-DICIEMBRE'
            END;

        DECLARE @CUATRIMESTRE VARCHAR(255) =
            CASE 
                WHEN @MES_ <= 4 THEN 'CUATRIMESTRE ENERO-ABRIL'
                WHEN @MES_ <= 8 THEN 'CUATRIMESTRE MAYO-AGOSTO'
                ELSE 'CUATRIMESTRE SEPTIEMBRE-DICIEMBRE'
            END;

        DECLARE @SEMESTRE VARCHAR(255) =
            CASE 
                WHEN @MES_ <= 6 THEN 'SEMESTRE ENERO-JUNIO'
                ELSE 'SEMESTRE JULIO-DICIEMBRE'
            END;

        DECLARE @BISIESTO NCHAR(255) = 
            CASE 
                WHEN (@AÑO % 4 = 0 AND @AÑO % 100 != 0) OR @AÑO % 400 = 0 THEN 'BISIESTO'
                ELSE 'NO BISIESTO'
            END;

        INSERT INTO TIEMPO_DIM VALUES (
            @FECHA_INICIO, @DIA_SEMANA, @DIA_MES, @DIA_AÑO, @SEMANA_MES, @MES, 
            @AÑO, @ESTACION, @BIMESTRE, @TRIMESTRE, @CUATRIMESTRE, @SEMESTRE, @BISIESTO
        );

        SET @FECHA_INICIO = DATEADD(DAY, 1, @FECHA_INICIO);
    END;
END;
GO
CREATE TABLE PAIS_ORIGEN_DIM (
	PAIS VARCHAR(255) PRIMARY KEY,
	POBLACION VARCHAR(255),
	CONTINENTE VARCHAR(255),
	CLIMA VARCHAR(255),
	RELIGION VARCHAR(255),
	SEGURIDAD VARCHAR(255),
	GOBIERNO VARCHAR(255),
	MONEDA VARCHAR(255),
	IDIOMA VARCHAR(255),
	TASA_CRECIMIENTO_POBLACION VARCHAR(255),
	TASA_CRECIMIENTO_ECONOMICO VARCHAR(255),
	TASA_MORTALIDAD VARCHAR(255),
	EDUCACION VARCHAR(255),
	ZONA_HORARIA VARCHAR(255)
)
GO
INSERT INTO PAIS_ORIGEN_DIM 
VALUES 
    (
        'MEXICO', '127 MILLONES', 'AMERICA', 'Tropical, Seco, Templado', 'CATOLICA', 
        'MEDIA', 'REPUBLICANA', 'PESO MEXICANO', 'ESPAÑOL', '1.2%', '2.5%', 
        '5.3 por cada 1000 habitantes', 'MEDIA', 'UTC-6 a UTC-8'
    ),
    (
        'ESTADOS UNIDOS', '331 millones', 'América', 'Variado', 'CRISTIANA', 'MEDIA', 'República federal', 'Dólar estadounidense', 'Inglés', '0.6%', '1.9%', '8.2 por cada 1000 habitantes', 'Alta', 'UTC-5 a UTC-10'
    ),
    (
        'JAPON', '126 millones', 'Asia', 'Templado', 'BUDISTA', 'ALTA', 'Monarquía constitucional', 'Yen japonés', 'Japonés', '-0.2%', '1.3%', '9.8 por cada 1000 habitantes', 'Alta', 'UTC+9'
    ),
    (
        'ALEMANIA', '83 millones', 'Europa', 'Templado', 'CRISTIANA', 'Alto', 'República federal', 'Euro', 'Alemán', '0.2%', '2.2%', '11.2 por cada 1000 habitantes', 'Alta', 'UTC+1 a UTC+2'
    ),
    (
        'PAISES BAJOS', '17 millones', 'Europa', 'Templado', 'CRISTIANA', 'MEDIA', 'Monarquía constitucional', 'Euro', 'Holandés', '0.2%', '1.8%', '9.2 por cada 1000 habitantes', 'Alta', 'UTC+1'
    ),
    (
        'CHINA', '1441 millones', 'Asia', 'Variado', 'ATEA', 'ALTA', 'República socialista', 'Yuan', 'Chino', '0.4%', '6.1%', '7.1 por cada 1000 habitantes', 'Alta', 'UTC+8 a UTC+6'
    ),
    (
        'CANADA', '38 millones', 'América', 'Variado', 'CRISTIANA', 'ALTA', 'Monarquía constitucional', 'Dólar canadiense', 'Inglés y francés', '0.8%', '1.6%', '7.7 por cada 1000 habitantes', 'Alta', 'UTC-3.5 a UTC-8'
    ),
    (
        'BRASIL', '212 millones', 'América', 'Variado', 'CRISTIANA', 'BAJA', 'República federal', 'Real brasileño', 'Portugués', '0.7%', '1.1%', '6.2 por cada 1000 habitantes', 'Alta', 'UTC-2 a UTC-5'
    ),
    (
        'COREA DEL SUR', '51 millones', 'Asia', 'Templado', 'CRISTIANA', 'ALTA', 'República presidencialista', 'Won surcoreano', 'Coreano', '0.1%', '2.9%', '6.3 por cada 1000 habitantes', 'Alta', 'UTC+9'
    ),
    (
        'AUSTRALIA', '25 millones', 'Oceanía', 'Variado', 'CRISTIANA', 'ALTA', 'Monarquía constitucional', 'Dólar australiano', 'Inglés', '0.9%', '2.2%', '7.3 por cada 1000 habitantes', 'Alta', 'UTC+8 a UTC+11'
    );


GO
CREATE TABLE MEDIO_TRANSPORTE_DIM (
	TRANSPORTE VARCHAR(255) PRIMARY KEY,
	VELOCIDAD_PROMEDIO VARCHAR(255),
	TIEMPO_ENTREGA VARCHAR(255),
	CAPACIDAD_CARGA VARCHAR(255),
	CAPACIDAD_PERSONAS VARCHAR(255),
	COSTO_AMBIENTAL VARCHAR(255),
	COSTO_VIAJE INT,
	DISTANCIA_MAXIMA VARCHAR(255),
	TIPO_LICENCIA VARCHAR(255),
	PESO VARCHAR(255)
)
GO
INSERT INTO MEDIO_TRANSPORTE_DIM
VALUES
    (
        'CARRETERA', '60 km/h', '1 día', '10 toneladas', '5 personas', 'Alto', 1000, '1000 km', 'Licencia tipo A', '1000 kg'
    ),
    (
        'MAR', '40 km/h', '3 días', '1000 toneladas', '1000 personas', 'Bajo', 2000, '10000 km', 'Licencia tipo B', '10000 kg'
    ),
    (
        'CIELO', '800 km/h', '6 horas', '100 toneladas', '500 personas', 'Medio', 5000, '10000 km', 'Licencia tipo C', '1000 kg'
    ),
    (
        'RIELES', '100 km/h', '1 día', '100 toneladas', '500 personas', 'Bajo', 2000, '1000 km', 'Licencia tipo D', '1000 kg'
    ),
    (
        'TREN', '120 km/h', '1 día', '100 toneladas', '500 personas', 'Bajo', 2000, '1000 km', 'Licencia tipo D', '1000 kg'
    );
GO
-- Procedimiento para a todos las tuplas con transporte null asignarle al 25% un transporte
-- Mar, Cielo, Tren, Carretera   
-- Se debe contar cuantas tuplas son null y luego para el primer 25% asignarle Mar, para el segundo 25% Cielo, para el tercer 25% Tren y para el ultimo 25% Carretera
CREATE PROCEDURE LlenarTransporteImportesAux AS
BEGIN
    DECLARE @TRANSPORTE VARCHAR(255);
    DECLARE @CANTIDAD_TRANSPORTE INT;
    DECLARE @PORCENTAJE INT;
    DECLARE @CONTADOR INT = 0;
    SELECT @CANTIDAD_TRANSPORTE = COUNT(*) FROM IMPORTES_AUX;
    SET @PORCENTAJE = @CANTIDAD_TRANSPORTE / 4;

    DECLARE @CLAVE INT = MIN(CLAVE) FROM IMPORTES_AUX WHERE TRANSPORTE IS NULL;

    WHILE @CONTADOR < @CANTIDAD_TRANSPORTE
    BEGIN
        SET @TRANSPORTE = 
            CASE 
                WHEN @CONTADOR < @PORCENTAJE THEN 'MAR'
                WHEN @CONTADOR < @PORCENTAJE * 2 THEN 'CIELO'
                WHEN @CONTADOR < @PORCENTAJE * 3 THEN 'TREN'
                ELSE 'CARRETERA'
            END;

        UPDATE IMPORTES_AUX
        SET TRANSPORTE = @TRANSPORTE
        WHERE CLAVE = @CLAVE;

        SET @CONTADOR = @CONTADOR + 1;
        SET @CLAVE = MIN(CLAVE) FROM IMPORTES_AUX WHERE TRANSPORTE IS NULL AND CLAVE > @CLAVE;
    END;
END;

