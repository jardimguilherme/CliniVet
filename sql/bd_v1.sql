-- MySQL  generated by MySQL Workbench
-- Sat Nov 30 15:44:01 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS mydb DEFAULT CHARACTER SET utf8;
USE mydb;

-- -----------------------------------------------------
-- Table `mydb`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS addresses (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  street VARCHAR(255) NULL,
  number INT NULL,
  complement VARCHAR(255) NULL,
  zipcode VARCHAR(8) NULL,
  city VARCHAR(255) NULL,
  state VARCHAR(2) NULL,
  );


-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS customer (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  cpf VARCHAR(11) NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NULL,
  phone VARCHAR(11) NULL,
  mobile VARCHAR(11) NULL,
  address_id INT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table `mydb`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS employees (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  register INT NOT NULL,
  crmv VARCHAR(45) NULL,
  login VARCHAR(31) NOT NULL,
  password VARCHAR(31) NOT NULL,
  admssion DATETIME NOT NULL,
  admin TINYINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NULL,
  birth_date DATETIME NULL,
  phone VARCHAR(11) NULL,
  mobile VARCHAR(11) NULL,
  address_id INT NOT NULL,
  CONSTRAINT address
    FOREIGN KEY (address_id)
    REFERENCES addresses (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `mydb`.`procedures`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS procedures (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NULL,
  price DECIMAL(10,2) NULL);


-- -----------------------------------------------------
-- Table `mydb`.`species`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS species (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  );


-- -----------------------------------------------------
-- Table `mydb`.`breeds`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS breeds (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  species_id INT NOT NULL,
  CONSTRAINT species
    FOREIGN KEY (id)
    REFERENCES species (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);


-- -----------------------------------------------------
-- Table `mydb`.`animals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS animals (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  gender VARCHAR(1) NOT NULL,
  weight DECIMAL(3,2) NULL,
  birthdate DATETIME NULL,
  color VARCHAR(45) NULL,
  breed_id INT NOT NULL,
  customer_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT breed
    FOREIGN KEY (id)
    REFERENCES breeds (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT customer
    FOREIGN KEY (id)
    REFERENCES customer (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`payments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS payments (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  type VARCHAR(45) NULL,
);


-- -----------------------------------------------------
-- Table `mydb`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS orders (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  date DATETIME NULL,
  total DECIMAL(10,2) NULL,
  payment_id INT NOT NULL,
  customer_id INT NULL,
  PRIMARY KEY (id),
  INDEX payment_idx (payment_id ASC) VISIBLE,
  INDEX customer_idx (customer_id ASC) VISIBLE,
  CONSTRAINT payment
    FOREIGN KEY (payment_id)
    REFERENCES payments (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT customer
    FOREIGN KEY (customer_id)
    REFERENCES customer (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`procedure_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.procedure_schedule (
  id INT NOT NULL,
  date DATETIME NOT NULL,
  performed TINYINT NULL,
  animal_id INT NOT NULL,
  procedure_id INT NOT NULL,
  order_id INT NULL,
  PRIMARY KEY (id),
  INDEX animal_idx (animal_id ASC) VISIBLE,
  INDEX order_idx (order_id ASC) VISIBLE,
  INDEX procedure_idx (procedure_id ASC) VISIBLE,
  CONSTRAINT animal
    FOREIGN KEY (animal_id)
    REFERENCES mydb.animals (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT procedure
    FOREIGN KEY (procedure_id)
    REFERENCES mydb.procedures (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT order
    FOREIGN KEY (order_id)
    REFERENCES mydb.orders (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.appointments (
  id INT NOT NULL AUTO_INCREMENT,
  type VARCHAR(45) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
  UNIQUE INDEX name_UNIQUE (type ASC) VISIBLE,
  UNIQUE INDEX price_UNIQUE (price ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`order_procedure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.order_procedure (
  order_id INT NOT NULL,
  procedure_id INT NOT NULL,
  PRIMARY KEY (order_id, procedure_id),
  INDEX procedure_idx (procedure_id ASC) VISIBLE,
  CONSTRAINT order
    FOREIGN KEY (order_id)
    REFERENCES mydb.orders (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT procedure
    FOREIGN KEY (procedure_id)
    REFERENCES mydb.procedures (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.products (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`order_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.order_product (
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  PRIMARY KEY (order_id, product_id),
  INDEX product_idx (product_id ASC) VISIBLE,
  CONSTRAINT order
    FOREIGN KEY (order_id)
    REFERENCES mydb.orders (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT product
    FOREIGN KEY (product_id)
    REFERENCES mydb.products (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- Table `mydb`.`order_appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.order_appointment (
  order_id INT NOT NULL,
  appointment_id INT NOT NULL,
  PRIMARY KEY (appointment_id, order_id),
  INDEX appointment_idx (appointment_id ASC) VISIBLE,
  CONSTRAINT order
    FOREIGN KEY (order_id)
    REFERENCES mydb.orders (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT appointment
    FOREIGN KEY (appointment_id)
    REFERENCES mydb.appointments (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`appointment_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.appointment_schedule (
  id INT NOT NULL AUTO_INCREMENT,
  date DATETIME NOT NULL,
  sympthoms VARCHAR(255) NULL,
  performed TINYINT NULL,
  animal_id INT NOT NULL,
  appointment_id INT NOT NULL,
  order_id INT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
  INDEX animal_idx (animal_id ASC) VISIBLE,
  INDEX appointment_idx (appointment_id ASC) VISIBLE,
  INDEX order_idx (order_id ASC) VISIBLE,
  CONSTRAINT animal
    FOREIGN KEY (animal_id)
    REFERENCES mydb.animals (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT appointment
    FOREIGN KEY (appointment_id)
    REFERENCES mydb.appointments (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT order
    FOREIGN KEY (order_id)
    REFERENCES mydb.orders (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;