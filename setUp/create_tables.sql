create schema demo;
use demo;


CREATE TABLE IF NOT EXISTS `jobs`(
   `id` bigint(20) NOT NULL,
   `status` VARCHAR(64) NOT NULL,
   `create_time` bigint(20) NOT NULL,
   `client_id` bigint(20) NOT NULL,
   PRIMARY KEY ( `id` ),
   FOREIGN KEY (`client_id`) REFERENCES `clients`(`id`)
);


CREATE TABLE `notes` (
    `id` bigint(20) NOT NULL,
    `job_id` bigint(20) NOT NULL,
    `content` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`job_id`) REFERENCES `jobs`(`id`)
);


CREATE TABLE IF NOT EXISTS `clients` (
    `id` bigint(20) NOT NULL,
    `name` varchar(255) NOT NULL,
    `phone_num` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);