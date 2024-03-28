package com.lcy.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {
    private Integer code; //编码：200成功，500和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    public static <T> R<T> success(String msg) {
        R<T> r = new R<T>();
        r.msg = msg;
        r.code = 200;
        return r;
    }

    public static <T> R<T> success(T data,String msg) {
        R<T> r = new R<T>();
        r.msg = msg;
        r.code = 200;
        r.data = data;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 500;
        return r;
    }
}