package com.lcy.controller;


import com.lcy.common.R;
import com.lcy.entity.Role;
import com.lcy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 新增角色
     * @param role
     * @return
     */
    //@RequiresPermissions("role:add")
    @PostMapping
    public R insertRole(@RequestBody Role role){
        Boolean flag = roleService.insertRole(role);
        if (flag){
            return R.success("添加成功！");
        }
        return R.error("添加失败！");
    }

    /**
     * 分页查询角色信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    //@RequiresRoles(value = {"系统管理员","超级管理员"},logical = Logical.OR)
    @GetMapping("/page")
    public R page(@RequestParam(value = "page",defaultValue = "1") Integer page,
                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                 String name){
        return roleService.page(page,pageSize,name);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    //@RequiresPermissions("role:delete")
    @DeleteMapping("/{id}")
    public R deleteRole(@PathVariable Integer id){
        Boolean flag = roleService.deleteRole(id);
        if (flag){
            return R.success("删除成功！");
        }
        return R.error("删除失败！");
    }

    /**
     * 启用角色
     * @param id
     * @return
     */
    //@RequiresPermissions("role:edit")
    @PutMapping("enable")
    public R enableRole(Integer id){
        Boolean flag = roleService.enableRole(id);
        if (flag){
            return R.success("修改角色状态成功！");
        }
        return R.error("修改角色状态失败！");
    }

    /**
     * 根据角色id查询角色
     * @param id
     * @return
     */
    //@RequiresRoles(value = {"系统管理员","超级管理员"},logical = Logical.OR)
    @GetMapping("/{id}")
    public R<Role> findById(@PathVariable Integer id){
        Role role = roleService.getById(id);
        return R.success(role);
    }
}
