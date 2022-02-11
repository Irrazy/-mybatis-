package com.lzy.enums;

import lombok.Getter;

/**
 * @program: myexception_demo2
 * @description: 封装状态码和信息的枚举类
 * @author: 作者
 * @create: 2022-01-28 10:03
 */
@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    FAILURE(-1,"系统异常"),
    PARAM_ERROR(300,"参数不正确"),
    RESULT_NOT_EXIST(202,"查询结果不存在");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
