package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Role;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.RoleName;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.IRoleRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.IRoleService;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
