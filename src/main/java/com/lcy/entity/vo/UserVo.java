package com.lcy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private Integer id;
    private String name;
    private String username;
    private Integer identity;
    private Integer gender;
    private String phone;
    private String image;
    private List<String> perms;
    private List<String> roles;
}
