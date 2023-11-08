-- insert roles
INSERT INTO `roles` (`role_name`, `create_at`, `create_by` ) VALUES
('ADMIN', CURDATE(), 'stranger'),
('USER', CURDATE(), 'stranger');
select * from `roles`;

INSERT INTO customer (customer_name, `password`, phone_number, email, `status`, role_id, create_at, create_by)
VALUES ('Admin', '$2a$12$T7/7gDQcqxckpQn/mTitReQN.UGlsPqz1yYzSTD1fBOJNlF6BxpGi','admin', 'admin@gmail.com', 'Open', 1, CURDATE(), 'stranger');