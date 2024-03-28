package com.lcy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 路由显示信息
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaVo
{
    /**
     * 设置该路由在侧边栏中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    private Boolean hidden;

    private List<Integer> roles;
}

