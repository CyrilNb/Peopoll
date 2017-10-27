CREATE SCHEMA IF NOT EXISTS peopoll AUTHORIZATION root;

DROP TABLE peopoll.POLLS, peopoll.CHOICES,peopoll.COMMENTS, peopoll.CONTRIBUTIONS;

CREATE TABLE IF NOT EXISTS peopoll.POLLS (
  id_poll INT AUTO_INCREMENT CONSTRAINT pk_polls PRIMARY KEY,
  manager_code VARCHAR2(10) NOT NULL CONSTRAINT constraint_uniq_manager_code UNIQUE,
  location VARCHAR2(50),
  description VARCHAR2(250),
  title VARCHAR2(20) CONSTRAINT constraint_notnull_title_poll NOT NULL,
  mail_creator VARCHAR2(50) CONSTRAINT constraint_notnull_mail_creator NOT NULL,
  name_creator VARCHAR2(25),
  is_locked BIT CONSTRAINT constraint_notnull_is_locked NOT NULL,
  nb_max_contributor INT,
  id_final_choice INT,

);

CREATE TABLE IF NOT EXISTS peopoll.CHOICES(
  id_choice INT AUTO_INCREMENT CONSTRAINT pk_choices PRIMARY KEY,
  date_choice DATE CONSTRAINT constraint_notnull_date_choice NOT NULL,
  starting_time INT,
  ending_time INT,
  id_poll INT,
  CONSTRAINT fk1choice FOREIGN KEY (id_poll) REFERENCES POLLS
);

CREATE TABLE IF NOT EXISTS peopoll.COMMENTS(
  id_comment INT AUTO_INCREMENT CONSTRAINT pk_comments PRIMARY KEY,
  name_author VARCHAR2(30) CONSTRAINT constraint_notnull_name_author NOT NULL,
  content VARCHAR2(300) CONSTRAINT constraint_notnull_content NOT NULL,
  date_comment DATE CONSTRAINT constraint_notnull_date_comment NOT NULL,
  id_poll INT,
  CONSTRAINT fk1comment FOREIGN KEY (id_poll) REFERENCES POLLS
);

CREATE TABLE IF NOT EXISTS peopoll.CONTRIBUTIONS(
  id_contribution INT AUTO_INCREMENT CONSTRAINT pk_contributions PRIMARY KEY,
  name_contributor VARCHAR2(30) CONSTRAINT constraint_notnull_name_contributor NOT NULL,
  idP INT,
  idC INT,
  CONSTRAINT fk1contribution FOREIGN KEY (idP) REFERENCES POLLS,
  CONSTRAINT fk2contribution FOREIGN KEY (idC) REFERENCES CHOICES
);
