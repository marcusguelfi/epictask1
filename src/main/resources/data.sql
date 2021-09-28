CREATE TABLE task (
	id bigint PRIMARY KEY auto_increment,
	title varchar(200),
	description TEXT,
	points int,
	status int DEFAULT 0
);

CREATE TABLE user (
	id bigint PRIMARY KEY auto_increment,
	name varchar(200),
	email varchar(200),
	password varchar(200),
	githubuser varchar(200)
);

INSERT INTO user (name, email, password, githubuser) VALUES
('Joao Carlos', 'joao@gmail.com', '$2a$12$O2BXknIGzw4aqG52PMfVkut/8/pFrfAo1ZpHsNdZy6Tvb3I4vZ2Oe', 'joaocarloslima'),
('Carla Lopes', 'carla@gmail.com', '123', 'carla'),
('Fabio Cabrini', 'fabio@fiap.com.br', '123', 'jose');


INSERT INTO task (title, description, points, status) VALUES 
	('Criar banco de dados',
	'Criar um banco de dados na nuvem com Oracle', 
	200,
	10);
	
	
INSERT INTO task (title, description, points, status) VALUES 
	('Protótipo','Criação de protótipo de alta fidelidade', 100, 50);
	
INSERT INTO task (title, description, points, status) VALUES 
	('API REST','Publicação de API com endpoints da aplicação', 150, 95);