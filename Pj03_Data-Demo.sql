-- M14 DUAL
-- UF2 Projectes
-- Projecte 03: Facturacio
-- Authors: Gerard Lopez & Jordi Hernandez

USE `Pj03_Gerard-Jordi`;

-- --------------------------
-- ARTICLES
-- --------------------------
DELETE FROM `articles`;
INSERT INTO `articles` (`id`, `code`, `name`, `price`)
	VALUES	(1, 'A100' ,'Laptop', 1247.35),
			(2, 'B100' ,'Keyboard', 41.04),
			(3, 'B300' ,'Mouse', 34.78),
			(4, 'C100' ,'Mouse Pad', 9.44),
			(5, 'D100' ,'Monitor', 132.89),
			(6, 'E100' ,'CPU', 199.99),
			(7, 'Z100' ,'PC', 1355.67)
;

-- --------------------------
-- CLIENTS
-- --------------------------
DELETE FROM `clients`;
INSERT INTO `clients` (`id`, `nif`, `name`, `lastname`, `address`, `town`)
	VALUES	(1, '98987345J', 'Jordi', 'Hernandez', 'C/ Laravel', 'Lloret de Mar'),
			(2, '87244508H', 'Gerard', 'Lopez', 'C/ Javascript', 'Pineda de Mar'),
			(3, '87295656W', 'Guillem', 'Fors', 'C/ Java', 'Tordera'),
			(4, '22948575N', 'Ariel', 'Zambrano', 'C/ Tailwind', 'Blanes')
;

-- --------------------------
-- INVOICES
-- --------------------------
DELETE FROM `invoices`;
INSERT INTO `invoices` (`id`, `date`, `paid`, `taxable_base`, `iva`, `iva_import`, `discount`, `discount_import`, `total`, `client_id`)
	VALUES	(1, '2021-01-14', 1, 1282.03, 45.67, 0.0, 0.0, 0.0, 1298.35, 1),
			(2, '2021-01-30', 0, 2955.35, 99.34, 0.0, 110.00, 0.0, 1867.34, 3),
			(3, '2021-02-01', 1, 402.71, 76.41, 0.0, 31.45, 0.0, 458.44, 2)
;

-- --------------------------
-- INVOICES DETAILS
-- --------------------------
DELETE FROM `invoices_details`;
INSERT INTO `invoices_details` (`id`, `line_number`, `total_articles`, `line_price`, `article_id`, `invoice_id`)
	VALUES	(1, 1, 1, 34.56, 3, 1),
			(2, 2, 1, 1247.57, 1, 1),
			(3, 1, 2, 2955.35, 6, 2),
			(4, 1, 2, 398.31, 5, 3),
			(5, 2, 1, 9.44, 4, 3)
;