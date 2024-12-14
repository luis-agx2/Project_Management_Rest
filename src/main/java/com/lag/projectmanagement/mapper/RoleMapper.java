package com.lag.projectmanagement.mapper;

import com.lag.projectmanagement.dto.RoleDto;
import com.lag.projectmanagement.dto.request.RoleRequest;
import com.lag.projectmanagement.dto.request.RoleUpdate;
import com.lag.projectmanagement.entity.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class RoleMapper {
    public List<RoleDto> toListDto(List<RoleEntity> roleEntities) {
        return roleEntities.stream().map(this::toDto).toList();
    }

    public RoleDto toDto(RoleEntity roleEntity) {
        RoleDto role = new RoleDto();

        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());

        return role;
    }

    public RoleEntity toEntity(RoleRequest roleRequest) {
        RoleEntity role = new RoleEntity();

        role.setName(roleRequest.getName());

        return role;
    }

    public RoleEntity toEntity(RoleUpdate roleUpdate, RoleEntity roleDb) {
        RoleEntity role = new RoleEntity();

        role.setId(roleDb.getId());
        role.setCreatedAt(roleDb.getCreatedAt());
        role.setName(checkValue(roleDb::getName, roleUpdate::getName));

        return role;
    }

    private <T> T checkValue(Supplier<T> value, Supplier<T> newValue) {
        return newValue.get() != null ? newValue.get() : value.get();
    }
}
