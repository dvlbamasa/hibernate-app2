DROP TABLE address CASCADE;
DROP TABLE contact_information CASCADE;
DROP TABLE person CASCADE;
DROP TABLE role CASCADE;
DROP TABLE person_role CASCADE;


CREATE TABLE address (
	id INT PRIMARY KEY NOT NULL,
	street_no INT NOT NULL,
	barangay CHAR(20) NOT NULL,
	municipality CHAR(20) NOT NULL,
	zip_code INT NOT NULL
);

CREATE TABLE contact_information (
	id INT PRIMARY KEY NOT NULL,
	landline BIGINT NOT NULL,
	mobile_number BIGINT NOT NULL,
	email CHAR(30) NOT NULL
);

CREATE TABLE person (
	id INT PRIMARY KEY NOT NULL,
	first_name CHAR(20) NOT NULL,
	middle_name CHAR(20) NOT NULL,
	last_name CHAR(20) NOT NULL,
	address INT NOT NULL,
	birthday DATE NOT NULL,
	gwa FLOAT NOT NULL,
	date_hired DATE NOT NULL,
	currently_employed BIT NOT NULL,
	contact_information INT NOT NULL,
	roles INT NOT NULL
);


CREATE TABLE role (
	id INT PRIMARY KEY NOT NULL,
	name CHAR(10) NOT NULL
);

CREATE TABLE person_role (
	person_id INT NOT NULL,
	role_id INT NOT NULL,
	PRIMARY KEY (person_id, role_id)
);