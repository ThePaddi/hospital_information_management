package com.lcy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcy.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("select menu_id from role_menu where role_id = #{roleId}")
    List<Integer> getMenuIdsByRoleId(Integer roleId);

    @Select("select\n" +
            "distinct\n" +
            "m.id,m.parent_id,m.tree_path,m.`name`,m.type,m.path,m.component,m.perm,m.visible,m.sort,m.icon,m.redirect,m.create_time,m.update_time,m.always_show,m.keep_alive\n" +
            "from menu m\n" +
            "inner join role_menu rm on rm.menu_id = m.id\n" +
            "inner join user_role ur on ur.role_id = rm.role_id\n" +
            "where\n" +
            "ur.user_id = #{userId}" +
            "and m.type != 4")
    List<Menu> findMenuListByUserId(Integer userId);

    @Select("select\n" +
            "distinct\n" +
            "m.perm\n"+
            "from menu m\n" +
            "inner join role_menu rm on rm.menu_id = m.id\n" +
            "inner join user_role ur on ur.role_id = rm.role_id\n" +
            "where\n" +
            "ur.user_id = #{userId}\n" +
            "and m.perm is not null")
    List<String> findMenusByUserId(Integer userId);
}




