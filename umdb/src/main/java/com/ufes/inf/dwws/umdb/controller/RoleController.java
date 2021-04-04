package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.Role;
import com.ufes.inf.dwws.umdb.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class RoleController {

    RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping("/api/admin/role")
    @ResponseBody
    public ResponseEntity<Object> saveRole (@RequestBody Role role) {
        Role d = this.roleService.saveRole(role.getName());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Já existe um ator cadastrado com esse nome!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/admin/role")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.roleService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/admin/role/{id}")
    @ResponseBody
    public ResponseEntity<Object> findRole(@PathVariable Long id) {
        Role d = this.roleService.findRoleById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/admin/role/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteRole (@PathVariable Long id) {
        Role d = this.roleService.deleteRoleById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/admin/role/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateRole (@RequestBody Role role, @PathVariable Long id) {
        Role d = this.roleService.updateRoleById(id, role.getName());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
