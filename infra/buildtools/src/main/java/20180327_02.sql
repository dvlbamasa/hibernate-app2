DROP TABLE address CASCADE;
DROP TABLE contact_information CASCADE;
DROP TABLE person CASCADE;
DROP TABLE role CASCADE;
DROP TABLE person_role CASCADE;


CREATE TABLE address (
	id BIGINT PRIMARY KEY NOT NULL,
	street_no INT NOT NULL,
	barangay VARCHAR(20) NOT NULL,
	municipality VARCHAR(20) NOT NULL,
	zip_code INT NOT NULL
);

CREATE TABLE contact_information (
	id BIGINT PRIMARY KEY NOT NULL,
	landline VARCHAR(20) NOT NULL,
	mobile_number VARCHAR(20) NOT NULL,
	email VARCHAR(30) NOT NULL
);

CREATE TABLE person (
	id BIGINT PRIMARY KEY NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	middle_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	gender	VARCHAR(10) NOT NULL,
	address BIGINT NOT NULL,
	birthday DATE NOT NULL,
	gwa FLOAT NOT NULL,
	date_hired DATE NOT NULL,
	currently_employed CHAR(1) NOT NULL
);


CREATE TABLE role (
	id BIGINT PRIMARY KEY NOT NULL,
	name VARCHAR(20) NOT NULL
);

CREATE TABLE person_role (
	person_id BIGINT NOT NULL,
	role_id BIGINT NOT NULL,
	PRIMARY KEY (person_id, role_id)
);

ALTER TABLE contact_information 
       add CONSTRAINT FKauxbhl67hx6ftcqayqtu8xmes 
       FOREIGN KEY (id) 
       REFERENCES person;
    
ALTER TABLE person 
       add CONSTRAINT FKbs1pxpi8sc2h5t4bc5uov92p 
       FOREIGN KEY (address) 
       REFERENCES address;

ALTER TABLE person_role 
       add CONSTRAINT FKs7asxi8amiwjjq1sonlc4rihn 
       FOREIGN KEY (role_id) 
       REFERENCES role
       ON DELETE CASCADE;


ALTER TABLE person_role 
       add CONSTRAINT FKhyx1efsls0f00lxs6xd4w2b3j 
       FOREIGN KEY (person_id) 
       REFERENCES person;

INSERT INTO role VALUES (1, 'SOFTWARE_DEVELOPER');

INSERT INTO role VALUES (2, 'QA_ENGINEER');

INSERT INTO role VALUES (3, 'BUSINESS_ANALYST');

INSERT INTO role VALUES (4, 'HR_OFFICER');