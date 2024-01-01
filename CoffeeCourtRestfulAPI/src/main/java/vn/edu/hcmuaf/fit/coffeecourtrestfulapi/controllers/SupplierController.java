package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order.SupplierDTO;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request.SupplierRequest;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.response.SupplierResponse;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Order;
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
    @PersistenceContext
    private EntityManager entityManager;
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
    @Transactional
    public ResponseEntity<String> addSupplier(@RequestBody SupplierRequest sr) {
        try{
            Supplier supp = new Supplier();
            supp.setId(sr.getId());
            supp.setEmail(sr.getEmail());
            supp.setName(sr.getName());
            supp.setPassword(sr.getPassword());
            supp.setPhone(sr.getPhone());
            supp.setStatus(sr.getStatus());
            supp.setPassword("123");
            supplierRepository.save(supp);
            Supplier attachedSup = entityManager.merge(supp);

            SupplierImage si  = new SupplierImage();
            si.setSupplierId(attachedSup.getId());
            si.setSupplier(attachedSup);
            si.setImageLink(sr.getImageLink());
            supplierImageRepository.save(si);
            return new ResponseEntity<>("Create supplier successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }


    }
    @Transactional
    @PutMapping ("/update/{id}")
    public ResponseEntity<String> updateSupplier(@PathVariable Long id,@RequestBody SupplierRequest sr) {
        try{

            Supplier supp = supplierRepository.findOneById(id);
            supp.setEmail(sr.getEmail());
            supp.setName(sr.getName());
            supp.setPassword(sr.getPassword());
            supp.setPhone(sr.getPhone());
            supp.setStatus(sr.getStatus());
            supplierRepository.save(supp);
            Supplier attachedSup = entityManager.merge(supp);
            SupplierImage si  = supplierImageRepository.findBySupplier(attachedSup);
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