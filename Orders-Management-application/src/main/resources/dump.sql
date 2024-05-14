CREATE DATABASE order_management_db;

CREATE USER 'jawad' IDENTIFIED BY 'password';
GRANT ALL ON order_management_db.* TO 'jawad';

CREATE TABLE `client` (
        `id` bigint AUTO_INCREMENT PRIMARY KEY,
        `name` varchar(255) NOT NULL,
        `age` int
);

CREATE TABLE `order` (
        `id` bigint AUTO_INCREMENT PRIMARY KEY,
        `client_id` bigint NOT NULL,
        `product_id` bigint NOT NULL,
        `quantity` int NOT NULL,
        `order_date` datetime NOT NULL,
        FOREIGN KEY (`client_id`) REFERENCES `client`(`id`),
        FOREIGN KEY (`product_id`) REFERENCES `product`(`id`)
);


CREATE TABLE `product` (
        `id` bigint AUTO_INCREMENT PRIMARY KEY,
        `name` varchar(50) NOT NULL,
        `description` varchar(1024) NOT NULL,
        `price` double NOT NULL,
        `stock_quantity` INT NOT NULL DEFAULT 0
);

