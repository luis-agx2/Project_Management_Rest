package com.lag.projectmanagement.service.impl;

import com.lag.projectmanagement.dto.RoleDto;
import com.lag.projectmanagement.dto.request.RoleRequest;
import com.lag.projectmanagement.dto.request.RoleUpdate;
import com.lag.projectmanagement.entity.RoleEntity;
import com.lag.projectmanagement.exception.NotFoundException;
import com.lag.projectmanagement.mapper.RoleMapper;
import com.lag.projectmanagement.repository.RoleRepository;
import com.lag.projectmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDto> findAll() {
        return roleMapper.toListDto(this.roleRepository.findAll());
    }

    @Override
    public RoleDto findById(Long roleId) throws NotFoundException {
        RoleEntity roleDb = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role Not Found"));

        return roleMapper.toDto(roleDb);
    }

    @Override
    public RoleDto create(RoleRequest roleRequest) {
        RoleEntity roleToCreate = roleMapper.toEntity(roleRequest);
        roleToCreate.setCreatedAt(LocalDateTime.now());

        return roleMapper.toDto(roleRepository.save(roleToCreate));
    }

    @Override
    public RoleDto update(RoleUpdate roleUpdate, Long roleId) throws NotFoundException {
        RoleEntity roleDb = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role Not Found"));

        RoleEntity roleToEdit = roleMapper.toEntity(roleUpdate, roleDb);
        roleToEdit.setUpdatedAt(LocalDateTime.now());

        return roleMapper.toDto(roleRepository.save(roleToEdit));
    }

    @Override
    public RoleDto deleteById(Long roleId) throws NotFoundException {
        RoleEntity roleDb = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role Not Found"));

        roleRepository.deleteById(roleId);

        return roleMapper.toDto(roleDb);
    }
}
