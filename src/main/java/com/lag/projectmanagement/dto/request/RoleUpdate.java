package com.lag.projectmanagement.dto.request;

public class RoleUpdate {
    private String name;

    public RoleUpdate() {
    }

    public RoleUpdate(
            String name) {
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
        return "RoleUpdate[" +
                "name='" + name + '\'' +
                ']';
    }
}
