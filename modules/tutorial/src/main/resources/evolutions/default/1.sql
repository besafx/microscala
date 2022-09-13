-- fleet_system schema

-- !Ups

CREATE TABLE IF NOT EXISTS `trip` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) NOT NULL,
  `driver_uuid` varchar(64) DEFAULT NULL,
  `pool_uuid` varchar(64) DEFAULT NULL,
  `trip_short_code` varchar(64) NOT NULL ,
  `payment_type` varchar(64) NOT NULL,
  `status` varchar(64) NOT NULL,
  `total_amount` decimal DEFAULT NULL,
  `delivery_duration` int(11) DEFAULT NULL,
  `cooking_duration` int(11) DEFAULT NULL,
  `estimated_delivery_duration` int(11) DEFAULT NULL,
  `estimated_delivery_distance` int(11) DEFAULT NULL,
  `estimated_moving_duration` int(11) DEFAULT NULL,
  `estimated_moving_distance` int(11) DEFAULT NULL,
  `stacked` tinyint(1) NOT NULL DEFAULT 0,
  `non_partner` tinyint(1) NOT NULL DEFAULT 0,
  `bonus_percentage` float NOT NULL DEFAULT 1,
  `receipt_photo_uuid` varchar(64) DEFAULT NULL,
  `expected_dispatching_time` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE current_timestamp(),
  UNIQUE(`id`),
  PRIMARY KEY (`uuid`),
  KEY `key_trip_stacked_trips` (`stacked`),
  KEY `key_trip_pool_uuid` (`pool_uuid`),
  KEY `key_trip_status` (`status`),
  KEY `key_trip_driver_uuid` (`driver_uuid`),
  KEY `key_trip_non_partner` (`non_partner`),
  KEY `key_trip_bonus_percentage` (`bonus_percentage`)
) ENGINE=InnoDB AUTO_INCREMENT=459926 DEFAULT CHARSET=utf8;