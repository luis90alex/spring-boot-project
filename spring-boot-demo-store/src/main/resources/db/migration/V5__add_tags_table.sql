-- V5__add_tags_table.sql
-- Create tags and user_tags tables (many-to-many)

USE `store`;

-- tags table
CREATE TABLE IF NOT EXISTS `store`.`tags` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- user_tags join table
CREATE TABLE IF NOT EXISTS `store`.`user_tags` (
  `user_id` BIGINT NOT NULL,
  `tag_id` INT NOT NULL,
  INDEX `fk_user_tags_users1_idx` (`user_id` ASC),
  INDEX `fk_user_tags_tags1_idx` (`tag_id` ASC),
  PRIMARY KEY (`user_id`, `tag_id`),
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `store`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag_id`
    FOREIGN KEY (`tag_id`)
    REFERENCES `store`.`tags` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE = InnoDB;
