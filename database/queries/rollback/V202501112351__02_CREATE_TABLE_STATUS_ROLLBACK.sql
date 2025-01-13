-- Rollback script: Remoção da tabela Status

IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'status' AND TABLE_SCHEMA = 'dbo')
BEGIN
    DROP TABLE status;
END