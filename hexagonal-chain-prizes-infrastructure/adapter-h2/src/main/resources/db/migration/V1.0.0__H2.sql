create table brands (
    brand_id INTEGER not null,
    code CHAR(2),
    description CHAR(255),
    distribution_center INTEGER,
	PRIMARY KEY (brand_id)
);

create table prices (
    price_id INTEGER not null,
	brand_id INTEGER not null,
	start_date TimeStamp,
	end_date TimeStamp,
	price_list INTEGER not null,
	product_id INTEGER not null,
	priority INTEGER not null,
    price NUMERIC(20, 2),
    currency CHAR(5) not null,
	PRIMARY KEY (price_id),
	FOREIGN KEY (brand_id) REFERENCES brands(brand_id)
);

INSERT INTO
	brands(brand_id, code, description, distribution_center)
VALUES
	(1,'ZA', 'ZARA', 1);

INSERT INTO
	brands(brand_id, code, description, distribution_center)
VALUES
	(2,'BK', 'BERSHKA', 2);

INSERT INTO
	brands(brand_id, code, description, distribution_center)
VALUES
	(3,'ST', 'STRADIVARIUS', 3);

INSERT INTO
	brands(brand_id, code, description, distribution_center)
VALUES
	(4,'PB', 'PULL & BEAR', 4);

INSERT INTO
	brands(brand_id, code, description, distribution_center)
VALUES
	(5,'OY', 'OYSHO', 5);

INSERT INTO
	brands(brand_id, code, description, distribution_center)
VALUES
	(6,'MD', 'MASSIMO DUTTY', 6);

INSERT INTO
	brands(brand_id, code, description, distribution_center)
VALUES
	(7,'LF', 'LEFTIES', 7);

INSERT INTO
	brands(brand_id, code, description, distribution_center)
VALUES
	(8,'TP', 'TEMPE', 9);

INSERT INTO
	brands(brand_id, code, description, distribution_center)
VALUES
	(9,'ZH', 'ZARA HOME', 9);


INSERT INTO
	prices(price_id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES
	(1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR');

INSERT INTO
	prices(price_id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES
	(2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR');

INSERT INTO
	prices(price_id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES
	(3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR');

INSERT INTO
	prices(price_id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES
	(4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');

