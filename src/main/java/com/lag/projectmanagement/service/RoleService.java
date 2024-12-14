package com.lag.projectmanagement.service;

import com.lag.projectmanagement.dto.RoleDto;
import com.lag.projectmanagement.dto.request.RoleRequest;
import com.lag.projectmanagement.dto.request.RoleUpdate;
import com.lag.projectmanagement.exception.NotFoundException;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();

    RoleDto findById(Long roleId) throws NotFoundException;

    RoleDto create(RoleRequest roleRequest);

    RoleDto update(RoleUpdate roleUpdate, Long roleId) throws NotFoundException;

    RoleDto deleteById(Long roleId) throws NotFoundException;
}
