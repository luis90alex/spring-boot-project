-- V4__add_profiles_table.sql
-- Create profiles table

USE `store`;

CREATE TABLE IF NOT EXISTS `store`.`profiles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `bio` TEXT NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `loyalty_points` INT UNSIGNED NOT NULL DEFAULT 0,
  `users_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_profiles_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_profiles_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `store`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;
