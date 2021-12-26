package com.example.CrudAppSecurBoor.service;

import com.example.CrudAppSecurBoor.models.Role;
import com.example.CrudAppSecurBoor.repository.RoleRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRoleByName(String role) {
        return roleRepo.findByRole(role);
    }
}
