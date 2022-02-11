package com.lzy.exception;

import com.lzy.enums.ResultEnum;
import lombok.Getter;

/**
 * @program: myexception_demo2
 * @description: 自定义异常
 * @author: 作者
 * @create: 2022-02-09 15:00
 */
@Getter
public class DIYException extends RuntimeException {
    private Integer code;

    public DIYException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public DIYException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
