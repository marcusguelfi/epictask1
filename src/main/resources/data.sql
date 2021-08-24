CREATE TABLE task (
	id int PRIMARY KEY auto_increment, 
	title varchar(200),
	description TEXT,
	points int
);	

INSERT INTO task VALUES(1, 'Criar banco de dados', 'Criar o banco de dados na nuvem', 50);
INSERT INTO task VALUES(2, 'Protótipo', 'Prototipação de alta fidelidade', 30);
INSERT INTO task VALUES(3, 'API REST dos dados', 'Criar a API para endpoints da aplicação', 150);