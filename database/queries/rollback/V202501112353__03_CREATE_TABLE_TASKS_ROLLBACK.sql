-- Rollback script: Remover a tabela 'tasks' se ela existir
IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'tasks' AND TABLE_SCHEMA = 'dbo')
BEGIN
    ALTER TABLE tasks DROP CONSTRAINT FK_STATUS;
    DROP TABLE tasks;
END
