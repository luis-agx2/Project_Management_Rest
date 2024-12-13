package com.lag.projectmanagement.dto.request;

public class CategoryUpdate {
    private String name;

    public CategoryUpdate() {
    }

    public CategoryUpdate(String name) {
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
        return "CategoryUpdate[" +
                "name='" + name + '\'' +
                ']';
    }
}
