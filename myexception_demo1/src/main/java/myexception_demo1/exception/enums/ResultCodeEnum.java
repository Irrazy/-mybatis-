package myexception_demo1.exception.enums;

/**
 * @program: myexception_demo1
 * @description: 返回信息对象中的枚举类
 * @author: 作者
 * @create: 2022-01-25 09:58
 */
public enum ResultCodeEnum {
    //系统通用异常
    SUCCESS(200,"请求成功"),
    FAILURE(500,"请求失败"),
    UNKNOWN(000,"未知异常"),
    NullPointer(100,"空指针异常"),
    ParamNull(2010,"参数体为空异常"),
    ParamException(2011,"参数校验异常"),

    //用户操作异常信息码
    OPERATE(666,"用户操作失败"),
    SAVE_USER_INFO_FAILED(2001,"保存用户信息失败"),
    GET_USER_INFO_FAILED(2002,"获取用户信息失败");



    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
