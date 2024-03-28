package com.lcy.controller;


import com.lcy.common.R;
import com.lcy.entity.vo.RouterVo;
import com.lcy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    //@RequiresRoles(value = {"超级管理员","系统管理员"},logical = Logical.OR)
    @GetMapping("/routes")
    public R<List<RouterVo>> findUserMenuListByUserId(Integer userId){
        return R.success(menuService.findUserMenuListByUserId(userId),"成功");
    }
}
