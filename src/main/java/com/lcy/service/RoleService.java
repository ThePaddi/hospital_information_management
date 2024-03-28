package com.lcy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcy.common.R;
import com.lcy.entity.Role;

import java.util.List;


public interface RoleService extends IService<Role> {
    List<Integer> getRolesByUserId(Integer userId);

    Boolean insertRole(Role role);

    R page(Integer page, Integer pageSize, String name);

    Boolean deleteRole(Integer id);

    Boolean deleteUserRole(Integer id);

    Boolean enableRole(Integer id);
}
