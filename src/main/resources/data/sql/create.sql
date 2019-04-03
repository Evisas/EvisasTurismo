/*
	DROP DATABASE IF EXISTS evisas;
	CREATE DATABASE evisas COLLATE utf8mb4_unicode_ci;
	USE evisas;
*/

CREATE TABLE usuarios 
(
	id           	bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome         	varchar(80) NOT NULL,
	email        	varchar(40) NOT NULL UNIQUE,
	telefone       	numeric(11),
	senha         	varchar(15) NOT NULL
);

CREATE TABLE funcionarios 
(
	id           	bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome         	varchar(80) NOT NULL,
	email        	varchar(40) NOT NULL UNIQUE,
	matricula      	numeric(4) 	NOT NULL UNIQUE,
	senha         	varchar(15) NOT NULL
);

CREATE TABLE solicitacoesPassaporte 
(
	id           	bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nomeSolicitante	varchar(80) NOT NULL,
	cpfSolicitante 	numeric(11) NOT NULL,
	rgSolicitante	numeric(20) NOT NULL,
	dataSolicitacao	datetime 	NOT NULL,
	status			enum('PENDENTE', 'ACEITA', 'RECUSADA', 'CANCELADA')	NOT NULL,
	observacao		varchar(100),
	motivoRecusa	varchar(100),
	previsaoSaida	date		NOT NULL,
	idUsuario		bigint,
	
	CONSTRAINT FOREIGN KEY (idUsuario) REFERENCES usuarios(id) ON DELETE SET NULL
);

CREATE TABLE solicitacoesVisto 
(
	id           				bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nomeSolicitante				varchar(80) NOT NULL,
	cpfSolicitante 				numeric(11) NOT NULL,
	paisResidencia				varchar(30) NOT NULL,
	paisAVisitar				varchar(30) NOT NULL,
	possuiPassaporte 			boolean 	NOT NULL,
	dataNascimentoSolicitante 	date 		NOT NULL,
--	documento					
	dataSolicitacao				datetime 	NOT NULL,
	status						enum('PENDENTE', 'ACEITA', 'RECUSADA', 'CANCELADA')	NOT NULL,
	observacao					varchar(100),
	motivoRecusa				varchar(100),
	idUsuario					bigint,
	
	CONSTRAINT FOREIGN KEY (idUsuario) REFERENCES usuarios(id) ON DELETE SET NULL
);
