-- M14 DUAL
-- UF2 Projectes
-- Projecte 03: Facturacio
-- Authors: Gerard Lopez & Jordi Hernandez

USE `Pj03_Gerard-Jordi`;

-- --------------------------
-- ARTICLES
-- --------------------------
DELETE FROM `articles`;
INSERT INTO `articles` (`id`, `name`, `price`)
	VALUES	(1, 'Laptop', 1247.35),
			(2, 'Keyboard', 41.04),
			(3, 'Mouse', 34.78),
			(4, 'Mouse Pad', 9.44),
			(5, 'Monitor', 132.89),
			(6, 'CPU', 199.99),
			(7, 'PC', 1355.67)
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
INSERT INTO `invoices` (`id`, `date`, `paid`, `taxable_base`, `iva`, `discount`, `total`, `client_id`)
	VALUES	(1, '2021-01-14', 1, 1282.03, 45.67, 0.0, 1298.35, 1),
			(2, '2021-01-30', 0, 2955.35, 99.34, 110.00, 1867.34, 3),
			(3, '2021-02-01', 1, 402.71, 76.41, 31.45, 458.44, 2)
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