/*
MariaDB Backup
Database: nova_messenger
Backup Time: 2024-09-01 05:12:25
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `nova_messenger`.`chats`;
DROP TABLE IF EXISTS `nova_messenger`.`chats_seq`;
DROP TABLE IF EXISTS `nova_messenger`.`group_memberships`;
DROP TABLE IF EXISTS `nova_messenger`.`groups`;
DROP TABLE IF EXISTS `nova_messenger`.`messages`;
DROP TABLE IF EXISTS `nova_messenger`.`messages_seq`;
DROP TABLE IF EXISTS `nova_messenger`.`users`;
DROP TABLE IF EXISTS `nova_messenger`.`users_seq`;
CREATE TABLE `chats` (
  `chat_id` int(11) NOT NULL,
  `user_one` int(11) DEFAULT NULL,
  `user_two` int(11) DEFAULT NULL,
  `last_message` varchar(255) DEFAULT NULL,
  `slot_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `chats_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;
CREATE TABLE `group_memberships` (
  `membership_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `joined_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`membership_id`),
  UNIQUE KEY `group_id` (`group_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `groups` (
  `group_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `messages` (
  `message_id` int(11) NOT NULL,
  `message_content` varchar(255) DEFAULT NULL,
  `sent_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` varchar(255) DEFAULT NULL,
  `chat_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `idx_messages_timestamp` (`sent_at`),
  KEY `chat_const` (`chat_id`),
  CONSTRAINT `chat_const` FOREIGN KEY (`chat_id`) REFERENCES `chats` (`chat_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `messages_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `users_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;
BEGIN;
LOCK TABLES `nova_messenger`.`chats` WRITE;
DELETE FROM `nova_messenger`.`chats`;
INSERT INTO `nova_messenger`.`chats` (`chat_id`,`user_one`,`user_two`,`last_message`,`slot_name`) VALUES (1, 1, 2, 'asdasdda', 'user and user2');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `nova_messenger`.`chats_seq` WRITE;
DELETE FROM `nova_messenger`.`chats_seq`;
INSERT INTO `nova_messenger`.`chats_seq` (`next_not_cached_value`,`minimum_value`,`maximum_value`,`start_value`,`increment`,`cache_size`,`cycle_option`,`cycle_count`) VALUES (1, 1, 9223372036854775806, 1, 50, 0, 0, 0);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `nova_messenger`.`group_memberships` WRITE;
DELETE FROM `nova_messenger`.`group_memberships`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `nova_messenger`.`groups` WRITE;
DELETE FROM `nova_messenger`.`groups`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `nova_messenger`.`messages` WRITE;
DELETE FROM `nova_messenger`.`messages`;
INSERT INTO `nova_messenger`.`messages` (`message_id`,`message_content`,`sent_at`,`status`,`chat_id`,`sender_id`) VALUES (1, 'sdfsd', '2024-08-29 07:39:19', 'delivered', 1, 1),(553, 'a', '2024-08-30 09:42:03', 'delivered', 1, 2),(554, ' i', '2024-08-30 09:42:07', 'delivered', 1, 2),(555, 's', '2024-08-30 09:57:55', 'delivered', 1, 2),(602, 'dddd', '2024-08-30 10:02:30', 'delivered', 1, 2),(603, 'iiyuu', '2024-08-30 10:09:46', 'delivered', 1, 1),(604, 'asdasdasd', '2024-08-30 10:11:05', 'delivered', 1, 2),(605, 'fsafasfaf', '2024-08-30 10:11:12', 'delivered', 1, 2),(606, 'fdafafafsafas', '2024-08-30 10:11:18', 'delivered', 1, 2),(607, 'dsadasfasfasf fasfasfafs', '2024-08-30 10:11:22', 'delivered', 1, 2),(608, '👉👌', '2024-08-30 10:11:36', 'delivered', 1, 2),(609, 'see the s is d', '2024-08-30 10:11:58', 'delivered', 1, 1),(610, 'asdasddsa', '2024-08-30 10:12:06', 'delivered', 1, 2),(611, 'dasdasd dasdasdsd', '2024-08-30 10:15:03', 'delivered', 1, 2),(612, 'ccfddgeg', '2024-08-30 10:15:08', 'delivered', 1, 1),(613, 'userTwo and I are going to be in the car in a few minutes and I will be there in a few minutes and I will be there ', '2024-08-30 10:15:15', 'delivered', 1, 1),(614, 'ok', '2024-08-30 10:15:28', 'delivered', 1, 2),(615, 'suck me cocc', '2024-08-30 10:17:58', 'delivered', 1, 1),(616, 'dasdas', '2024-08-30 10:19:37', 'delivered', 1, 2),(617, 'asdasd', '2024-08-30 10:25:02', 'delivered', 1, 2),(618, 'asfasfasfasf', '2024-08-30 10:25:07', 'delivered', 1, 2),(619, 'ss', '2024-08-30 10:25:14', 'delivered', 1, 1),(620, 'asda', '2024-08-30 10:25:49', 'delivered', 1, 2),(621, '2', '2024-08-30 10:27:13', 'delivered', 1, 2),(622, 'asdasd', '2024-08-30 10:27:36', 'delivered', 1, 2),(623, 'ddd', '2024-08-30 10:28:11', 'delivered', 1, 2),(624, 'sadasd', '2024-08-30 10:28:27', 'delivered', 1, 2),(625, 'asdasd', '2024-08-30 10:28:46', 'delivered', 1, 2),(626, ' rrghoo', '2024-08-30 10:28:55', 'delivered', 1, 1),(627, 'you', '2024-08-30 10:30:27', 'delivered', 1, 1),(628, 'jikij', '2024-08-30 10:30:38', 'delivered', 1, 1),(629, 'asdasd', '2024-08-30 10:30:41', 'delivered', 1, 2),(630, 'ee', '2024-08-30 10:30:58', 'delivered', 1, 1),(631, 'asdasdasd', '2024-08-30 10:31:09', 'delivered', 1, 2),(632, 'asdasd', '2024-08-30 10:35:33', 'delivered', 1, 2),(633, 'asd', '2024-08-30 10:37:11', 'delivered', 1, 2),(634, 'Provider.of<ChatProvider>(context, listen: false)                .onReceiveNewMessage(message);', '2024-08-30 10:44:06', 'delivered', 1, 1),(635, 'asdsad', '2024-08-30 10:44:10', 'delivered', 1, 2),(636, 'dasd', '2024-08-30 10:44:32', 'delivered', 1, 2),(637, 'hello world', '2024-08-30 10:45:38', 'delivered', 1, 2),(638, 'asdasd', '2024-08-30 10:45:48', 'delivered', 1, 2),(639, 'asdasdasd', '2024-08-30 10:46:18', 'delivered', 1, 2),(640, 'asdasddsa', '2024-08-30 10:47:21', 'delivered', 1, 2),(641, 'wwwww', '2024-08-30 10:47:26', 'delivered', 1, 2),(642, 'dd', '2024-08-30 10:52:03', 'delivered', 1, 2),(643, 'asdasdda', '2024-08-30 10:56:16', 'delivered', 1, 2);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `nova_messenger`.`messages_seq` WRITE;
DELETE FROM `nova_messenger`.`messages_seq`;
INSERT INTO `nova_messenger`.`messages_seq` (`next_not_cached_value`,`minimum_value`,`maximum_value`,`start_value`,`increment`,`cache_size`,`cycle_option`,`cycle_count`) VALUES (701, 1, 9223372036854775806, 1, 50, 0, 0, 0);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `nova_messenger`.`users` WRITE;
DELETE FROM `nova_messenger`.`users`;
INSERT INTO `nova_messenger`.`users` (`user_id`,`username`,`password`) VALUES (1, 'user', 'A123123a'),(2, 'user2', 'A123123a');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `nova_messenger`.`users_seq` WRITE;
DELETE FROM `nova_messenger`.`users_seq`;
INSERT INTO `nova_messenger`.`users_seq` (`next_not_cached_value`,`minimum_value`,`maximum_value`,`start_value`,`increment`,`cache_size`,`cycle_option`,`cycle_count`) VALUES (1, 1, 9223372036854775806, 1, 50, 0, 0, 0);
UNLOCK TABLES;
COMMIT;
