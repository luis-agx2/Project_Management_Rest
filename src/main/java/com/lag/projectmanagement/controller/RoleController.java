package com.lag.projectmanagement.controller;

import com.lag.projectmanagement.dto.request.RoleRequest;
import com.lag.projectmanagement.dto.request.RoleUpdate;
import com.lag.projectmanagement.exception.NotFoundException;
import com.lag.projectmanagement.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/admin/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @GetMapping("/{roleId}")
    public ResponseEntity<?> getOneById(@PathVariable Long roleId) throws NotFoundException {
        return ResponseEntity.ok(roleService.findById(roleId));
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(roleRequest));
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PutMapping("/{roleId}")
    public ResponseEntity<?> update(@RequestBody RoleUpdate roleUpdate, @PathVariable Long roleId) throws NotFoundException {
        return ResponseEntity.ok(roleService.update(roleUpdate, roleId));
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> deleteById(@PathVariable Long roleId) throws NotFoundException {
        return ResponseEntity.ok(roleService.deleteById(roleId));
    }
}
