CREATE DATABASE order_management_db;

-- Create the user and grant privileges
CREATE USER 'jawad' IDENTIFIED BY 'password';
GRANT ALL ON order_management_db.* TO 'jawad';

-- Create the Client table
CREATE TABLE `client` (
        `id` bigint AUTO_INCREMENT PRIMARY KEY,
        `name` varchar(255) NOT NULL,
        `age` int
);

-- Create the Order table
CREATE TABLE `order` (
        `id` bigint AUTO_INCREMENT PRIMARY KEY,
        `total_price` double NOT NULL,
        `client_id` bigint,
        FOREIGN KEY (`client_id`) REFERENCES `client`(`id`)
);

-- Create the Product table
CREATE TABLE `product` (
        `id` bigint AUTO_INCREMENT PRIMARY KEY,
        `name` varchar(50) NOT NULL,
        `description` varchar(1024) NOT NULL,
        `price` double NOT NULL,
        `stock_quantity` INT NOT NULL DEFAULT 0
);

