package com.lcy.common;

import com.lcy.entity.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    public static List<Menu> buildTree(List<Menu> menuList){
        List<Menu> trees = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getParentId().longValue() == 0){
                trees.add(findChildren(menu,menuList));
            }
        }
        return trees;
    }

    public static Menu findChildren(Menu menu,List<Menu> treeNodes){
        menu.setChildren(new ArrayList<Menu>());

        for (Menu it : treeNodes) {
            if(menu.getId().longValue() == it.getParentId().longValue()) {
                if (menu.getChildren() == null) {
                    menu.setChildren(new ArrayList<>());
                }
                menu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return menu;
    }
}