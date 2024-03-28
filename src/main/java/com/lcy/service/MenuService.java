package com.lcy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcy.entity.Menu;
import com.lcy.entity.vo.RouterVo;

import java.util.List;


public interface MenuService extends IService<Menu> {
    List<Integer> getMenuIdsByRoleId(Integer roleId);

    public List<RouterVo> findUserMenuListByUserId(Integer userId);

    List<String> findMenusByUserId(Integer userId);
}
