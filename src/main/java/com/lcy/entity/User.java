package com.lcy.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 1病人，2医生，3管理人员
     */
    private Integer identity;

    /**
     * 1男，2女
     */
    private Integer gender;

    /**
     * 
     */
    private String phone;

    /**
     * 头像
     */
    private String image;

    /**
     * 部门id
     */
    @TableField("department_id")
    private Integer departmentId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_time;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;

    /**
     * 状态，0禁用，1启用
     */
    private Integer status;

}