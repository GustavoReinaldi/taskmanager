IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'tasks' AND TABLE_SCHEMA = 'dbo')
BEGIN
	CREATE TABLE tasks (
    id bigint IDENTITY(1,1) PRIMARY KEY, 
    title VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    createdAt date DEFAULT GETDATE(),
    id_status bigint,
    CONSTRAINT FK_STATUS FOREIGN KEY (id_status) REFERENCES status(id)

	);
END