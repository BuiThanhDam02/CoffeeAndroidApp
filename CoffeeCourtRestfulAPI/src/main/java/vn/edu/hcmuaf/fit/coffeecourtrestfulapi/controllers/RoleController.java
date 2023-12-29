package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Role;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/all")
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
}
