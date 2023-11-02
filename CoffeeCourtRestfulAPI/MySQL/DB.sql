use coffeecourt;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    password VARCHAR(255),
    address VARCHAR(255),
    level INT,
    token VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS admins (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    password VARCHAR(255),
    address VARCHAR(255),
    level INT,
    token VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS suppliers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    phone VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    status INT
    );

CREATE TABLE IF NOT EXISTS supplier_images (
     id INT PRIMARY KEY AUTO_INCREMENT,
     supplier_id INT ,
     imagelink VARCHAR(255),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
    );

CREATE TABLE IF NOT EXISTS coffees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    supplier_id INT,
    name VARCHAR(255),
    description VARCHAR(255),
    status int,
    price float,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
    );

CREATE TABLE IF NOT EXISTS coffee_discounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coffee_id INT,
    discount float,
    status int,
    FOREIGN KEY (coffee_id) REFERENCES coffees(id)
    );

CREATE TABLE IF NOT EXISTS coffee_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coffee_id INT,
    imagelink VARCHAR(255),
    status int,
    FOREIGN KEY (coffee_id) REFERENCES coffees(id)
    );

CREATE TABLE IF NOT EXISTS coffee_stars (
    id INT PRIMARY KEY AUTO_INCREMENT,
    coffee_id INT,
    star INT,
    status int,
    FOREIGN KEY (coffee_id) REFERENCES coffees(id)
    );

CREATE TABLE IF NOT EXISTS comments (
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

CREATE TABLE IF NOT EXISTS likes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    coffee_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (coffee_id) REFERENCES coffees(id)
    );

CREATE TABLE IF NOT EXISTS orders (
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

CREATE TABLE IF NOT EXISTS order_details (
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

-- Insert Data Coffee --
INSERT INTO `coffees` (`id`, `supplier_id`, `name`, `description`, `status`, `price`) VALUES
(1, 1, 'LATTE', '"Ly cà phê sữa ngọt ngào đến khó quên! Với một chút nhẹ nhàng hơn so với Cappuccino', 0, 65000),
(2, 1, 'CAPPUCCINO HighLand', '"Ly cà phê sữa đậm đà thời thượng! Một chút đậm đà hơn so với Latte', 0, 65000),
(3, 1, 'AMERICANO', 'Americano tại Highlands Coffee là sự kết hợp giữa cà phê espresso thêm vào nước đun sôi. Bạn có thể tùy thích lựa chọn uống nóng hoặc dùng chung với đá.', 0, 45000),
(4, 1, 'ESPRESSO', 'Đích thực là ly cà phê espresso ngon đậm đà! Được chiết xuất một cách hoàn hảo từ loại cà phê rang được phối trộn độc đáo từ những hạt cà phê Robusta và Arabica chất lượng hảo hạng.', 0, 45000),
(5, 1, 'PHINDI HẠT DẺ CƯỜI', '"PhinDi Hạt Dẻ Cười - Cà phê Phin thế hệ mới với chất Phin êm hơn', 0, 55000),
(6, 1, 'PHINDI CHOCO', '"PhinDi Choco - Cà phê Phin thế hệ mới với chất Phin êm hơn', 0, 45000),
(7, 1, 'PHINDI KEM SỮA', '"PhinDi Kem Sữa - Cà phê Phin thế hệ mới với chất Phin êm hơn', 0, 45000),
(8, 1, 'BẠC XỈU ĐÁ', '"Nếu Phin Sữa Đá dành cho các bạn đam mê vị đậm đà', 0, 25000),
(9, 1, 'CARAMEL MACCHIATO', '"Thỏa mãn cơn thèm ngọt! Ly cà phê Caramel Macchiato bắt đầu từ dòng sữa tươi và lớp bọt sữa béo ngậy', 0, 69000)
(10, 1, 'MOCHA MACCHIATO', '"Một thức uống yêu thích được kết hợp bởi giữa sốt sô cô la ngọt ngào', 0, 69000),
(11, 2, 'Cappuccino Phúc Long', 'Cappuccino Phúc Long', 0, 45000),
(12, 2, 'Latte Phúc Long', 'Latte Phúc Long', 0, 45000),
(13, 2, 'Phin Sữa Đá Phúc Long', 'Phin Sữa Đá Phúc Long', 0, 35000),
(14, 2, 'Phin Đen Đá Phúc Long', 'Phin Đen Đá Phúc Long', 0, 30000),
(15, 2, 'Vanilla Latte Phúc Long', 'Vanilla Latte Phúc Long', 0, 45000),
(16, 3, 'Phin Sữa Tươi Bánh Flan', '"Tỉnh tức thì cùng cà phê Robusta pha phin đậm đà và bánh flan núng nính. Uống là tỉnh', 0, 49000),
(17, 3, 'Đường Đen Sữa Đá', '"Nếu chuộng vị cà phê đậm đà', 0, 45000),
(18, 3, 'The Coffee House Sữa Đá', '"Thức uống giúp tỉnh táo tức thì để bắt đầu ngày mới thật hứng khởi. Không đắng khét như cà phê truyền thống', 0, 39000),
(19, 3, 'Cà Phê Sữa Đá', '"Cà phê Đắk Lắk nguyên chất được pha phin truyền thống kết hợp với sữa đặc tạo nên hương vị đậm đà', 0, 29000),
(20, 3, 'Cà Phê Sữa Nóng', '"Cà phê được pha phin truyền thống kết hợp với sữa đặc tạo nên hương vị đậm đà', 0, 29000),
(21, 3, 'Bạc Sỉu', '"Bạc sỉu chính là ""Ly sữa trắng kèm một chút cà phê"". Thức uống này rất phù hợp những ai vừa muốn trải nghiệm chút vị đắng của cà phê vừa muốn thưởng thức vị ngọt béo ngậy từ sữa."', 0, 29000),
(22, 3, 'Bạc Sỉu Nóng', '"Bạc sỉu chính là ""Ly sữa trắng kèm một chút cà phê"". Thức uống này rất phù hợp những ai vừa muốn trải nghiệm chút vị đắng của cà phê vừa muốn thưởng thức vị ngọt béo ngậy từ sữa."', 0, 39000),
(23, 3, 'Cà Phê Đen Đá', 'Không ngọt ngào như Bạc sỉu hay Cà phê sữa, Cà phê đen mang trong mình phong vị trầm lắng, thi vị hơn. Người ta thường phải ngồi rất lâu mới cảm nhận được hết hương thơm ngào ngạt, phảng phất mùi cacao và cái đắng mượt mà trôi tuột xuống vòm họng.', 0, 29000),
(24, 3, 'Cà Phê Đen Nóng', 'Không ngọt ngào như Bạc sỉu hay Cà phê sữa, Cà phê đen mang trong mình phong vị trầm lắng, thi vị hơn. Người ta thường phải ngồi rất lâu mới cảm nhận được hết hương thơm ngào ngạt, phảng phất mùi cacao và cái đắng mượt mà trôi tuột xuống vòm họng.', 0, 39000),
(25, 3, 'Cà Phê Sữa Đá Chai Fresh 250ML', '"Vẫn là hương vị cà phê sữa đậm đà quen thuộc của The Coffee House nhưng khoác lên mình một chiếc áo mới tiện lợi hơn', 0, 75000),
(26, 3, 'Đường Đen Marble Latte', 'Đường Đen Marble Latte êm dịu cực hấp dẫn bởi vị cà phê đắng nhẹ hoà quyện cùng vị đường đen ngọt thơm và sữa tươi béo mịn. Sự kết hợp đầy mới mẻ của cà phê và đường đen cũng tạo nên diện mạo phân tầng đẹp mắt. Đây là lựa chọn đáng thử để bạn khởi đầu ngà', 0, 55000),
(27, 3, 'Caramel Macchiato Đá', '"Khuấy đều trước khi sử dụng Caramel Macchiato sẽ mang đến một sự ngạc nhiên thú vị khi vị thơm béo của bọt sữa', 0, 55000),
(28, 3, 'Caramel Macchiato Nóng', '"Caramel Macchiato sẽ mang đến một sự ngạc nhiên thú vị khi vị thơm béo của bọt sữa', 0, 55000),
(29, 3, 'Latte Đá', '"Một sự kết hợp tinh tế giữa vị đắng cà phê Espresso nguyên chất hòa quyện cùng vị sữa nóng ngọt ngào', 0, 55000),
(30, 3, 'Latte Nóng', '"Một sự kết hợp tinh tế giữa vị đắng cà phê Espresso nguyên chất hòa quyện cùng vị sữa nóng ngọt ngào', 0, 55000),
(31, 3, 'Americano Đá', '"Americano được pha chế bằng cách pha thêm nước với tỷ lệ nhất định vào tách cà phê Espresso', 0, 45000),
(32, 3, 'Americano Nóng', '"Americano được pha chế bằng cách pha thêm nước với tỷ lệ nhất định vào tách cà phê Espresso', 0, 45000),
(33, 3, 'Cappuccino Đá', '"Thanh mát và cân bằng với hương vị cà phê nguyên bản 100% Arabica Cầu Đất cùng sữa tươi thơm béo cho từng ngụm tròn vị', 0, 55000),
(34, 3, 'Cappuccino Nóng', '"Capuchino là thức uống hòa quyện giữa hương thơm của sữa', 0, 55000),
(35, 3, 'Espresso Đá', '"Một tách Espresso nguyên bản được bắt đầu bởi những hạt Arabica chất lượng', 0, 49000),
(36, 3, 'Espresso Nóng', '"Một tách Espresso nguyên bản được bắt đầu bởi những hạt Arabica chất lượng', 0, 45000),
(37, 3, 'Cold Brew Phúc Bồn Tử', 'Vị chua ngọt của trái phúc bồn tử, làm dậy lên hương vị trái cây tự nhiên vốn sẵn có trong hạt cà phê, hòa quyện thêm vị đăng đắng, ngọt dịu nhẹ nhàng của Cold Brew 100% hạt Arabica Cầu Đất để mang đến một cách thưởng thức cà phê hoàn toàn mới, vừa thơm l', 0, 49000),
(38, 3, 'Cold Brew Sữa Tươi', '"Thanh mát và cân bằng với hương vị cà phê nguyên bản 100% Arabica Cầu Đất cùng sữa tươi thơm béo cho từng ngụm tròn vị', 0, 49000),
(39, 3, 'Cold Brew Truyền Thống', '"Tại The Coffee House, Cold Brew được ủ và phục vụ mỗi ngày từ 100% hạt Arabica Cầu Đất với hương gỗ thông, hạt dẻ, nốt sô-cô-la đặc trưng, thoang thoảng hương khói nhẹ giúp Cold Brew giữ nguyên vị tươi mới.\n"', 0, 45000),
(40, 4, 'Cà Phê Sữa Success – 100ml', 'Mùi thơm dịu, vị béo của sữa hòa quyện với cà phê mang đến sự cân bằng, khơi nguồn năng lượng cho sự sáng tạo, thích hợp với khách hàng có gu cà phê sữa đá truyền thống.', 0, 29000),
(41, 4, 'Cà Phê Đen Success – 100ml', 'Mùi thơm nồng, đậm đà đầy lôi cuốn, thích hợp với khách hàng có gu cà phê mạnh.', 0, 25000),
(42, 4, 'Bạc Xỉu – 100ml', 'Mùi thơm êm dịu, vị béo hòa quyện vị ngọt, thêm vị đắng nhẹ của cà phê tạo nguồn năng lượng sảng khoái khi thưởng thức, thích hợp với khách hàng có gu cà phê nhẹ.', 0, 29000),
(43, 4, 'Bạc Xỉu', 'Mùi thơm êm dịu, vị béo hòa quyện vị ngọt, thêm vị đắng nhẹ của cà phê tạo nguồn năng lượng sảng khoái khi thưởng thức, thích hợp với khách hàng có gu cà phê nhẹ.', 0, 55000),
(44, 4, 'Cà Phê Sữa Success', 'Mùi thơm dịu, vị béo của sữa hòa quyện với cà phê mang đến sự cân bằng, khơi nguồn năng lượng cho sự sáng tạo, thích hợp với khách hàng có gu cà phê sữa đá truyền thống.', 0, 55000),
(45, 4, 'Cà Phê Đen Success', '"Mùi thơm nồng, đậm đà đầy lôi cuốn, thích hợp với khách hàng có gu cà phê mạnh."', 0, 55000);

-- Inset Data supplier --

INSERT INTO `suppliers` (`id`, `name`, `phone`, `password`, `email`, `status`) VALUES
(1, 'Highlands', '0987654321', '123', 'customerservice@highlandcoffee.com.vn', 0),
(2, 'Phúc Long', '0987654322', '456', 'info2@phuclong.masangroup.com', 0),
(3, 'The Coffee House', '0987654323', '789', 'hi@thecoffeehouse.vn', 0),
(4, 'Trung Nguyên', '0987654324', '123', 'trungnguyenfranchising@trungnguyenlegend.com', 0);

