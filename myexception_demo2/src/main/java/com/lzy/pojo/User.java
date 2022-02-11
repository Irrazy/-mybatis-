package com.lzy.pojo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @program: myexception_demo2
 * @description: 用户实体类
 * @author: 作者
 * @create: 2022-02-09 15:57
 */

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotNull(message = "姓名不为空")
    private String name;
    @Min(value = 18,message = "年龄应该>=18岁")
    private Integer age;
    @Email(message = "邮箱格式错误")
    private String email;
    private String sex;

    //嵌套必须加 @Valid，否则嵌套中的验证不生效
    @Valid
    @NotNull(message = "用户订单信息不能为空")
    @Size(min = 1,message = "至少要有一个属性")
    private List<UserOrder> userOrder;
}
