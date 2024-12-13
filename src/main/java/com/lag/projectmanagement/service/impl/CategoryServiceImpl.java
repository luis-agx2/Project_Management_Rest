package com.lag.projectmanagement.service.impl;

import com.lag.projectmanagement.dto.CategoryDto;
import com.lag.projectmanagement.dto.request.CategoryRequest;
import com.lag.projectmanagement.dto.request.CategoryUpdate;
import com.lag.projectmanagement.entity.CategoryEntity;
import com.lag.projectmanagement.exception.NotFoundException;
import com.lag.projectmanagement.mapper.CategoryMapper;
import com.lag.projectmanagement.repository.CategoryRepository;
import com.lag.projectmanagement.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll() {
        return categoryMapper.toDtos(categoryRepository.findAll());
    }

    @Override
    public CategoryDto findOne(Long categoryId) throws NotFoundException {
        CategoryEntity categoryDb = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category Not Found"));

        return categoryMapper.toDto(categoryDb);
    }

    @Transactional
    @Override
    public CategoryDto create(CategoryRequest categoryRequest) {
        CategoryEntity categoryToCreate = categoryMapper.toEntity(categoryRequest);
        categoryToCreate.setCreatedAt(LocalDateTime.now());

        return categoryMapper.toDto(categoryRepository.save(categoryToCreate));
    }

    @Override
    public CategoryDto update(CategoryUpdate categoryUpdate, Long categoryId) throws NotFoundException {
        CategoryEntity categoryDb = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category Not Found"));

        CategoryEntity categoryToUpdate = categoryMapper.toEntity(categoryUpdate, categoryDb);
        categoryToUpdate.setUpdatedAt(LocalDateTime.now());

        return categoryMapper.toDto(categoryRepository.save(categoryToUpdate));
    }

    @Override
    public CategoryDto deleteById(Long categoryId) throws NotFoundException {
        CategoryEntity categoryDb = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category Not Found"));

        categoryRepository.deleteById(categoryId);

        return categoryMapper.toDto(categoryDb);
    }
}
