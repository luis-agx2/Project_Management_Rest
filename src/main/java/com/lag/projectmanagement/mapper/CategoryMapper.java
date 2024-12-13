package com.lag.projectmanagement.mapper;

import com.lag.projectmanagement.dto.CategoryDto;
import com.lag.projectmanagement.dto.request.CategoryRequest;
import com.lag.projectmanagement.dto.request.CategoryUpdate;
import com.lag.projectmanagement.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class CategoryMapper {
    public List<CategoryDto> toDtos(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream().map(this::toDto).toList();
    }

    public CategoryDto toDto(CategoryEntity categoryEntity) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(categoryEntity.getId());
        categoryDto.setName(categoryEntity.getName());

        return categoryDto;
    }

    public CategoryEntity toEntity(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName(categoryRequest.getName());

        return categoryEntity;
    }

    public CategoryEntity toEntity(CategoryUpdate categoryUpdate, CategoryEntity categoryEntity) {
        CategoryEntity category = new CategoryEntity();

        category.setId(categoryEntity.getId());
        category.setCreatedAt(categoryEntity.getCreatedAt());
        category.setName(checkValue(categoryEntity::getName, categoryUpdate::getName));

        return category;
    }

    private <T> T checkValue(Supplier<T> value, Supplier<T> newValue) {
        T data = newValue.get();

        return data != null ? newValue.get() : value.get();
    }
}
