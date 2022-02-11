package myexception_demo1.exception.common;

/**
 * @program: myexception_demo1
 * @description: 统一返回异常基类--自定义
 * @author: 作者
 * @create: 2022-01-24 17:03
 */
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 4609901760178739600L;

    //错误代码
    protected Integer code;
    //错误信息
    protected String message;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
