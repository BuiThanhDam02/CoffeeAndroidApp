package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.CoffeeImage;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Comment;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Supplier;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.request.CoffeeRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    CoffeeRepository coffeeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private CoffeeImageRepository coffeeImageRepository;
    @Autowired
    private SupplierRepository supplierRepository;


    @GetMapping("/all")
    public List<Coffee> getAll() {
        List<Coffee> coffees = coffeeRepository.findAll();
        for (Coffee coffee : coffees) {
            List<CoffeeImage> coffeeImages = coffeeImageRepository.findByCoffeeId(coffee.getId());
            if (!coffeeImages.isEmpty()) {
                coffee.setImageLink(coffeeImages.get(0).getImageLink());
            }
        }
        return coffees;
    }

    @GetMapping("/search")
    public List<Coffee> searchByName(@RequestParam String name) {
        List<Coffee> coffees = coffeeRepository.findByNameContaining(name);
        for (Coffee coffee : coffees) {
            List<CoffeeImage> coffeeImages = coffeeImageRepository.findByCoffeeId(coffee.getId());
            if (!coffeeImages.isEmpty()) {
                coffee.setImageLink(coffeeImages.get(0).getImageLink());
            }
        }
        return coffees;
    }

    @GetMapping("/bySupplierId")
    public List<Coffee> getBySupplierId(@RequestParam("supplierId") Long supplierId){
        List<Coffee> coffees = coffeeRepository.findBySupplierId(supplierId);
        for (Coffee coffee : coffees) {
            List<CoffeeImage> coffeeImages = coffeeImageRepository.findByCoffeeId(coffee.getId());
            if (!coffeeImages.isEmpty()) {
                coffee.setImageLink(coffeeImages.get(0).getImageLink());
            }
        }
        return coffees;
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getCommentsByCoffeeId(@PathVariable Long id) {
        return commentRepository.findByCoffeeId(id);
    }

//    @GetMapping("/{id}/like/{userid}")
//    public List<Comment> likeByCoffeeId(@PathVariable Long id) {
//        return likeRepository.findByCoffeeId(id);
//    }
//    @GetMapping("/{id}/unlike")
//    public List<Comment> unlikeByCoffeeId(@PathVariable Long id) {
//        return likeRepository.findByCoffeeId(id);
//    }
@PostMapping("/add")
public ResponseEntity<String> addCoffee(@RequestBody CoffeeRequest coffeeRequest) {
    try {
        Supplier supplier = supplierRepository.findById(coffeeRequest.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        Optional<Coffee> exitingCoffee = Optional.ofNullable(coffeeRepository.findByName(coffeeRequest.getName()));
        Coffee coffee = null;
        if(exitingCoffee.isPresent()) {
            coffee = exitingCoffee.get();
            if(Objects.equals(coffee.getSupplier().getId(), supplier.getId())) {
                coffee.setDescription(coffeeRequest.getDescription());
                coffee.setStatus(coffeeRequest.getStatus());
                coffee.setPrice(coffeeRequest.getPrice());
                coffee.setImageLink(coffeeRequest.getImageLink());
            } else {
                coffee = new Coffee();
                coffee.setSupplier(supplier);
                coffee.setName(coffeeRequest.getName());
                coffee.setDescription(coffeeRequest.getDescription());
                coffee.setStatus(coffeeRequest.getStatus());
                coffee.setPrice(coffeeRequest.getPrice());
            }
        } else {
            coffee = new Coffee();
            coffee.setSupplier(supplier);
            coffee.setName(coffeeRequest.getName());
            coffee.setDescription(coffeeRequest.getDescription());
            coffee.setStatus(coffeeRequest.getStatus());
            coffee.setPrice(coffeeRequest.getPrice());
        }
        coffeeRepository.save(coffee);
        return ResponseEntity.ok("Coffee added successfully");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding coffee: " + e.getMessage());
    }
}

    private List<CoffeeRequest> readAndProcessFile(MultipartFile file) {
        List<CoffeeRequest> coffeeRequests = new ArrayList<>();

        try(InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                CoffeeRequest coffeeRequest = new CoffeeRequest();
                coffeeRequest.setSupplierId(Long.parseLong(row.getCell(0).getStringCellValue()));
                coffeeRequest.setName(row.getCell(1).getStringCellValue());
                coffeeRequest.setDescription(row.getCell(2).getStringCellValue());
                coffeeRequest.setStatus(Integer.parseInt(row.getCell(3).getStringCellValue()));
                coffeeRequest.setPrice(Float.parseFloat(row.getCell(4).getStringCellValue()));
                coffeeRequest.setImageLink(row.getCell(5).getStringCellValue());
                coffeeRequests.add(coffeeRequest);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return coffeeRequests;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importCoffeeFromFile(@RequestParam("file") MultipartFile file) {
        try {
            List<CoffeeRequest> coffeeRequests = readAndProcessFile(file);
            for (CoffeeRequest coffeeRequest : coffeeRequests) {
                addCoffee(coffeeRequest);
            }
            return ResponseEntity.ok("Coffee imported successfully");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing coffee from file: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{coffeeId}")
    public ResponseEntity<String> deleteCoffee(@PathVariable Long coffeeId) {
        try {
            Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow(
                    ()-> new RuntimeException("Coffee not found")
            );

            coffeeRepository.delete(coffee);
            return ResponseEntity.ok("Coffee deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting coffee: " + e.getMessage());
        }
    }
}
