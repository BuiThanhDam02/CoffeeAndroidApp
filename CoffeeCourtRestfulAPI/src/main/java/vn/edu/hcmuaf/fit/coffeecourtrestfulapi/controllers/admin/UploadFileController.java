package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers.admin;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RequestMapping("/api/admin")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UploadFileController {
    private static final String UPLOAD_DIR = "/src/main/resources/";
    private static final String ONTIME_UPLOAD_DIR = "/target/classes/";
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir")+UPLOAD_DIR);
    private static final Path ONTIME_FOLDER = Paths.get(System.getProperty("user.dir")+ONTIME_UPLOAD_DIR);
    @PostMapping("/upload/coffee")
    @ResponseBody
    public String uploadCoffeeImage(@RequestParam("file") MultipartFile file) {
        try {
            // Lấy tên file gốc
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            // Tạo đường dẫn tới thư mục lưu trữ
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");
            Path coffeePath = Paths.get("coffee");
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path cfile = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(coffeePath).resolve(fileName);
            Path onfile = ONTIME_FOLDER.resolve(staticPath).resolve(imagePath).resolve(coffeePath).resolve(fileName);
            try (OutputStream os = Files.newOutputStream(cfile)) {
                os.write(file.getBytes());
            }
            try (OutputStream os = Files.newOutputStream(onfile)) {
                os.write(file.getBytes());
            }

            return "/images/coffee/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi khi tải lên hình ảnh.";
        }
    }
    @PostMapping("/upload/supplier")
    @ResponseBody
    public String uploadSupplierImage(@RequestParam("file") MultipartFile file) {
        try {
            // Lấy tên file gốc
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            // Tạo đường dẫn tới thư mục lưu trữ
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");
            Path coffeePath = Paths.get("sup");
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path cfile = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(coffeePath).resolve(fileName);
            Path onfile = ONTIME_FOLDER.resolve(staticPath).resolve(imagePath).resolve(coffeePath).resolve(fileName);
            try (OutputStream os = Files.newOutputStream(cfile)) {
                os.write(file.getBytes());
            }
            try (OutputStream os = Files.newOutputStream(onfile)) {
                os.write(file.getBytes());
            }

            return "/images/sup/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi khi tải lên hình ảnh.";
        }
    }
}
