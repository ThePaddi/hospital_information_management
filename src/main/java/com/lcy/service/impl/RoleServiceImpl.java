package com.lcy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcy.common.R;
import com.lcy.entity.Role;
import com.lcy.mapper.RoleMapper;
import com.lcy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询用户角色id
     * @param userId
     * @return
     */
    @Override
    public List<Integer> getRolesByUserId(Integer userId){
        List<Integer> userRoles = null;
        userRoles = roleMapper.selectRoleIdsByUserId(userId);
        return userRoles;
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @Override
    public Boolean insertRole(Role role) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getName,role.getName());
        int count = this.count(queryWrapper);
        if (count > 0){
            return false;
        }
        this.save(role);
        return true;
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    public R page(Integer page, Integer pageSize, String name) {
        IPage<Role> p = new Page<>(page,pageSize);
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Role::getName, name);
        roleMapper.selectPage(p,queryWrapper);
        return R.success(p.getRecords(),"查询成功！");
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Boolean deleteRole(Integer id) {
        Role role = this.getById(id);
        if (role == null){
            return false;
        }
        role.setStatus(0);
        this.updateById(role);
        deleteUserRole(id);
        return true;
    }

    /**
     * 删除角色是必须删除拥有这些角色信息的记录
     * @param id
     * @return
     */
    @Override
    public Boolean deleteUserRole(Integer id){
        Boolean flag = roleMapper.deleteUserRole(id);
        if (flag){
            return true;
        }
        return false;
    }

    /**
     * 启用角色
     * @param id
     * @return
     */
    @Override
    public Boolean enableRole(Integer id) {
        Role role = this.getById(id);
        if (role == null){
            return false;
        }
        role.setStatus(1);
        this.updateById(role);
        return true;
    }
}




