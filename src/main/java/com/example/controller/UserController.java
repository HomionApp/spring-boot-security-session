package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired private AuthenticationManager authenticationManager;

    @Autowired private UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return userService.signup();
    }

    @GetMapping("/signin")
    public String signin() {
        System.out.println();
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                "test@gmail.com", "123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "TRUE";
    }

    @PreAuthorize("hasAuthority('CAN_READ_DATA')")
    @GetMapping("/")
    public String home() {
        return "<h1>HOME</h1>";
    }

    @PreAuthorize("hasAuthority('CAN_READ_DATAA')")
    @GetMapping("/addRole/{name}")
    public String addRole(@PathVariable() String name) {
        return userService.addRole(name);
    }

    @GetMapping("/addPermission/{name}")
    public String addPermission(@PathVariable() String name) {
        return userService.addPermission(name);
    }

    @GetMapping("/addRolePermissionMapping/{roleName}/{permissionName}")
    public String addRolePermissionMapping(
            @PathVariable() String roleName, @PathVariable() String permissionName) {
        return userService.addRolePermissionMapping(roleName, permissionName);
    }

    @GetMapping("/addUserRoleMapping/{email}/{roleName}")
    public String addUserRoleMapping(
            @PathVariable() String email, @PathVariable() String roleName) {
        return userService.addUserRoleMapping(email, roleName);
    }

    @PreAuthorize("hasAuthority('CAN_READ_DATA')")
    @GetMapping("/getRoles/{email}")
    public String getRoles(@PathVariable() String email) {
        return userService.getRoles(email);
    }

    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
}
