

IF EXISTS (SELECT 1 FROM sistema WHERE nombre = 'Sistema prueba') THEN
    SELECT 'Sí existe' AS resultado;
ELSE
    INSERT INTO sistema (nombre, activo, aperturado)
    VALUES ('Sistema prueba', 0, 0);
END IF;