package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order.SupplierDTO;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.SupplierRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.response.SupplierResponse;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Supplier;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.SupplierImage;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.SupplierImageRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierImageRepository supplierImageRepository;

    @GetMapping("/get")
    public List<SupplierResponse>getSuppliers() {
        List<SupplierResponse> result = new ArrayList<>();
        for(Supplier supp : supplierRepository.findAll()){
            String image = supplierImageRepository.findBySupplier(supp).getImageLink();
            SupplierResponse model = new SupplierResponse();
            model.setId(supp.getId());
            model.setName(supp.getName());
            model.setPhone(supp.getPhone());
            model.setPassword(supp.getPassword());
            model.setStatus(supp.getStatus());
            model.setEmail(supp.getEmail());
            model.setImageLink(image);
            result.add(model);
        }
        return result;
    }
    @GetMapping("/get/{id}")
    public SupplierResponse getSupplierDetail(@PathVariable Long id) {
        Supplier supp = supplierRepository.findOneById(id);
        if (supp != null){
            String image = supplierImageRepository.findBySupplier(supp).getImageLink();
            SupplierResponse model = new SupplierResponse();
            model.setId(supp.getId());
            model.setName(supp.getName());
            model.setPhone(supp.getPhone());
            model.setPassword(supp.getPassword());
            model.setStatus(supp.getStatus());
            model.setEmail(supp.getEmail());
            model.setImageLink(image);
           return model;
        }else{
            return null;
        }

    }
    @PostMapping ("/add")
    public ResponseEntity<String> addSupplier(@RequestBody SupplierRequest sr) {
        try{
            Supplier supp = new Supplier();
            supp.setId(sr.getId());
            supp.setEmail(sr.getEmail());
            supp.setName(sr.getName());
            supp.setPassword(sr.getPassword());
            supp.setPhone(sr.getPhone());
            supp.setStatus(sr.getStatus());
            supplierRepository.save(supp);

            SupplierImage si  = new SupplierImage();
            si.setSupplierId(sr.getId());
            si.setImageLink(sr.getImageLink());
            supplierImageRepository.save(si);
            return new ResponseEntity<>("Create supplier successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }


    }
    @PutMapping ("/update")
    public ResponseEntity<String> updateSupplier(@RequestBody SupplierRequest sr) {
        try{

            Supplier supp = supplierRepository.findOneById(sr.getId());
            supp.setEmail(sr.getEmail());
            supp.setName(sr.getName());
            supp.setPassword(sr.getPassword());
            supp.setPhone(sr.getPhone());
            supp.setStatus(sr.getStatus());
            supplierRepository.save(supp);

            SupplierImage si  = supplierImageRepository.findBySupplier(supp);
            si.setImageLink(sr.getImageLink());
            supplierImageRepository.save(si);
            return new ResponseEntity<>("Update supplier successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }


    }
    @GetMapping("/getAll")
    public List<SupplierDTO>  getAll() {
        return convertToListModel(supplierRepository.findAll());
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<String>  deleteSupplier(@PathVariable Long id) {
        try{
            Supplier supp = supplierRepository.findOneById(id);;
            supp.setStatus(-1);
            supplierRepository.save(supp);

            return new ResponseEntity<>("Delete supplier successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    public List<SupplierDTO> convertToListModel(List<Supplier> supplier){
        List<SupplierDTO> suppliers = new ArrayList<>();
        for(Supplier supp : supplier){

            String image = supplierImageRepository.findBySupplier(supp).getImageLink();
            SupplierDTO model = new SupplierDTO();
            model.setSupplierID(supp.getId());
            model.setImage(image);
            model.setName(supp.getName());
            suppliers.add(model);
        }

        return suppliers;
    }

}