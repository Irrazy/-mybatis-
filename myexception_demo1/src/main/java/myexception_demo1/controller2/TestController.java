package myexception_demo1.controller2;

import myexception_demo1.exception.common.ResultResponse;
import myexception_demo1.exception.enums.ResultCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: myexception_demo1
 * @description: 全局异常测试
 * @author: 作者
 * @create: 2022-01-25 10:34
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test1")
    public ResultResponse test1(){
        //使用断言方法，取代if判断，同时抛出异常
        //Assert.assertTrue(1 == 1,"Tset测试异常");
        int a=8;
        if(a>9){
            return ResultResponse.builder().status(ResultCodeEnum.SUCCESS.getCode()).message(ResultCodeEnum.SUCCESS.getMessage()).data("hello").build();
        }return ResultResponse.builder().status(ResultCodeEnum.FAILURE.getCode()).message(ResultCodeEnum.FAILURE.getMessage()).data("aaa").build();

    }



    @RequestMapping("/test3")
    public String test3(){
        //人为制造除数为0异常
        int a=1/0;
        return "bbb";
    }

}
