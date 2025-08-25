-- V3__move_state_from_users_to_addresses.sql
-- Remove state from users and add it to addresses (as NOT NULL)

USE `store`;

-- drop state column from users (assumes column exists)
ALTER TABLE `store`.`users`
  DROP COLUMN `state`;

-- add state column to addresses as NOT NULL
ALTER TABLE `store`.`addresses`
  ADD COLUMN `state` VARCHAR(255) NOT NULL;
