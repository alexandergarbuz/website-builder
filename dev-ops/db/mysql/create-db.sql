CREATE DATABASE  IF NOT EXISTS website_builder_db;
USE website_builder_db;

ALTER DATABASE website_builder_db 
DEFAULT CHARACTER SET utf8 
DEFAULT COLLATE utf8_general_ci;

CREATE USER IF NOT EXISTS 'website_builder_user'@'%' IDENTIFIED BY 'website_builder_password';

GRANT ALL PRIVILEGES ON website_builder_db.* TO 'website_builder_user'@'%';

CREATE TABLE IF NOT EXISTS User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);
