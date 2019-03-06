CREATE DATABASE `notification_manager`;
USE `notification_manager`;

CREATE TABLE IF NOT EXISTS `ussd_session` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`session_id` VARCHAR(20) NOT NULL,
	`masked_msisdn` VARCHAR(200) NOT NULL,
	`ussd_operation` VARCHAR(20) NOT NULL,
	`current_menu` VARCHAR(30) NOT NULL,
	`current_action` VARCHAR(30) NOT NULL,
	`last_updated_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`status` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `msisdn` (`session_id`),
	INDEX ussd_session_index (session_id, masked_msisdn)
);