package myexception_demo1.exception.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import myexception_demo1.exception.enums.ResultCodeEnum;

/**
 * @program: myexception_demo1
 * @description: 全局异常信息统一返回类
 * @author: 作者
 * @create: 2022-01-25 09:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder与以上三个注解一起使用，可以构造器模式生成javaBean,效率更高
//eg:User user = User.builder().name("zhangsan").country(CountryEnum.china).build();
@Builder
public class ResultResponse<T> {
    /*
    返回状态码
     */
    //状态码即code：200/500
    private Integer status;

    /*
    返回信息
     */
    private String message;

    /*
    返回数据
     */
    private T data;

    /*
    token
     */
    private String token;

    public ResultResponse(ResultCodeEnum codeEnum) {
        this.status = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }
}
