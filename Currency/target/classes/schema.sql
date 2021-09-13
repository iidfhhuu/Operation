CREATE TABLE currency (
	id int AUTO_INCREMENT,
	code varchar(100) NULL,
	symbol varchar(100)  NULL,
	rate varchar(100) NULL,
	description varchar(100) NULL,
	rate_float int NULL,
	update_date datetime NULL,
	CONSTRAINT Engineer_Id UNIQUE (code)
);