package com.example.CrudAppSecurBoor.service;

import com.example.CrudAppSecurBoor.models.Role;
import com.example.CrudAppSecurBoor.models.User;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByName(String role);
}
