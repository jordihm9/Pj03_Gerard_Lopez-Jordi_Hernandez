-- M14 DUAL
-- UF2 Projectes
-- Projecte 03: Facturacio
-- Authors: Gerard Lopez & Jordi Hernandez

DROP SCHEMA IF EXISTS `Pj03_Gerard-Jordi`;

CREATE SCHEMA `Pj03_Gerard-Jordi`
	DEFAULT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';

USE `Pj03_Gerard-Jordi`;

-- --------------------------
-- CLIENTS TABLE
-- --------------------------
DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients` (
	`id`		INT UNSIGNED	NOT NULL AUTO_INCREMENT,
	`nif`		VARCHAR(9)		NOT NULL COMMENT '8 digits + 1 letter',
	`name`		VARCHAR(25)		NOT NULL,
	`lastname`	VARCHAR(25)		NOT NULL,
	`address`	VARCHAR(50)		NOT NULL,
	`town`		VARCHAR(25)		NOT NULL,

	CONSTRAINT `PK_clients`		PRIMARY KEY (`id`),
	CONSTRAINT `UK_clients_nif` UNIQUE (`nif`)
);

-- --------------------------
-- ARTICLES TABLE
-- --------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
	`id`		INT UNSIGNED	NOT NULL AUTO_INCREMENT,
	`name`		VARCHAR(40) 	NOT NULL,
	`price`		DOUBLE			NOT NULL,

	CONSTRAINT `PK_articles`	PRIMARY KEY (`id`)
);

-- --------------------------
-- INVOICES TABLE
-- --------------------------
DROP TABLE IF EXISTS `invoices`;
CREATE TABLE `invoices` (
	`id`				INT UNSIGNED	NOT NULL AUTO_INCREMENT,
	`date`				DATETIME 		NOT NULL,
	`paid`				TINYINT(1)		NOT NULL,
	`taxable_base`		DOUBLE			NOT NULL COMMENT 'Price without IVA',
	`iva`				FLOAT			NOT NULL DEFAULT 21,
	`discount`			FLOAT			DEFAULT 0,
	`total`				DOUBLE			NOT NULL,
	`client_id`			INT UNSIGNED	NOT NULL,

	CONSTRAINT `PK_invoices` 			PRIMARY KEY (`id`),
	CONSTRAINT `FK_invoices_clients`	FOREIGN KEY (`client_id`)
		REFERENCES `clients` (`id`)
);

-- --------------------------
-- INVOICES DETAILS TABLE
-- --------------------------
DROP TABLE IF EXISTS `invoices_details`;
CREATE TABLE `invoices_details` (
	`id`				INT UNSIGNED		NOT NULL AUTO_INCREMENT,
	`line_number`		TINYINT UNSIGNED	NOT NULL,
	`total_articles`	TINYINT				NOT NULL,
	`line_price`		DOUBLE				NOT NULL,
	`article_id`		INT UNSIGNED		NOT NULL,
	`invoice_id`		INT	UNSIGNED		NOT NULL,

	CONSTRAINT `PK_invoices_details`				PRIMARY KEY (`id`),
	CONSTRAINT `UK_invoices_details_invoice_line`	UNIQUE (`invoice_id`, `line_number`),
	CONSTRAINT `FK_invoices_details_invoices`		FOREIGN KEY (`invoice_id`)
		REFERENCES `invoices` (`id`)
			ON DELETE CASCADE,
	CONSTRAINT `FK_invoices_details_articles`		FOREIGN KEY (`article_id`)
		REFERENCES `articles` (`id`)
);