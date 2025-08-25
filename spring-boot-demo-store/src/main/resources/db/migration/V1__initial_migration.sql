-- V1__initial_migration.sql
-- Create schema (if not exists) and only create tables users and addresses

CREATE SCHEMA IF NOT EXISTS `store` DEFAULT CHARACTER SET utf8;
USE `store`;

-- Table `store`.`users`
CREATE TABLE IF NOT EXISTS `store`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- Table `store`.`addresses`
CREATE TABLE IF NOT EXISTS `store`.`addresses` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  `zip` VARCHAR(255) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_addresses_users_idx` (`user_id` ASC),
  CONSTRAINT `fk_addresses_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `store`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;
