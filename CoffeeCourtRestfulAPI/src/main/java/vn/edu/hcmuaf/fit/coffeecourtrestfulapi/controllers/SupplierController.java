package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.order.SupplierDTO;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Supplier;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.SupplierImageRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierImageRepository supplierImageRepository;

    @GetMapping("/getAll")
    public List<SupplierDTO>  getAll() {
        return convertToListModel(supplierRepository.findAll());
    }

    public List<SupplierDTO> convertToListModel(List<Supplier> supplier){
        List<SupplierDTO> suppliers = new ArrayList<>();
        for(Supplier supp : supplier){

            String image = supplierImageRepository.findBySupplierId(supp.getId()).getImageLink();
            SupplierDTO model = new SupplierDTO();
            model.setSupplierID(supp.getId());
            model.setImage(image);
            model.setName(supp.getName());
            suppliers.add(model);
        }

        return suppliers;
    }

}