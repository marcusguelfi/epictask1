CREATE TABLE task (
	id bigint PRIMARY KEY auto_increment,
	title varchar(200),
	description TEXT,
	points int,
	status int DEFAULT 0,
	user_id int
);

CREATE TABLE user (
	id bigint PRIMARY KEY auto_increment,
	name varchar(200),
	email varchar(200),
	password varchar(200),
	githubuser varchar(200)
);

CREATE TABLE role (
	id int primary key auto_increment,
	name varchar(200)
);

CREATE TABLE user_roles(
	user_id int,
	roles_id int
);

INSERT INTO role (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO user_roles VALUES (1, 1);


INSERT INTO user (name, email, password, githubuser) VALUES
('Joao Carlos', 'joao@gmail.com', '$2a$12$O2BXknIGzw4aqG52PMfVkut/8/pFrfAo1ZpHsNdZy6Tvb3I4vZ2Oe', 'joaocarloslima'),
('Carla Lopes', 'carla@gmail.com', '$2a$12$O2BXknIGzw4aqG52PMfVkut/8/pFrfAo1ZpHsNdZy6Tvb3I4vZ2Oe', 'carla'),
('Fabio Cabrini', 'fabio@fiap.com.br', '$2a$12$O2BXknIGzw4aqG52PMfVkut/8/pFrfAo1ZpHsNdZy6Tvb3I4vZ2Oe', 'jose');


INSERT INTO task (title, description, points, status, user_id) VALUES 
	('Criar banco de dados',
	'Criar um banco de dados na nuvem com Oracle', 
	200,
	10,
	1);
	
	
INSERT INTO task (title, description, points, status) VALUES 
	('Protótipo','Criação de protótipo de alta fidelidade', 100, 50);
	
INSERT INTO task (title, description, points, status, user_id) VALUES 
	('API REST','Publicação de API com endpoints da aplicação', 150, 95, 2);