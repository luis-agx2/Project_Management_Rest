package com.lag.projectmanagement.controller;

import com.lag.projectmanagement.dto.request.CategoryRequest;
import com.lag.projectmanagement.dto.request.CategoryUpdate;
import com.lag.projectmanagement.exception.NotFoundException;
import com.lag.projectmanagement.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getOne(@PathVariable Long categoryId) throws NotFoundException {
        return ResponseEntity.ok(categoryService.findOne(categoryId));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(categoryRequest));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> update(@RequestBody CategoryUpdate categoryUpdate, @PathVariable Long categoryId) throws NotFoundException {
        return ResponseEntity.ok(categoryService.update(categoryUpdate, categoryId));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable Long categoryId) throws NotFoundException {
        return ResponseEntity.ok(categoryService.deleteById(categoryId));
    }
}
