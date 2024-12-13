package com.lag.projectmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CategoryRequest {
    @NotBlank
    @Length(min = 4, max = 20)
    private String name;

    public CategoryRequest() {
    }

    public CategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryRequest[" +
                "name='" + name + '\'' +
                ']';
    }
}
