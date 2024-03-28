package com.lcy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcy.common.R;
import com.lcy.entity.Role;
import com.lcy.entity.User;
import com.lcy.entity.vo.UserVo;
import com.lcy.mapper.UserMapper;
import com.lcy.service.MenuService;
import com.lcy.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuService menuService;

    /**
     * 注册/新增用户
     * @param user
     * @return
     */
    @Override
    public boolean insertUser(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        String username = user.getUsername();
        queryWrapper.eq(User::getUsername,username);
        if (this.count(queryWrapper) != 0 || username.length() > 30){
            return false;
        }
        String password = user.getPassword();
        if (password.length() > 16){
            return false;
        }
        Md5Hash md5Hash = new Md5Hash(password,"salt",3);
        user.setPassword(md5Hash.toHex());
        this.save(user);
        return true;
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @param username
     * @return
     */
    @Override
    public R page(Integer page, Integer pageSize, String name, String username) {
        IPage<User> p = new Page<>(page,pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getStatus,1);
        queryWrapper.like(name != null,User::getName,name).or()
                .like(username != null,User::getUsername,username);
        this.page(p,queryWrapper);
        return R.success(p.getRecords(),"查询成功！");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public Boolean removeUser(Integer id) {
        User user = this.getById(id);
        if (user != null){
            user.setStatus(0);
            this.updateById(user);
            return true;
        }
        return false;
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User user = this.getOne(queryWrapper);
        return user;
    }

    /**
     * 根据用户名查询用户角色
     * @param username
     * @return
     */
    @Override
    public List<String> getRolesByUsername(String username){
        List<String> userRoles = null;
        userRoles = userMapper.getRolesByUsername(username);
        return userRoles;
    }

    /**
     * 根据用户id查询用户角色
     * @param userId
     * @return
     */
    public List<Role> getRolesByUserId(Integer userId){
        List<Role> userRoles = null;
        userRoles = userMapper.getUserRoles(userId);
        return userRoles;
    }

    /**
     * 根据用户id查询用户角色id
     * @param userId
     * @return
     */
    public List<Integer> getRolesIdByUserId(Integer userId){
        List<Integer> userRolesId = null;
        userRolesId = userMapper.getUserRolesId(userId);
        return userRolesId;
    }

    /**
     * 查询登录用户信息
     * @param id
     * @return
     */
    @Override
    public UserVo userInfo(Integer id) {
        UserVo userInfo = new UserVo();
        User user = this.getById(id);
        if (user.getStatus().intValue() == 0){
            return null;
        }
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setUsername(user.getUsername());
        userInfo.setIdentity(user.getIdentity());
        userInfo.setGender(user.getGender());
        userInfo.setImage(user.getImage());
        userInfo.setPhone(user.getPhone());
        userInfo.setRoles(this.getRolesByUsername(user.getUsername()));
        userInfo.setPerms(this.menuService.findMenusByUserId(id));
        return userInfo;
    }

    /**
     * 根据用户名查询用户权限
     * @param username
     * @return
     */
    @Override
    public List<String> getPermsByUsername(String username) {
        List<String> perms = userMapper.getPermsByUsername(username);
        return perms;
    }

    /**
     * 启用用户
     * @param id
     * @return
     */
    @Override
    public Boolean enableUser(Integer id) {
        User user = this.getById(id);
        if (user != null){
            user.setStatus(1);
            this.updateById(user);
            return true;
        }
        return false;
    }
}




