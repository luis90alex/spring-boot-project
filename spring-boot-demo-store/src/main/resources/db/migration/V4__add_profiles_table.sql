-- V4__add_profiles_table.sql
-- Create profiles table

USE `store`;

-- Table `store`.`profiles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`profiles` (
  `id` BIGINT NOT NULL,
  `bio` TEXT NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `loyalty_points` INT UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_profiles_users`
    FOREIGN KEY (`id`)
    REFERENCES `store`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
