-- Rollback script: Remover os registros inseridos na tabela 'status'
IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'status' AND TABLE_SCHEMA = 'dbo')
BEGIN
    DELETE FROM status
    WHERE title IN ('backlog', 'priorizado', 'fazendo', 'feito');
END
