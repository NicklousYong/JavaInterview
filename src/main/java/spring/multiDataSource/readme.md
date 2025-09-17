当前路径下为关于多数据源的配置。

表结构为：
CREATE TABLE t_user (
user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50) NOT NULL,
create_time DATETIME NOT NULL,
birth_day DATE
);

插入数据：
INSERT INTO t_user (username, create_time, birth_day) VALUES
('Alice', NOW(), '1990-01-01'),
('Bob', NOW(), '1985-05-15'),
('Charlie', NOW(), '1992-07-20'),
('David', NOW(), '1988-03-10'),
('Eve', NOW(), '1995-12-25'),
('Frank', NOW(), '1991-11-11'),
('Grace', NOW(), '1987-06-30'),
('Hank', NOW(), '1993-09-09'),
('Ivy', NOW(), '1990-04-18'),
('Jack', NOW(), '1986-08-22'),
('Kathy', NOW(), '1994-02-14'),
('Leo', NOW(), '1989-10-05'),
('Mona', NOW(), '1996-01-30'),
('Nina', NOW(), '1991-07-07'),
('Oscar', NOW(), '1985-12-12'),
('Paul', NOW(), '1993-03-03'),
('Quinn', NOW(), '1992-11-21'),
('Rachel', NOW(), '1988-08-08'),
('Steve', NOW(), '1990-06-06'),
('Tina', NOW(), '1995-09-15');