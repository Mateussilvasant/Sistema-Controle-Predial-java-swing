create database sistemaPredial
default character set utf8
default collate utf8_general_ci;
use sistemaPredial;
CREATE TABLE empresa 
(
  CNPJ 			VARCHAR(18) 	NOT NULL,
  RazaoSocial 	VARCHAR(30) 	NOT NULL,
  temperatura 	VARCHAR(2) 	    NOT NULL,
  HoraInicio 	TIME 			NOT NULL DEFAULT '00:00:00',
  HoraFim 		TIME 			NOT NULL DEFAULT '00:00:00',
  horaArInicio  time			not null default '00:00:00',
  horaArFim		time			not null default '00:00:00',
  PRIMARY KEY (CNPJ)
  )DEFAULT CHARACTER SET = utf8;

CREATE TABLE conjunto 
(
  idConjunto 	INT(11) 		NOT NULL AUTO_INCREMENT,
  andar 		INT(11) 		NOT NULL,
  numero 		VARCHAR(45) 	NOT NULL,
  Empresa_CNPJ 	VARCHAR(18),
  PRIMARY KEY (idConjunto),
  INDEX fk_Conjunto_Empresa_idx(Empresa_CNPJ ASC),
  CONSTRAINT fk_Conjunto_Empresa
    FOREIGN KEY (Empresa_CNPJ)
		REFERENCES empresa(CNPJ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
)DEFAULT CHARACTER SET = utf8;

CREATE TABLE usuario 
(
  CPF  					varchar(14) 			NOT NULL,
  Nome 					VARCHAR(25)				NOT NULL,
  sobrenome				varchar(25)				NOT NULL,
  usuario 				varchar(25)				NOT NULL,
  senha					varchar(10)				NOT NULL,	
  Telefone 				VARCHAR(15) 			NOT NULL,
  HoraEntrada 			TIME 					NOT NULL,
  HoraSaida 			TIME 					NOT NULL,
  Tipo 					ENUM('S', 'A', 'F') 	NOT NULL,
  alterarTemperatura	boolean					not null,
  Empresa_CNPJ 			VARCHAR(18) 			NOT NULL,
  UNIQUE KEY(cpf),
  PRIMARY KEY (CPF, Empresa_CNPJ),
  INDEX fk_Usuario_Empresa1_idx (Empresa_CNPJ ASC),
	CONSTRAINT fk_Usuario_Empresa1
		FOREIGN KEY (Empresa_CNPJ)
		REFERENCES empresa(CNPJ)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
)DEFAULT CHARACTER SET = utf8;

CREATE TABLE acesso 
(
  horaEntrada TIMESTAMP   NOT NULL,
  horaSaida   TIMESTAMP   NOT NULL,
  usuarioCPF  VARCHAR(18) NOT NULL,
  
  INDEX fk_acesso_usuario_idx (usuarioCPF ASC),
  CONSTRAINT fk_acesso_usuario
    FOREIGN KEY (usuarioCPF)
    REFERENCES usuario(CPF)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)DEFAULT CHARACTER SET = utf8;

/*Dados*/

# Adiciona os conjuntos ao edifício
insert into conjunto values
#IdConjunto|Andar|Sala|Empresa_CNPJ
('1','1','1',null),('2','2','2',null),
('3','3','3',null),('4','4','4',null),
('5','5','5',null),('6','6','6',null),
('7','7','7',null),('8','8','8',null),
('9','9','9',null),('10','9','10',null),
('11','10','11',null),('12','11','12',null);

#Empresas previamente cadastradas
#CNPJ|RazaoSocial|Temperatura|HoraInicio|HoraFim|HoraARInicio|HoraArFIm
insert into empresa values 
('85.664.579/6954-25', 'Sistema Predial','28', '03:45:00', '23:50:00', '10:16:00', '17:35:00'),
('77.888.556/6998-87', 'MyFindComida','24', '08:00:00', '18:00:00', '08:00:00', '18:00:00'),
('22.231.884/2318-65', 'TransporteInc','30', '05:15:00', '20:00:00', '11:50:00', '15:25:00'),
('75.478.314/4962-44', 'Niplan','23', '09:00:00', '22:00:00', '08:50:00', '23:25:00'),
('88.752.123/6125-00', 'ThePetshop','20', '10:15:00', '19:00:00', '09:50:00', '18:25:00');

#Adiciona conjuntos as empresas
update conjunto set Empresa_CNPJ = '85.664.579/6954-25' where idConjunto = 1;
update conjunto set Empresa_CNPJ = '22.231.884/2318-65' where idConjunto = 2;
update conjunto set Empresa_CNPJ = '22.231.884/2318-65' where idConjunto = 3;
update conjunto set Empresa_CNPJ = '77.888.556/6998-87' where idConjunto = 4;
update conjunto set Empresa_CNPJ = '75.478.314/4962-44' where idConjunto = 5;
update conjunto set Empresa_CNPJ = '88.752.123/6125-00' where idConjunto = 10;


#Adiciona funcionarios as empresas
#CPF|Nome|Sobrenome|Usuario|Senha|Telefone|HoraEntrada|HoraSaida|Tipo|alterarTemperatura|Empresa_CNPJ
insert into usuario values
('264.317.752-22','Flavio' ,'Vaz'   ,'Vaz'    , '123456','(11)2365-7463','08:10:00','11:50:00','S',  true, '85.664.579/6954-25'),
('147.635.249-83','Douglas','Miler' ,'Douglas', '123456','(11)4753-1225','08:10:00','11:50:00','S',  true, '85.664.579/6954-25'),
('865.469.852-48','Mateus' ,'Santos','Mateus' , '123456','(11)8756-4756','08:10:00','11:50:00','S',  true, '85.664.579/6954-25'),
('000.000.000-00','Administrador','Admin','Admin','padrao','(00)0000-0000','00:00:00','00:00:00','S',true, '85.664.579/6954-25'),
('856.478.321-75','Maria ','Dequem'    ,'Maria2016','123456','(11)6487-3697','05:00:00','20:50:00','A',  true, '85.664.579/6954-25'),
('348.752.114-47','Alfredo','Prazeirozo','Alfredo','123456','(11)1036-4598','07:45:00','18:50:00','F', true,'77.888.556/6998-87'),
('459.214.357-12','Juvenalda','Gulosa','Juvenalda','123456','(11)8564-1268','07:45:00','18:50:00','F', true,'77.888.556/6998-87'),
('198.642.102.33','Vicente','Souza','ViceSouza','123456','(11)3487-1436','05:00:00','20:50:00','F', false, '22.231.884/2318-65'),
('756.463.258-98','Vitor','Tocagaita','Vitor','123456','(11)5789-7563','05:00:00','20:50:00','F',   false, '22.231.884/2318-65'),
('120.478.472-33','Anatalino ','Reguete','Reguete','123456','(11)1415-6487','05:00:00','20:50:00','F', true, '75.478.314/4962-44'),
('478.523.147-14','Darkson ','Veigas','Darkson ','123456','(11)1204-7523','05:00:00','20:50:00','F', false, '75.478.314/4962-44'),
('102.634.457-78','Kaelisson  ','Bruno','Kaelisson ','123456','(11)1204-7523','05:00:00','20:50:00','F', true, '88.752.123/6125-00'),
('201.512.640-97','Remo  ','Longo','Remo ','123456','(11)1204-7523','05:00:00','20:50:00','F', true, '88.752.123/6125-00');

/* Inserindo alguns acesso*/
insert into acesso value
('2016-10-09  08:30:00','2016-10-09 21:45:35','147.635.249-83'),
('2016-10-10  08:28:55','2016-10-10 22:00:44','147.635.249-83'),
('2016-10-11  09:00:00','2016-10-11 21:00:00','147.635.249-83'),
('2016-10-12  08:45:36','2016-10-12 21:57:55','147.635.249-83'),
('2016-10-10  08:28:55','2016-10-10 22:00:44','102.634.457-78'),
('2016-10-11  09:00:00','2016-10-11 21:16:00','201.512.640-97'),
('2016-10-12  08:45:36','2016-10-12 21:57:55','102.634.457-78'),
('2016-10-09  08:30:00','2016-10-09 21:45:35','201.512.640-97'),
('2016-10-10  08:28:55','2016-10-10 22:28:44','348.752.114-47'),
('2016-10-11  03:50:00','2016-10-11 23:23:23','348.752.114-47'),
('2016-10-12  08:45:36','2016-10-12 23:57:55','348.752.114-47');