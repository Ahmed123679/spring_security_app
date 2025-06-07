Create Table user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

Select * from user;

insert into user (username, password, email) 
values 
('Ahmed', '1234', 'midoibrahim432@gamil.com'),
('Yasser', '0000', 'yasser@yahoo.com');


insert into user (username, password, email)
values 
('Mohamed', '$2a$10$wT6Anl7Jf9oaRkNknGJG1eM3sZj5KzNolSo7VZqB3448Zby1i2D4O', 'mo@hotmail.com');