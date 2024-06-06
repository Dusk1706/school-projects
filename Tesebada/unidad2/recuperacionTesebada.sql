Drop DATABASE SistemaOperacional
Drop DATABASE AlmacenDatos

-----------------------------------------------------------------------------------------------------------------
CREATE DATABASE SistemaOperacional
GO
USE SistemaOperacional
GO
CREATE TABLE Importaciones (
	ID INT IDENTITY(1,1) PRIMARY KEY,
	Pais VARCHAR(30),
	Producto VARCHAR(30),
	Unidades INT,
	Importe DECIMAL(10, 2)
)
GO
CREATE TABLE Exportaciones (
	ID INT IDENTITY(1,1) PRIMARY KEY,
	Pais VARCHAR(30),
	Producto VARCHAR(30),
	Unidades INT,
	Importe DECIMAL(10, 2)
)
GO
CREATE TRIGGER TR_InsertImportaciones
ON Importaciones
FOR INSERT
AS
BEGIN
	DECLARE @Pais VARCHAR(30), @Producto VARCHAR(30)
	DECLARE @Unidades INT
	DECLARE @Importe DECIMAL(10, 2)
	DECLARE @MinUnidades INT

	DECLARE C_Insert CURSOR FOR
    SELECT 
		Pais, Producto, Unidades, Importe
    FROM INSERTED

	OPEN C_Insert
	
	FETCH NEXT FROM C_INSERT INTO @Pais, @Producto, @Unidades, @Importe

	WHILE @@FETCH_STATUS = 0
	BEGIN
		EXEC @MinUnidades = AlmacenDatos.dbo.SP_actualizarMinImportaciones @Pais, @Producto, @Unidades, 'MinUnidadesImportaciones', 1
		
		--Actualizar DWSumImporte
		IF EXISTS (
			SELECT 1 
			FROM 
				AlmacenDatos.dbo.TAImportaciones
			WHERE 
				@PAIS = Pais AND @Producto = Producto
		)
		BEGIN
			UPDATE AlmacenDatos.dbo.TAImportaciones 
			SET 
				DWSumImporte += (@Importe * @Unidades),
				DWMinUnidades = @MinUnidades
			WHERE 
				@Pais = Pais AND @Producto = Producto
		END
		ELSE --No existe
		BEGIN
			INSERT INTO AlmacenDatos.dbo.TAImportaciones  
			VALUES(@Pais, @Producto, (@Importe * @Unidades), @MinUnidades)
		END
		
		EXEC AlmacenDatos.dbo.SP_actualizarVM @pais, @producto, 'INSERT'

		FETCH NEXT FROM C_INSERT INTO @Pais, @Producto, @Unidades, @Importe
	END
	
	CLOSE C_Insert
    DEALLOCATE C_Insert
END
GO
CREATE TRIGGER TR_InsertExportaciones
ON Exportaciones
FOR INSERT
AS
BEGIN
	DECLARE @Pais VARCHAR(30), @Producto VARCHAR(30)
	DECLARE @Unidades INT
	DECLARE @Importe DECIMAL(10, 2)
	    
	DECLARE C_Insert CURSOR FOR
    SELECT 
		Pais, Producto, Unidades, Importe
    FROM INSERTED

	OPEN C_Insert
	
	FETCH NEXT FROM C_INSERT INTO @Pais, @Producto, @Unidades, @Importe

	WHILE @@FETCH_STATUS = 0
	BEGIN
		IF EXISTS (
			SELECT 1 
			FROM AlmacenDatos.dbo.TAExportaciones
			WHERE @PAIS = Pais AND @Producto = Producto
		)
		BEGIN
			UPDATE AlmacenDatos.dbo.TAExportaciones 
			SET DWSumImporte += (@Importe * @Unidades) 
			WHERE @Pais = Pais AND @Producto = Producto
		END
		ELSE 
		BEGIN
			INSERT INTO AlmacenDatos.dbo.TAExportaciones
			VALUES(@Pais, @Producto, (@Importe * @Unidades))
		END
		
		EXEC AlmacenDatos.dbo.SP_actualizarVM @pais, @producto, 'INSERT'

		FETCH NEXT FROM C_INSERT INTO @Pais, @Producto, @Unidades, @Importe
	END
	
	CLOSE C_Insert
    DEALLOCATE C_Insert
END
GO
CREATE TRIGGER TR_DeleteImportaciones
ON Importaciones
FOR DELETE
AS
BEGIN
	DECLARE @Pais VARCHAR(30), @Producto VARCHAR(30)
	DECLARE @Unidades INT
	DECLARE @Importe DECIMAL(10, 2)
	DECLARE @UnidadesVeces INT, @MinUnidades INT

    DECLARE C_DELETE CURSOR FOR
    SELECT 
		Pais, Producto, Unidades, Importe
    FROM DELETED

    OPEN C_DELETE;

    FETCH NEXT FROM C_DELETE INTO @Pais, @Producto, @Unidades, @Importe

    WHILE @@FETCH_STATUS = 0
    BEGIN
		EXEC @MinUnidades = AlmacenDatos.dbo.SP_actualizarMinImportaciones @Pais, @Producto, @Unidades, 'MinUnidadesImportaciones', -1
		
		IF @MinUnidades = 0 
		BEGIN
			DELETE FROM AlmacenDatos.dbo.TAImportaciones
			WHERE @Pais = Pais AND @Producto = Producto
		END
		ELSE
		BEGIN
			UPDATE AlmacenDatos.dbo.TAImportaciones
			SET	
				DWSumImporte -= (@Unidades * @Importe),
				DWMinUnidades = @MinUnidades
			WHERE @Pais = Pais AND @Producto = Producto
		END
		
		EXEC AlmacenDatos.dbo.SP_actualizarVM @pais, @producto, 'DELETE'

        FETCH NEXT FROM C_DELETE INTO @Pais, @Producto, @Unidades, @Importe
    END

    CLOSE C_DELETE;
    DEALLOCATE C_DELETE;
END
GO
CREATE TRIGGER TR_DeleteExportaciones
ON Exportaciones
FOR DELETE
AS
BEGIN
	DECLARE @Pais VARCHAR(30), @Producto VARCHAR(30)
	DECLARE @Unidades INT
	DECLARE @Importe DECIMAL(10, 2)
	DECLARE @Total DECIMAL(10, 2)

    DECLARE C_DELETE CURSOR FOR
    SELECT 
		Pais, Producto, Unidades, Importe
    FROM DELETED

    OPEN C_DELETE;

    FETCH NEXT FROM C_DELETE INTO @Pais, @Producto, @Unidades, @Importe

    WHILE @@FETCH_STATUS = 0
    BEGIN
		UPDATE AlmacenDatos.dbo.TAExportaciones
		SET	DWSumImporte -= (@Unidades * @Importe)
		WHERE @Pais = Pais AND @Producto = Producto
		
		SET @TOTAL = (
			SELECT DWSumImporte
			FROM AlmacenDatos.dbo.TAExportaciones
			WHERE @Pais = Pais AND @Producto = Producto
		)
		IF @TOTAL = 0
		BEGIN
			DELETE FROM AlmacenDatos.dbo.TAExportaciones
			WHERE 
				@Pais = Pais AND @Producto = Producto
		END

		EXEC AlmacenDatos.dbo.SP_actualizarVM @pais, @producto, 'DELETE'

        FETCH NEXT FROM C_DELETE INTO @Pais, @Producto, @Unidades, @Importe
    END

    CLOSE C_DELETE;
    DEALLOCATE C_DELETE;
END
GO
-----------------------------------------------------------------------------------------------------------------
CREATE DATABASE AlmacenDatos
GO
USE AlmacenDatos
GO
CREATE TABLE VistaMaterializada (
	Pais VARCHAR(30) NOT NULL,
	Producto VARCHAR(30) NOT NULL,
	VtasImportaciones DECIMAL(10, 2) NOT NULL,
	VtasExportaciones DECIMAL(10, 2) NOT NULL,
	MinUnidadesImportaciones INT NOT NULL,
	VnInicio INT NOT NULL,
	VnFin INT NOT NULL,
	OperacionVN VARCHAR(6) NOT NULL
)
GO
CREATE TABLE VistaMaterializadaUltimaVersion (
	Pais VARCHAR(30),
	Producto VARCHAR(30),
	UltimoVN INT
	
	PRIMARY KEY(Pais, Producto)
)
GO
CREATE TABLE ControlVN (
	CurrentVN INT NOT NULL,
	MaintenanceActive VARCHAR(5) NOT NULL
)
GO
INSERT INTO ControlVN
VALUES(1, 'False')
GO
CREATE TABLE TAImportaciones(
	Pais VARCHAR(30),
	Producto VARCHAR(30),
	DWSumImporte DECIMAL(10, 2) NOT NULL,
	DWMinUnidades INT NOT NULL

	PRIMARY KEY(Pais, Producto)
)
GO
CREATE TABLE TAExportaciones(
	Pais VARCHAR(30),
	Producto VARCHAR(30),
	DWSumImporte DECIMAL(10, 2) NOT NULL

	PRIMARY KEY(Pais, Producto)
)
GO
CREATE TABLE ImportacionesMinMax(
	Pais VARCHAR(30),
	Producto VARCHAR(30),
	AtrNombre VARCHAR(30),
	UnidadesValor INT,
	UnidadesVeces INT NOT NULL

	PRIMARY KEY(Pais, Producto, UnidadesValor)
)
GO
-----------------------------------------------------------------------------------------------------------------
CREATE FUNCTION FN_existeJoin(@Pais VARCHAR(30), @Producto VARCHAR(30))
RETURNS VARCHAR(5)
AS
BEGIN
    DECLARE @Resultado VARCHAR(5)

    IF EXISTS (
		SELECT 1 
		FROM TAImportaciones I
		INNER JOIN TAExportaciones E
			ON I.Pais = E.Pais AND I.Producto = E.Producto
		WHERE 
			I.Pais = @Pais AND I.Producto = @Producto
	)
        SET @Resultado = 'True'
    ELSE
        SET @Resultado = 'False'

    RETURN @Resultado
END
GO
-- Actualiza ImportacionesMinMax, borra si la nueva cantidad es 0  
-- y retorna el nuevo MinUnidades o 0 si no hay mas
-- Borra de la tabla asociada en caso de que sea 0
CREATE PROC SP_actualizarMinImportaciones (@Pais VARCHAR(30), @Producto VARCHAR(30), @Unidades INT, @ATRIBUTO VARCHAR(30), @CANTIDAD INT)
AS
BEGIN
	DECLARE @UnidadesVeces INT
	IF EXISTS (
		SELECT 1 
		FROM ImportacionesMinMax 
		WHERE 
			@Pais = Pais AND 
			@Producto = Producto AND 
			@Unidades = UnidadesValor AND
			@ATRIBUTO = AtrNombre
	)
	BEGIN
		UPDATE ImportacionesMinMax  
		SET UnidadesVeces += @CANTIDAD 
		WHERE 
			@Pais = Pais AND 
			@Producto = Producto AND 
			@Unidades = UnidadesValor AND
			@ATRIBUTO = AtrNombre
	END
	ELSE IF @CANTIDAD = 1 
	BEGIN
		INSERT INTO ImportacionesMinMax
		VALUES (@PAIS, @PRODUCTO, @ATRIBUTO, @Unidades, 1)
	END

	SET @UnidadesVeces = (
		SELECT UnidadesVeces
		FROM ImportacionesMinMax
		WHERE 
			@Pais = Pais AND 
			@Producto = Producto AND 
			@Unidades = UnidadesValor AND
			@ATRIBUTO = AtrNombre
	)

	IF @UnidadesVeces = 0
	BEGIN
		DELETE FROM ImportacionesMinMax
		WHERE 
			@Pais = Pais AND 
			@Producto = Producto AND 
			@Unidades = UnidadesValor AND
			@ATRIBUTO = AtrNombre
	END

	RETURN (
		SELECT ISNULL(MIN(UnidadesValor), 0)
		FROM ImportacionesMinMax 
		WHERE 
			@Pais = Pais AND 
			@Producto = Producto AND 
			@ATRIBUTO = AtrNombre 
	)
END
GO
CREATE PROC SP_actualizarVM(@Pais VARCHAR(30), @Producto VARCHAR(30), @TIPO VARCHAR(6))
AS
BEGIN
	DECLARE @INF INT, @UltimaVN INT, @CurrentVN INT, @DWMinUnidades INT
	DECLARE @MaintenanceActive VARCHAR(5), @ExisteJoin VARCHAR(5)
	DECLARE @DWSumImporteI DECIMAL(10, 2), @DWSumImporteE DECIMAL(10, 2)
	
	SET @INF = 2147483647
	
	SELECT 
		@CurrentVN = CurrentVN,  
		@MaintenanceActive = MaintenanceActive
	FROM ControlVN

	SET @ExisteJoin = dbo.FN_existeJoin(@Pais, @Producto)
	
	SET @DWSumImporteI = 0
	SET @DWMinUnidades = 0
	
	IF EXISTS (
		SELECT 1
		FROM TAImportaciones 
		WHERE @Pais = Pais AND @Producto = Producto
	)
	BEGIN
		SELECT 
			@DWSumImporteI = DWSumImporte,
			@DWMinUnidades = DWMinUnidades
		FROM TAImportaciones 
	END

	SET @DWSumImporteE = 0
	IF EXISTS (
		SELECT 1
		FROM TAExportaciones 
		WHERE @Pais = Pais AND @Producto = Producto
	)
	BEGIN
		SELECT @DWSumImporteE = DWSumImporte
		FROM TAExportaciones
		WHERE @Pais = Pais AND @Producto = Producto
	END
	

	IF EXISTS (
			SELECT 1 
			FROM VistaMaterializada 
			WHERE 
				@Pais = Pais AND @Producto = Producto
	)
	BEGIN	
		IF @ExisteJoin = 'True'
		BEGIN
			SET @TIPO = 'INSERT'
		END
		UPDATE VistaMaterializada
		SET
			VtasImportaciones = @DWSumImporteI,
			VtasExportaciones = @DWSumImporteE,
			MinUnidadesImportaciones = 	@DWMinUnidades,
			OperacionVN = @TIPO
		WHERE @Pais = Pais AND @Producto = Producto AND VnFin = @INF

		---
		--	SELECT * FROM VistaMaterializada
		--	WHERE  'Mexico' = Pais AND 'Audi' = Producto AND VnFin = 2147483647
		---
	END
	ELSE
	BEGIN
		IF @ExisteJoin = 'True'
		BEGIN			
			INSERT INTO VistaMaterializada 
			VALUES(@Pais, @Producto, @DWSumImporteI, @DWSumImporteE, @DWMinUnidades, @CurrentVN, @INF, @TIPO)

			INSERT INTO VistaMaterializadaUltimaVersion
			VALUES(@Pais, @Producto, @CurrentVN)
		END
	END	

	UPDATE ControlVN 
	SET MaintenanceActive = 'True'
END
GO
CREATE PROC SP_consultar
AS 
BEGIN
	DECLARE @INF INT, @CurrentVN INT
	DECLARE @MaintenanceActive VARCHAR(5)
	DECLARE @Pais VARCHAR(30), @Producto VARCHAR(30)
	DECLARE @VtasImportaciones DECIMAL(10, 2), @VtasExportaciones DECIMAL(10, 2)
	DECLARE @MinUnidadesImportaciones INT, @VnInicio INT, @VnFin INT
	DECLARE @OperacionVN VARCHAR(6)
	
	SET @INF = 2147483647
	
	SELECT 
		@CurrentVN = CurrentVN,
		@MaintenanceActive = MaintenanceActive
	FROM ControlVN
	
	SELECT *
	INTO #TablaAux
	FROM VistaMaterializada
	WHERE VnFin = @INF
	
	IF(@MaintenanceActive = 'True')
	BEGIN
		UPDATE VistaMaterializada 
		SET VnFin = @CurrentVN
		WHERE VnFin = @INF

		UPDATE #TablaAux
		SET VnInicio = @CurrentVN + 1,
			VnFin = @INF
		
		-- Se inserta las nuevas versiones
		INSERT INTO VistaMaterializada
		SELECT * FROM #TablaAux

		UPDATE VMU
		SET VMU.UltimoVN = @CurrentVN + 1
		FROM 
			VistaMaterializadaUltimaVersion VMU
			INNER JOIN VistaMaterializada VM
			ON VMU.Pais = VM.Pais AND VMU.Producto = VM.Producto
		WHERE VM.VnFin = @INF

		UPDATE ControlVN
		SET 
			CurrentVN = @CurrentVN + 1,
			MaintenanceActive = 'False'

	END
	
	SELECT 
		Pais, Producto, VtasImportaciones, 
		VtasExportaciones, MinUnidadesImportaciones
	FROM VistaMaterializada 
	WHERE 
		VnFin = @CurrentVN AND OperacionVN != 'DELETE'
END
GO
CREATE PROC SP_ConsultarAux
AS
BEGIN
	SELECT * FROM VistaMaterializada
	SELECT * FROM TAExportaciones
	SELECT * FROM TAImportaciones
	SELECT * FROM ImportacionesMinMax
	SELECT * FROM ControlVN
END
GO
-----------------------------------------------------------------------------------------------------------------
Use SistemaOperacional
INSERT INTO Importaciones VALUES ('Mexico', 'Audi', 2, 500)
INSERT INTO Importaciones VALUES ('Mexico', 'Audi', 3, 10000)
INSERT INTO Exportaciones VALUES ('Mexico', 'Audi', 4, 100000)
GO
INSERT INTO Importaciones VALUES ('China', 'Nissan', 2, 500)
INSERT INTO Exportaciones VALUES ('China', 'Nissan', 3, 10000)
GO
INSERT INTO Importaciones VALUES ('USA', 'Camaro', 2, 500)
INSERT INTO Exportaciones VALUES ('USA', 'Camaro', 3, 10000)
GO
SELECT * FROM Exportaciones
SELECT * FROM Importaciones
GO
DELETE FROM Importaciones
WHERE ID = 2
DELETE FROM Exportaciones
WHERE ID = 3
GO
EXEC AlmacenDatos.dbo.SP_consultar
EXEC AlmacenDatos.dbo.SP_ConsultarAux