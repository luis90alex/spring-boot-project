-- V2__add_state_column.sql
-- Add column `state` to users

USE `store`;

ALTER TABLE `store`.`users`
  ADD COLUMN `state` VARCHAR(255);
