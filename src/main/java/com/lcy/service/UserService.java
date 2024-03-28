package com.lcy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcy.common.R;
import com.lcy.entity.Role;
import com.lcy.entity.User;
import com.lcy.entity.vo.UserVo;

import java.util.List;


public interface UserService extends IService<User> {
    List<String> getRolesByUsername(String username);

    User getUserByUsername(String name);

    boolean insertUser(User user);

    R page(Integer page, Integer pageSize, String name, String username);

    Boolean removeUser(Integer id);

    public List<Role> getRolesByUserId(Integer userId);

    public List<Integer> getRolesIdByUserId(Integer userId);

    UserVo userInfo(Integer id);

    List<String> getPermsByUsername(String username);

    Boolean enableUser(Integer id);
}
