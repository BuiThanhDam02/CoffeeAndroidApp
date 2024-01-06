/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : coffeecourt

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 03/01/2024 10:22:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_roles
-- ----------------------------
DROP TABLE IF EXISTS `admin_roles`;
CREATE TABLE `admin_roles`  (
  `admin_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  INDEX `admin_id`(`admin_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `admin_roles_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `admin_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_roles
-- ----------------------------

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `level` int NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admins
-- ----------------------------

-- ----------------------------
-- Table structure for coffee_discounts
-- ----------------------------
DROP TABLE IF EXISTS `coffee_discounts`;
CREATE TABLE `coffee_discounts`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `coffee_id` int NULL DEFAULT NULL,
  `discount` float NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `coffee_id`(`coffee_id` ASC) USING BTREE,
  CONSTRAINT `coffee_discounts_ibfk_1` FOREIGN KEY (`coffee_id`) REFERENCES `coffees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coffee_discounts
-- ----------------------------

-- ----------------------------
-- Table structure for coffee_images
-- ----------------------------
DROP TABLE IF EXISTS `coffee_images`;
CREATE TABLE `coffee_images`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `coffee_id` int NULL DEFAULT NULL,
  `imagelink` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `coffee_id`(`coffee_id` ASC) USING BTREE,
  CONSTRAINT `coffee_images_ibfk_1` FOREIGN KEY (`coffee_id`) REFERENCES `coffees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coffee_images
-- ----------------------------
INSERT INTO `coffee_images` VALUES (1, 1, '/images/coffee/latte.jpg', 0);
INSERT INTO `coffee_images` VALUES (2, 2, '/images/coffee/CAPPUCCINO.jpg', 0);
INSERT INTO `coffee_images` VALUES (3, 3, '/images/coffee/AMERICANO.jpg', 0);
INSERT INTO `coffee_images` VALUES (4, 4, '/images/coffee/ESPRESSO.jpg', 0);
INSERT INTO `coffee_images` VALUES (5, 5, '/images/coffee/Phindi_Pitaschio.jpg', 0);
INSERT INTO `coffee_images` VALUES (6, 6, '/images/coffee/PHINDI_CHOCO.jpg', 0);
INSERT INTO `coffee_images` VALUES (7, 7, '/images/coffee/PHINDI_KEM_SUA.jpg', 0);
INSERT INTO `coffee_images` VALUES (8, 8, '/images/coffee/BAC_XIU._HL.jpg', 0);
INSERT INTO `coffee_images` VALUES (9, 9, '/images/coffee/CARAMEL_MACCHIATTO.jpg', 0);
INSERT INTO `coffee_images` VALUES (10, 10, '/images/coffee/MOCHA.jpg', 0);
INSERT INTO `coffee_images` VALUES (11, 11, '/images/coffee/CappuccinoPL.png', 0);
INSERT INTO `coffee_images` VALUES (12, 12, '/images/coffee/LattePL.png', 0);
INSERT INTO `coffee_images` VALUES (13, 13, '/images/coffee/PhinSuaDaPL.png', 0);
INSERT INTO `coffee_images` VALUES (14, 14, '/images/coffee/PhinDenDaPL.png', 0);
INSERT INTO `coffee_images` VALUES (15, 15, '/images/coffee/vanillaPL.png', 0);
INSERT INTO `coffee_images` VALUES (16, 16, '/images/coffee/phin-sua-tuoi-banh-flan.png', 0);
INSERT INTO `coffee_images` VALUES (17, 17, '/images/coffee/duong_den_sua_da.png', 0);
INSERT INTO `coffee_images` VALUES (18, 18, '/images/coffee/The_Coffee_House_sua_da.png', 0);
INSERT INTO `coffee_images` VALUES (19, 19, '/images/coffee/The_Coffee_House_sua_da_tch.png', 0);
INSERT INTO `coffee_images` VALUES (20, 20, '/images/coffee/cfsua-nong.png', 0);
INSERT INTO `coffee_images` VALUES (21, 21, '/images/coffee/bac-siu_tch.jpg', 0);
INSERT INTO `coffee_images` VALUES (22, 22, '/images/coffee/bacsiunong.png', 0);
INSERT INTO `coffee_images` VALUES (23, 23, '/images/coffee/ca-phe-den-da_tch.jpg', 0);
INSERT INTO `coffee_images` VALUES (24, 24, '/images/coffee/ca-phe-den-nong_tch.jpg', 0);
INSERT INTO `coffee_images` VALUES (25, 25, '/images/coffee/ca_phe_sua_da_chai_fresh_250ml.png', 0);
INSERT INTO `coffee_images` VALUES (26, 26, '/images/coffee/duong_den_marble_latte.png', 0);
INSERT INTO `coffee_images` VALUES (27, 27, '/images/coffee/caramel-macchiato_tch.png', 0);
INSERT INTO `coffee_images` VALUES (28, 28, '/images/coffee/caramelmacchiatonongtch.jpg', 0);
INSERT INTO `coffee_images` VALUES (29, 29, '/images/coffee/latte-da_tch.png', 0);
INSERT INTO `coffee_images` VALUES (30, 30, '/images/coffee/latte_nong_tch.png', 0);
INSERT INTO `coffee_images` VALUES (31, 31, '/images/coffee/Americano_da.png', 0);
INSERT INTO `coffee_images` VALUES (32, 32, '/images/coffee/Americano_nong.jpg', 0);
INSERT INTO `coffee_images` VALUES (33, 33, '/images/coffee/capu-da_tch.png', 0);
INSERT INTO `coffee_images` VALUES (34, 34, '/images/coffee/capu-da_tch.png', 0);
INSERT INTO `coffee_images` VALUES (35, 35, '/images/coffee/espressoda_da_tch.jpg', 0);
INSERT INTO `coffee_images` VALUES (36, 36, '/images/coffee/espresso_nong_tch.jpg', 0);
INSERT INTO `coffee_images` VALUES (37, 37, '/images/coffee/coldbrew-pbt_tch.png', 0);
INSERT INTO `coffee_images` VALUES (38, 38, '/images/coffee/cold-brew-sua-tuoi_tch.png', 0);
INSERT INTO `coffee_images` VALUES (39, 39, '/images/coffee/classic-cold-brew.jpg', 0);
INSERT INTO `coffee_images` VALUES (40, 40, '/images/coffee/ca_phe_sua_Success100ml.jpg', 0);
INSERT INTO `coffee_images` VALUES (41, 41, '/images/coffee/ca_phe_den_success.jpg', 0);
INSERT INTO `coffee_images` VALUES (42, 42, '/images/coffee/bac_xiu_success.jpg', 0);
INSERT INTO `coffee_images` VALUES (43, 43, '/images/coffee/bac_xiu_success_2.jpg', 0);
INSERT INTO `coffee_images` VALUES (44, 44, '/images/coffee/ca_phe_sua_success.jpg', 0);
INSERT INTO `coffee_images` VALUES (45, 45, '/images/coffee/ca_phe_den_success_2.jpg', 0);
INSERT INTO `coffee_images` VALUES (54, 48, '/images/coffee/4bab552f7ed0a6-trasuaberryberry60000839.png', 0);
INSERT INTO `coffee_images` VALUES (55, 49, '/images/coffee/Mint-Chocolate-Milk-Tea-w-Pearl-Iced.png', 0);

-- ----------------------------
-- Table structure for coffee_stars
-- ----------------------------
DROP TABLE IF EXISTS `coffee_stars`;
CREATE TABLE `coffee_stars`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `coffee_id` int NULL DEFAULT NULL,
  `star` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `coffee_id`(`coffee_id` ASC) USING BTREE,
  CONSTRAINT `coffee_stars_ibfk_1` FOREIGN KEY (`coffee_id`) REFERENCES `coffees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coffee_stars
-- ----------------------------

-- ----------------------------
-- Table structure for coffees
-- ----------------------------
DROP TABLE IF EXISTS `coffees`;
CREATE TABLE `coffees`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_id` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `supplier_id`(`supplier_id` ASC) USING BTREE,
  CONSTRAINT `coffees_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coffees
-- ----------------------------
INSERT INTO `coffees` VALUES (1, 1, 'LATTE', '\"Ly cà phê sữa ngọt ngào đến khó quên! Với một chút nhẹ nhàng hơn so với Cappuccino', 0, 65000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (2, 1, 'CAPPUCCINO HighLand', '\"Ly cà phê sữa đậm đà thời thượng! Một chút đậm đà hơn so với Latte', 0, 65000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (3, 1, 'AMERICANO', 'Americano tại Highlands Coffee là sự kết hợp giữa cà phê espresso thêm vào nước đun sôi. Bạn có thể tùy thích lựa chọn uống nóng hoặc dùng chung với đá.', 0, 45000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (4, 1, 'ESPRESSO', 'Đích thực là ly cà phê espresso ngon đậm đà! Được chiết xuất một cách hoàn hảo từ loại cà phê rang được phối trộn độc đáo từ những hạt cà phê Robusta và Arabica chất lượng hảo hạng.', 0, 45000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (5, 1, 'PHINDI HẠT DẺ CƯỜI', '\"PhinDi Hạt Dẻ Cười - Cà phê Phin thế hệ mới với chất Phin êm hơn', 0, 55000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (6, 1, 'PHINDI CHOCO', '\"PhinDi Choco - Cà phê Phin thế hệ mới với chất Phin êm hơn', 0, 45000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (7, 1, 'PHINDI KEM SỮA', '\"PhinDi Kem Sữa - Cà phê Phin thế hệ mới với chất Phin êm hơn', 0, 45000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (8, 1, 'BẠC XỈU ĐÁ', '\"Nếu Phin Sữa Đá dành cho các bạn đam mê vị đậm đà', 0, 25000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (9, 1, 'CARAMEL MACCHIATO', '\"Thỏa mãn cơn thèm ngọt! Ly cà phê Caramel Macchiato bắt đầu từ dòng sữa tươi và lớp bọt sữa béo ngậy', 0, 69000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (10, 1, 'MOCHA MACCHIATO', 'Một thức uống yêu thích được kết hợp bởi giữa sốt sô cô la ngọt ngào', 0, 69000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (11, 2, 'Cappuccino Phúc Long', 'Cappuccino Phúc Long', 0, 45000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (12, 2, 'Latte Phúc Long', 'Latte Phúc Long', 0, 45000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (13, 2, 'Phin Sữa Đá Phúc Long', 'Phin Sữa Đá Phúc Long', 0, 35000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (14, 2, 'Phin Đen Đá Phúc Long', 'Phin Đen Đá Phúc Long', 0, 30000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (15, 2, 'Vanilla Latte Phúc Long', 'Vanilla Latte Phúc Long', 0, 45000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (16, 3, 'Phin Sữa Tươi Bánh Flan', '\"Tỉnh tức thì cùng cà phê Robusta pha phin đậm đà và bánh flan núng nính. Uống là tỉnh', 0, 49000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (17, 3, 'Đường Đen Sữa Đá', '\"Nếu chuộng vị cà phê đậm đà', 0, 45000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (18, 3, 'The Coffee House Sữa Đá', '\"Thức uống giúp tỉnh táo tức thì để bắt đầu ngày mới thật hứng khởi. Không đắng khét như cà phê truyền thống', 0, 39000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (19, 3, 'Cà Phê Sữa Đá', '\"Cà phê Đắk Lắk nguyên chất được pha phin truyền thống kết hợp với sữa đặc tạo nên hương vị đậm đà', 0, 29000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (20, 3, 'Cà Phê Sữa Nóng', '\"Cà phê được pha phin truyền thống kết hợp với sữa đặc tạo nên hương vị đậm đà', 0, 29000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (21, 3, 'Bạc Sỉu', '\"Bạc sỉu chính là \"\"Ly sữa trắng kèm một chút cà phê\"\". Thức uống này rất phù hợp những ai vừa muốn trải nghiệm chút vị đắng của cà phê vừa muốn thưởng thức vị ngọt béo ngậy từ sữa.\"', 0, 29000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (22, 3, 'Bạc Sỉu Nóng', '\"Bạc sỉu chính là \"\"Ly sữa trắng kèm một chút cà phê\"\". Thức uống này rất phù hợp những ai vừa muốn trải nghiệm chút vị đắng của cà phê vừa muốn thưởng thức vị ngọt béo ngậy từ sữa.\"', 0, 39000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (23, 3, 'Cà Phê Đen Đá', 'Không ngọt ngào như Bạc sỉu hay Cà phê sữa, Cà phê đen mang trong mình phong vị trầm lắng, thi vị hơn. Người ta thường phải ngồi rất lâu mới cảm nhận được hết hương thơm ngào ngạt, phảng phất mùi cacao và cái đắng mượt mà trôi tuột xuống vòm họng.', 0, 29000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (24, 3, 'Cà Phê Đen Nóng', 'Không ngọt ngào như Bạc sỉu hay Cà phê sữa, Cà phê đen mang trong mình phong vị trầm lắng, thi vị hơn. Người ta thường phải ngồi rất lâu mới cảm nhận được hết hương thơm ngào ngạt, phảng phất mùi cacao và cái đắng mượt mà trôi tuột xuống vòm họng.', 0, 39000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (25, 3, 'Cà Phê Sữa Đá Chai Fresh 250ML', '\"Vẫn là hương vị cà phê sữa đậm đà quen thuộc của The Coffee House nhưng khoác lên mình một chiếc áo mới tiện lợi hơn', 0, 75000, '2023-10-31 09:53:38');
INSERT INTO `coffees` VALUES (26, 3, 'Đường Đen Marble Latte', 'Đường Đen Marble Latte êm dịu cực hấp dẫn bởi vị cà phê đắng nhẹ hoà quyện cùng vị đường đen ngọt thơm và sữa tươi béo mịn. Sự kết hợp đầy mới mẻ của cà phê và đường đen cũng tạo nên diện mạo phân tầng đẹp mắt. Đây là lựa chọn đáng thử để bạn khởi đầu ngà', 0, 55000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (27, 3, 'Caramel Macchiato Đá', '\"Khuấy đều trước khi sử dụng Caramel Macchiato sẽ mang đến một sự ngạc nhiên thú vị khi vị thơm béo của bọt sữa', 0, 55000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (28, 3, 'Caramel Macchiato Nóng', '\"Caramel Macchiato sẽ mang đến một sự ngạc nhiên thú vị khi vị thơm béo của bọt sữa', 0, 55000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (29, 3, 'Latte Đá', '\"Một sự kết hợp tinh tế giữa vị đắng cà phê Espresso nguyên chất hòa quyện cùng vị sữa nóng ngọt ngào', 0, 55000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (30, 3, 'Latte Nóng', '\"Một sự kết hợp tinh tế giữa vị đắng cà phê Espresso nguyên chất hòa quyện cùng vị sữa nóng ngọt ngào', 0, 55000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (31, 3, 'Americano Đá', '\"Americano được pha chế bằng cách pha thêm nước với tỷ lệ nhất định vào tách cà phê Espresso', 0, 45000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (32, 3, 'Americano Nóng', '\"Americano được pha chế bằng cách pha thêm nước với tỷ lệ nhất định vào tách cà phê Espresso', 0, 45000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (33, 3, 'Cappuccino Đá', '\"Thanh mát và cân bằng với hương vị cà phê nguyên bản 100% Arabica Cầu Đất cùng sữa tươi thơm béo cho từng ngụm tròn vị', 0, 55000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (34, 3, 'Cappuccino Nóng', '\"Capuchino là thức uống hòa quyện giữa hương thơm của sữa', 0, 55000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (35, 3, 'Espresso Đá', '\"Một tách Espresso nguyên bản được bắt đầu bởi những hạt Arabica chất lượng', 0, 49000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (36, 3, 'Espresso Nóng', '\"Một tách Espresso nguyên bản được bắt đầu bởi những hạt Arabica chất lượng', 0, 45000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (37, 3, 'Cold Brew Phúc Bồn Tử', 'Vị chua ngọt của trái phúc bồn tử, làm dậy lên hương vị trái cây tự nhiên vốn sẵn có trong hạt cà phê, hòa quyện thêm vị đăng đắng, ngọt dịu nhẹ nhàng của Cold Brew 100% hạt Arabica Cầu Đất để mang đến một cách thưởng thức cà phê hoàn toàn mới, vừa thơm l', 0, 49000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (38, 3, 'Cold Brew Sữa Tươi', '\"Thanh mát và cân bằng với hương vị cà phê nguyên bản 100% Arabica Cầu Đất cùng sữa tươi thơm béo cho từng ngụm tròn vị', 0, 49000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (39, 3, 'Cold Brew Truyền Thống', '\"Tại The Coffee House, Cold Brew được ủ và phục vụ mỗi ngày từ 100% hạt Arabica Cầu Đất với hương gỗ thông, hạt dẻ, nốt sô-cô-la đặc trưng, thoang thoảng hương khói nhẹ giúp Cold Brew giữ nguyên vị tươi mới.\n\"', 0, 45000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (40, 4, 'Cà Phê Sữa Success – 100ml', 'Mùi thơm dịu, vị béo của sữa hòa quyện với cà phê mang đến sự cân bằng, khơi nguồn năng lượng cho sự sáng tạo, thích hợp với khách hàng có gu cà phê sữa đá truyền thống.', 0, 29000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (41, 4, 'Cà Phê Đen Success – 100ml', 'Mùi thơm nồng, đậm đà đầy lôi cuốn, thích hợp với khách hàng có gu cà phê mạnh.', 0, 25000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (42, 4, 'Bạc Xỉu – 100ml', 'Mùi thơm êm dịu, vị béo hòa quyện vị ngọt, thêm vị đắng nhẹ của cà phê tạo nguồn năng lượng sảng khoái khi thưởng thức, thích hợp với khách hàng có gu cà phê nhẹ.', 0, 29000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (43, 4, 'Bạc Xỉu', 'Mùi thơm êm dịu, vị béo hòa quyện vị ngọt, thêm vị đắng nhẹ của cà phê tạo nguồn năng lượng sảng khoái khi thưởng thức, thích hợp với khách hàng có gu cà phê nhẹ.', 0, 55000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (44, 4, 'Cà Phê Sữa Success', 'Mùi thơm dịu, vị béo của sữa hòa quyện với cà phê mang đến sự cân bằng, khơi nguồn năng lượng cho sự sáng tạo, thích hợp với khách hàng có gu cà phê sữa đá truyền thống.', 0, 55000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (45, 4, 'Cà Phê Đen Success', '\"Mùi thơm nồng, đậm đà đầy lôi cuốn, thích hợp với khách hàng có gu cà phê mạnh.\"', 0, 55000, '2023-12-25 09:53:38');
INSERT INTO `coffees` VALUES (48, 2, 'Trà sữa Berry Berry', 'Ngon ngon lắm Trà sữa Berry Berry', 0, 60000, '2023-12-25 10:24:09');
INSERT INTO `coffees` VALUES (49, 7, 'Mint Choco Milk Tea', 'Mint Choco Milk Tea Dòng thức uống đặc biệt kết hợp giữa trà nguyên chất với lớp milk foam béo cùng vị mặn nhẹ đặc trưng mang đến điểm nhấn sáng tạo cho một thức uống quen thuộc.', 0, 67000, '2024-01-01 09:27:37');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `coffee_id` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `star` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `coffee_id`(`coffee_id` ASC) USING BTREE,
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`coffee_id`) REFERENCES `coffees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, 1, 1, 'Adam', 'Ngon vai Chubin', 4, 0, '2023-12-25 09:55:45');
INSERT INTO `comments` VALUES (2, 1, 2, 'Adam', 'Ngon qua xung dang 1 jack', 4, 0, '2023-12-25 09:55:45');
INSERT INTO `comments` VALUES (3, 1, 2, 'Adam', 'ngon hon mie', 4, 0, '2023-12-25 09:55:45');
INSERT INTO `comments` VALUES (4, 1, 1, 'John Smith', 'Ngon vãi chubin', 4, 0, '2023-12-25 09:55:45');

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `coffee_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `coffee_id`(`coffee_id` ASC) USING BTREE,
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`coffee_id`) REFERENCES `coffees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of likes
-- ----------------------------

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NULL DEFAULT NULL,
  `coffee_id` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `discount` float NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id` ASC) USING BTREE,
  INDEX `coffee_id`(`coffee_id` ASC) USING BTREE,
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`coffee_id`) REFERENCES `coffees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES (1, 1, 1, 1, 65000, NULL, NULL);
INSERT INTO `order_details` VALUES (2, 1, 8, 1, 25000, NULL, NULL);
INSERT INTO `order_details` VALUES (3, 3, 1, 1, 65000, NULL, NULL);
INSERT INTO `order_details` VALUES (4, 5, 1, 1, 65000, NULL, NULL);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `total_price` float NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 'đảm', '0377184642', 1, 90000, 0, NULL, NULL, '2023-12-25 09:55:57');
INSERT INTO `orders` VALUES (3, 1, 'dam', '0123456789', 2, 65000, 1, NULL, NULL, '2023-12-29 09:19:19');
INSERT INTO `orders` VALUES (5, 1, 'dam', '0123456789', 2, 65000, 1, NULL, NULL, '2023-12-29 09:19:19');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 'ADMIN');
INSERT INTO `roles` VALUES (2, 'USER');
INSERT INTO `roles` VALUES (3, 'SUP');

-- ----------------------------
-- Table structure for supplier_images
-- ----------------------------
DROP TABLE IF EXISTS `supplier_images`;
CREATE TABLE `supplier_images`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_id` int NULL DEFAULT NULL,
  `imagelink` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `supplier_id`(`supplier_id` ASC) USING BTREE,
  CONSTRAINT `supplier_images_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of supplier_images
-- ----------------------------
INSERT INTO `supplier_images` VALUES (1, 1, '/images/sup/highlands.png');
INSERT INTO `supplier_images` VALUES (2, 2, '/images/sup/phuclong.png');
INSERT INTO `supplier_images` VALUES (3, 3, '/images/sup/thecoffeehouse.png');
INSERT INTO `supplier_images` VALUES (4, 4, '/images/sup/trungnguyen.png');
INSERT INTO `supplier_images` VALUES (6, 7, '/images/sup/gongcha.jpg');

-- ----------------------------
-- Table structure for suppliers
-- ----------------------------
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of suppliers
-- ----------------------------
INSERT INTO `suppliers` VALUES (1, 'Highlands', '0987654321', '123', 'customerservice@highlandcoffee.com.vn', 0);
INSERT INTO `suppliers` VALUES (2, 'Phúc Long', '0987654322', '456', 'info2@phuclong.masangroup.com', 0);
INSERT INTO `suppliers` VALUES (3, 'The Coffee House', '0987654323', '789', 'hi@thecoffeehouse.vn', 0);
INSERT INTO `suppliers` VALUES (4, 'Trung Nguyên', '0987654324', '123', 'trungnguyenfranchising@trungnguyenlegend.com', 0);
INSERT INTO `suppliers` VALUES (7, 'Gongcha', '0123456789', '123', 'info@gongcha.com.vn', 0);

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `user_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES (1, 2);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'Adam', '123@gmail.com', '0123456789', '123', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
