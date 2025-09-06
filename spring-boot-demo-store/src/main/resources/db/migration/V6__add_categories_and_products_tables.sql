-- V6__add_categories_products_table.sql
-- Create categories and products tables (one-to-many)


USE `store`;

-- -----------------------------------------------------
-- categories table
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`categories` (
  `id` TINYINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- products table
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `category_id` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_categories_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_products_categories`
    FOREIGN KEY (`category_id`)
    REFERENCES `store`.`categories` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
