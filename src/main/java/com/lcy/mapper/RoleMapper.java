package com.lcy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcy.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select role_id from user_role where user_id = #{userId}")
    List<Integer> selectRoleIdsByUserId(Integer userId);

    @Delete("delete from user_role where role_id = #{roleId}")
    Boolean deleteUserRole(Integer roleId);
}




