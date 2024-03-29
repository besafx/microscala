-- !Ups
CREATE TABLE IF NOT EXISTS trip_violation_checkpoint
(
    `uuid`           varchar(64)                                                             NOT NULL,
    `check_date`     timestamp                                                               NOT NULL DEFAULT '0000-00-00 00:00:00',
    `initial_eta`    timestamp                                                               NOT NULL DEFAULT '0000-00-00 00:00:00',
    `violation_type` ENUM ('FM_PREVENTION', 'FM_DETECTION', 'LM_PREVENTION', 'LM_DETECTION') NOT NULL,
    PRIMARY KEY (`uuid`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;