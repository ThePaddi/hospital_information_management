package com.lcy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcy.entity.Role;
import com.lcy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select r.id from user u,role r, user_role ur where u.id = ur.user_id and ur.role_id = r.id and u.username = #{username};")
    List<String> getRolesByUsername(String username);

    @Select("select r.id,r.name,r.code,r.sort,r.data_scope,r.role_remark,r.create_time,r.update_time,r.status from user u,role r, user_role ur where u.id = ur.user_id and ur.role_id = r.id and u.id = #{userId};")
    List<Role> getUserRoles(Integer userId);

    @Select("select r.id from user u,role r, user_role ur where u.id = ur.user_id and ur.role_id = r.id and u.id = #{userId};")
    List<Integer> getUserRolesId(Integer userId);

    @Select("select distinct\n" +
            "m.perm\n" +
            "from menu m \n" +
            "inner join role_menu rm on rm.menu_id = m.id \n" +
            "inner join user_role ur on ur.role_id = rm.role_id \n" +
            "inner join user u on u.id = ur.user_id \n" +
            "where \n" +
            "u.username = #{username} \n" +
            "and m.perm is not null")
    List<String> getPermsByUsername(String username);
}




