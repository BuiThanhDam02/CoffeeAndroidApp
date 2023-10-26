use coffeecourt;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    password VARCHAR(255),
    address VARCHAR(255),
    level INT,
    token VARCHAR(255)
);

CREATE TABLE admins (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    password VARCHAR(255),
    address VARCHAR(255),
    level INT,
    token VARCHAR(255)
);

CREATE TABLE suppliers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    phone VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    status INT
);

CREATE TABLE supplier_images (
    supplier_id INT PRIMARY KEY AUTO_INCREMENT,
    imagelink VARCHAR(255),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE coffees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    supplier_id INT,
    name VARCHAR(255),
    description VARCHAR(255),
    status int,
    price float,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE coffee_discounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coffee_id INT,
    discount float,
    status int,
    FOREIGN KEY (coffee_id) REFERENCES coffees(id)
);

CREATE TABLE coffee_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coffee_id INT,
    imagelink VARCHAR(255),
    status int,
    FOREIGN KEY (coffee_id) REFERENCES coffees(id)
);

CREATE TABLE coffee_stars (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coffee_id INT,
    star INT,
    status int,
    FOREIGN KEY (coffee_id) REFERENCES coffees(id)
);

CREATE TABLE comments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    coffee_id INT,
    name VARCHAR(255),
    content VARCHAR(255),
    star INT,
    status int,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (coffee_id) REFERENCES coffees(id)
);

CREATE TABLE likes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    coffee_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (coffee_id) REFERENCES coffees(id)
);

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    name VARCHAR(255),
    phone VARCHAR(255),
    status int,
    total_price float,
    type int,
    address VARCHAR(255),
    note VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_details (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    coffee_id INT,
    quantity INT,
    price float,
    discount float,
    name VARCHAR(255),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (coffee_id) REFERENCES coffees(id)
);