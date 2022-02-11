package myexception_demo1.exception;

import myexception_demo1.exception.common.BaseException;

/**
 * @program: myexception_demo1
 * @description: 自定义异常--继承刚刚的BaseException
 * @author: 作者
 * @create: 2022-01-24 17:48
 */
//这只是一个自定义异常类，还可以自定义许多其他类，如鉴权，登录。。。

public class UserException extends BaseException {
    private static final long serialVersionUID = -9153669060143538323L;

    /**
     * 异常构造方法 在使用时方便传入错误码和信息
     */
    public UserException() {
    }

    public UserException(Integer code,String message) {
        setCode(code);
        setMessage(message);
    }

    public UserException(String message) {
        getMessage();
        System.out.println("coming in..");
        System.out.println( getMessage());
    }

    public UserException(Integer codee) {
        getCode();

    }

}
