package com.lzy.controller;

import com.lzy.enums.ResultEnum;
import com.lzy.exception.DIYException;
import com.lzy.pojo.User;
import com.lzy.util.ResultVOUtil;
import com.lzy.vo.ResultVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @program: myexception_demo2
 * @description: 用户信息异常测试
 * @author: 作者
 * @create: 2022-02-09 16:02
 */
@RestController
@RequestMapping("/user")
@Validated
//@Validated是Spring’s JSR-303 规范提供的注解，@NotNull， @Min等都是其提供的
//@Valid是标准 JSR-303 规范提供的注解
//在检验 Controller 的入参是否符合规范时，使用 @Validated 或者 @Valid 在基本验证功能上没有太多区别
//两者区别：在分组、注解地方、嵌套验证等功能上（@Validated可分组，@Valid可嵌套验证，@Validated不可以注解在entity属性字段上）
public class UserInfoController {
    /**【参数校验异常一】
     *ConstraintViolationException异常统一处理测试
     * ！！！【校验url入参的异常 -- 没有实体入参，只能在单个入参的属性上加单个校验】
     * 1。用到注解@PathVariable 以及 @RequestParam 时 可直接标注在方法参数上,
     * 2.同时需要在controller类上加上 @Validated 进行标注 校验失败抛出异常: ConstraintViolationException
     */

    //Test1.用Spring Validation验证框架提供的@Validated来进行入参验证
           //在带参的方法里直接在每一个参数加上参数验证
    //其url为-- http://localhost:8080/user/getUser?name=aa&&age=15
    //与Test2方法相比，url一样，但是未使用@RequestParam，形参与form表单“name”一致的情况下可省略
    @RequestMapping("/getUser")
    public ResultVO<User> getUser(@NotNull(message = "name不为空")String name,
                                  @NotNull(message = "age不能为空")
                                  @Min(value = 18,message = "未满18岁") Integer age){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return ResultVOUtil.success(user);
    }

    //Test2.在带参的方法中用到注解@RequestParam ---- 其实和Test1是一样的
    //其url为-- http://localhost:8080/user/getUser2?name=kk&&age=14
    @RequestMapping("/getUser2")
    public ResultVO<User> getUser2(@RequestParam("name") String name,
                                   @Min(value = 18,message = "未满18岁") @RequestParam("age")Integer age){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return ResultVOUtil.success(user);
    }

    //Test3.在带参的方法中用到注解@PathVariable
    //其url为-- http://localhost:8080/user/getUser3/userName/Tom/userAge/16
    //两个参数使用@PathVariable，一个指定了（" "）,一个没有，因为当形参名与url占位符{传参名}一致的情况下可省略
    //但如果在方法参数名前面有swagger的@ApiParam注解或者其他的注解解释参数的话，则必须使用@PathVariable()注解进行赋值
    @RequestMapping("/getUser3/userName/{name}/userAge/{age}")
    public ResultVO<User> getUser3(@PathVariable("name") String name,
                                   @Min(value = 18,message = "未满18岁") @PathVariable Integer age){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return ResultVOUtil.success(user);
    }



    /**【参数校验异常二】
     *  MethodArgumentNotValidException--方法参数校验异常  统一处理测试
     *  用注解 @RequestBody 标注在实体参数上，校验失败抛出异常: MethodArgumentNotValidException
     *  Content-Type为application/json  ---> 实体类前必须要加一个@RequestBody注解才能获得到前端传来的参数
     */
    //url--  http://localhost:8080/user/saveUser,
    // 实体参数用json格式传入eg: {"name":"aa","age":20}
    //如果实体参数有嵌套实体，格式eg: {"name":"aa","age":20,[{"orderName":"apple","orderCount":20}]}
    @PostMapping("/saveUser")
    public ResultVO<User> saveUser(@RequestBody @Validated User user){
        user.setId(12L);
        return ResultVOUtil.success(user);
    }

    /**【参数校验异常三】
     * BindException--JSR303校验异常
     * Content-Type -- application/x-www-form-urlencoded ---> 即只能用form请求在url上入参，在形参实体前用@Validated/@Valid均可
     * （不能用@RequestBody注解，因为没有json实体参数）
     */
    //url -- http://localhost:8080/user/testBindUser?name='aa'&&age=15
    @PostMapping("/testBindUser")
    public ResultVO<User> testBindUser(@Valid User user){
        user.setId(11L);
        return ResultVOUtil.success(user);
    }

    /**
     * DIYException --- 自定义异常、未知异常统一处理
     */
    @GetMapping("/getUserInfo")
    public ResultVO<User> getUserInfo(@Max(value = 100,message = "id应该小于100") Long id){
        User user = new User();
        //自定义异常
        if(id<0){
            // 处理自定义的用户业务异常，都是手动抛异常：throw new
            throw new DIYException(ResultEnum.PARAM_ERROR);
        }
        //未知异常
        else if(id == 0){
            throw new RuntimeException("未知异常");
        }
        user.setId(id);
        user.setName("aa");
        user.setAge(19);
        return ResultVOUtil.success(user);
    }

}
