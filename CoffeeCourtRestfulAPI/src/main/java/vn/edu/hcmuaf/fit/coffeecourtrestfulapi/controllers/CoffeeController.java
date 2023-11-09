package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Coffee;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.CoffeeRepository;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping("/all")
    public List<Coffee> getAll() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/search")
    public List<Coffee> searchByName(@RequestParam String name) {
        return coffeeRepository.findByNameContaining(name);
    }

    @GetMapping("/bySupplierId")
    public List<Coffee> getBySupplierId(@RequestParam("supplierId") int supplierId){
        return coffeeRepository.findBySupplierId(supplierId);
    }


}
