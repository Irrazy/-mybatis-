package com.lzy.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: myexception_demo2
 * @description: 封装返回数据的数据格式
 * @author: 作者
 * @create: 2022-01-28 10:07
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -811254775957703766L;
    /*
        错误码
         */
    private Integer code;
    /*
    提示信息
     */
    private String msg;
    /*
    具体内容
     */
    private T data;
}
