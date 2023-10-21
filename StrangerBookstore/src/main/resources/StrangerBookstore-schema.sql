create database StrangerBookstore;
use StrangerBookstore;
-- Tạo bảng Customer
CREATE TABLE `customer` (
    `customer_id` INT AUTO_INCREMENT PRIMARY KEY,
    `customer_fullName` VARCHAR(255),
    `password` VARCHAR(255),
    `phone_number` VARCHAR(15),
    `email` VARCHAR(255),
    `birthday` DATE,
    `status` VARCHAR(15),
    `gender` VARCHAR(15),
    `picture` VARCHAR(255), -- Để hình ảnh
    `role_id` INT,
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng roles
CREATE TABLE `roles` (
    `role_id` INT AUTO_INCREMENT PRIMARY KEY,
    `role_name` VARCHAR(255),
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng product
CREATE TABLE `product` (
    `product_id` INT AUTO_INCREMENT PRIMARY KEY,
    `product_name` VARCHAR(255),
    `author` VARCHAR(255),
    `publisher` VARCHAR(255),
    `language` VARCHAR(50),
    `condition` VARCHAR(50),
    `quantity_in_stock` INT,
    `isbn` VARCHAR(255),
    `description` TEXT,
    `price` DECIMAL(10, 2),
    `category_id` INT,
    `picture_id` INT,
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng categories
CREATE TABLE `categories` (
    `category_id` INT AUTO_INCREMENT PRIMARY KEY,
    `category_name` VARCHAR(255),
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng address
CREATE TABLE `address` (
    `address_id` INT AUTO_INCREMENT PRIMARY KEY,
    `recipient_fullName` VARCHAR(255),
    `recipient_phoneNumber` VARCHAR(15),
    `address_detail` TEXT,
    `address_type` VARCHAR(255),
    `customer_id` INT,
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng news
CREATE TABLE `news` (
    `news_id` INT AUTO_INCREMENT PRIMARY KEY,
    `news_title` VARCHAR(255),
    `news_content` TEXT,
    `news_picture` BLOB, -- Để hình ảnh
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng contact_us
CREATE TABLE `contact_us` (
    `contact_id` INT AUTO_INCREMENT PRIMARY KEY,
    `full_name` VARCHAR(255),
    `phone_number` VARCHAR(15),
    `email` VARCHAR(255),
    `subject` VARCHAR(255),
    `message` TEXT,
    `status` VARCHAR(55),
    `reate_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng `order`
CREATE TABLE `order` (
    `order_id` INT AUTO_INCREMENT PRIMARY KEY,
    `customer_id` INT,
    `status_id` INT,
    `payment_method` VARCHAR(50),
    `total_amount` DECIMAL(10, 2),
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng status_orders
CREATE TABLE `status_orders` (
    `status_id` INT AUTO_INCREMENT PRIMARY KEY,
    `status_name` VARCHAR(50),
    `note` TEXT,
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng order_detail
CREATE TABLE `order_detail` (
    `orderDetail_id` INT AUTO_INCREMENT PRIMARY KEY,
    `order_id` INT,
    `product_id` INT,
    `quantity` INT,
    `amount` DECIMAL(10, 2),
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng ship_infor
CREATE TABLE `ship_infor` (
    `ship_id` INT AUTO_INCREMENT PRIMARY KEY,
    `address_id` INT,
    `order_id` INT
);

-- Tạo bảng cart
CREATE TABLE cart (
    `cart_id` INT AUTO_INCREMENT PRIMARY KEY,
    `customer_id` INT,
    `product_id` INT,
    `quantity` INT
);

-- Tạo bảng wishlist
CREATE TABLE `wishlist` (
    `wishlist_id` INT AUTO_INCREMENT PRIMARY KEY,
    `product_id` INT,
    `customer_id` INT
);

-- Tạo bảng product_reviews
CREATE TABLE `product_reviews` (
    `reviews_id` INT AUTO_INCREMENT PRIMARY KEY,
    `customer_id` INT,
    `review_content` TEXT,
    `star_for_product` INT,
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);

-- Tạo bảng picture
CREATE TABLE picture (
    picture_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    picture_file BLOB, -- Để hình ảnh
    create_at TIMESTAMP,
    create_by VARCHAR(255),
    update_at TIMESTAMP,
    update_by VARCHAR(255)
);