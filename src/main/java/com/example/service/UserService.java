package com.example.service;

import com.example.entity.Permission;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.PermissionRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String signup() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword(passwordEncoder.encode("123"));
        userRepository.save(user);
        return "<h1>User Created</h1>";
    }

    public String addRole(String name) {
        Role role = new Role();
        role.setRoleName(name);
        roleRepository.save(role);
        return "<h1>Role Added</h1>";
    }

    public String addPermission(String name) {
        Permission permission = new Permission();
        permission.setPermissionName(name);
        permissionRepository.save(permission);
        return "<h1>Permission Added</h1>";
    }

    public String addRolePermissionMapping(String roleName, String permissionName) {
        Permission permission = permissionRepository.findByPermissionName(permissionName);
        Role role = roleRepository.findByRoleName(roleName);
        role.getPermissions().add(permission);
        roleRepository.save(role);
        return "<h1>Mapping Added</h1>";
    }

    public String addUserRoleMapping(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
        return "<h1>Mapping Added</h1>";
    }

    public String getRoles(String email) {
        User user = userRepository.findByEmail(email);
        String output = "";
        for (Role role : user.getRoles()) {
            output += "<h1>" + role.getRoleName() + "</h1>";
            for (Permission permission : role.getPermissions()) {
                output += "<ul>" + permission.getPermissionName() + "</ul>";
            }
        }
        return output;
    }
}
