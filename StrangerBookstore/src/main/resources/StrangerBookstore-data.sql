-- insert roles
INSERT INTO `roles` (`role_name`, `create_at`, `create_by` ) VALUES
('ADMIN', CURDATE(), 'stranger'),
('USER', CURDATE(), 'stranger');
select * from `roles`;
-- insert customer (admin)
INSERT INTO customer (customer_name, `password`, phone_number, email, `status`, role_id, create_at, create_by)
VALUES ('Admin', '$  2a$12$T7/7gDQcqxckpQn/mTitReQN.UGlsPqz1yYzSTD1fBOJNlF6BxpGi','admin', 'admin@gmail.com', 'Open', 1, CURDATE(), 'stranger');
--insert categories
INSERT INTO `categories` (`category_name`, `create_at`, `create_by` ) VALUES
    ('Tiểu thuyết', CURDATE(), 'stranger'),
    ('Sách kỹ năng', CURDATE(), 'stranger'),
    ('Khoa học viễn tưởng', CURDATE(), 'stranger'),
    ('Kỹ thuật số và công nghệ', CURDATE(), 'stranger'),
    ('Tâm lý học', CURDATE(), 'stranger'),
    ('Phát triển cá nhân', CURDATE(), 'stranger'),
    ('Truyện tranh và Sách thiếu nhi', CURDATE(), 'stranger');
select * from categories;
