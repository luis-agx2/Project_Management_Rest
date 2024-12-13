package com.lag.projectmanagement.service;

import com.lag.projectmanagement.dto.CategoryDto;
import com.lag.projectmanagement.dto.request.CategoryRequest;
import com.lag.projectmanagement.dto.request.CategoryUpdate;
import com.lag.projectmanagement.exception.NotFoundException;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();

    CategoryDto findOne(Long categoryId) throws NotFoundException;

    CategoryDto create(CategoryRequest categoryRequest);

    CategoryDto update(CategoryUpdate categoryUpdate, Long categoryId) throws NotFoundException;

    CategoryDto deleteById(Long categoryId) throws NotFoundException;
}
