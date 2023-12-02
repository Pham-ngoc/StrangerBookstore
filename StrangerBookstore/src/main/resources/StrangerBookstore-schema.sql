create database StrangerBookstore;
use StrangerBookstore;
-- drop database strangerbookstore;
-- Tạo bảng Customer
CREATE TABLE `customer` (
    `customer_id` INT AUTO_INCREMENT PRIMARY KEY,
    `customer_name` VARCHAR(255),
    `password` VARCHAR(255),
    `phone_number` VARCHAR(15),
    `email` VARCHAR(255),
    `status` VARCHAR(15),
    `picture` TEXT, -- Để hình ảnh
    `role_id` INT,
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);
ALTER TABLE `customer`
ADD CONSTRAINT fk_customer_role
FOREIGN KEY (role_id)
REFERENCES roles (role_id);

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
CREATE TABLE `products` (
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
    `product_img` VARCHAR(255),
    `category_id` INT,
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);
ALTER TABLE `products`
ADD CONSTRAINT fk_product_category
FOREIGN KEY (category_id)
REFERENCES categories (category_id);

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
    `recipient_full_name` VARCHAR(255),
    `recipient_phone_number` VARCHAR(15),
    `address_detail` TEXT,
    `address_type` VARCHAR(255),
    `customer_id` INT,
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);
ALTER TABLE `address`
ADD CONSTRAINT fk_address_customer
FOREIGN KEY (customer_id)
REFERENCES customer (customer_id);

-- Tạo bảng news
CREATE TABLE `news` (
    `news_id` INT AUTO_INCREMENT PRIMARY KEY,
    `news_title` VARCHAR(255),
    `news_content` TEXT,
    `news_picture` TEXT, -- Để hình ảnh
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);
-- drop table news;
-- Tạo bảng contact_us
CREATE TABLE `contact_us` (
    `contact_id` INT AUTO_INCREMENT PRIMARY KEY,
    `full_name` VARCHAR(255),
    `phone_number` VARCHAR(15),
    `email` VARCHAR(255),
    `subject` VARCHAR(255),
    `message` TEXT,
    `status` VARCHAR(55),
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);
-- Tạo bảng `order`
CREATE TABLE `orders` (
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
ALTER TABLE `orders`
ADD CONSTRAINT fk_order_customer
FOREIGN KEY (customer_id)
REFERENCES customer (customer_id);

ALTER TABLE `orders`
ADD CONSTRAINT fk_order_statusOrder
FOREIGN KEY (status_id)
REFERENCES status_orders (status_id);

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
    `order_details_id` INT AUTO_INCREMENT PRIMARY KEY,
    `order_id` INT,
    `product_id` INT,
    `quantity` INT,
    `amount` DECIMAL(10, 2),
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);
ALTER TABLE `order_detail`
ADD CONSTRAINT fk_orderDetail_order
FOREIGN KEY (order_id)
REFERENCES `orders` (order_id);

ALTER TABLE `order_detail`
ADD CONSTRAINT fk_orderDetail_product
FOREIGN KEY (product_id)
REFERENCES products (product_id);

-- Tạo bảng ship_infor
CREATE TABLE `ship_infor` (
    `ship_id` INT AUTO_INCREMENT PRIMARY KEY,
    `address_id` INT,
    `order_id` INT,
    `status` varchar(10),
    `note` varchar(255)
);
ALTER TABLE `ship_infor`
ADD CONSTRAINT fk_shipInfor_order
FOREIGN KEY (order_id)
REFERENCES `orders` (order_id);

ALTER TABLE `ship_infor`
ADD CONSTRAINT fk_shipInfor_address
FOREIGN KEY (address_id)
REFERENCES address (address_id);

-- Tạo bảng cart
CREATE TABLE `cart` (
    `cart_id` INT AUTO_INCREMENT PRIMARY KEY,
    `customer_id` INT,
    `product_id` INT,
    `quantity` INT
);
ALTER TABLE `cart`
ADD CONSTRAINT fk_cart_customer
FOREIGN KEY (customer_id)
REFERENCES `customer` (customer_id);

ALTER TABLE `cart`
ADD CONSTRAINT fk_cart_product
FOREIGN KEY (product_id)
REFERENCES products (product_id);

-- Tạo bảng wishlist
CREATE TABLE `wishlist` (
    `wishlist_id` INT AUTO_INCREMENT PRIMARY KEY,
    `product_id` INT,
    `customer_id` INT
);
ALTER TABLE `wishlist`
ADD CONSTRAINT fk_wishlist_customer
FOREIGN KEY (customer_id)
REFERENCES `customer` (customer_id);

ALTER TABLE `wishlist`
ADD CONSTRAINT fk_wishlist_product
FOREIGN KEY (product_id)
REFERENCES products (product_id);

-- Tạo bảng product_reviews
CREATE TABLE `product_reviews` (
    `reviews_id` INT AUTO_INCREMENT PRIMARY KEY,
    `customer_id` INT,
    `review_content` TEXT,
    `star_for_product` INT,
    `product_id` INT,
    `create_at` TIMESTAMP,
    `create_by` VARCHAR(255),
    `update_at` TIMESTAMP,
    `update_by` VARCHAR(255)
);
ALTER TABLE `product_reviews`
ADD CONSTRAINT fk_productReviews_customer
FOREIGN KEY (customer_id)
REFERENCES `customer` (customer_id);

ALTER TABLE `product_reviews`
ADD CONSTRAINT fk_productReviews_product
FOREIGN KEY (product_id)
REFERENCES products (product_id);


