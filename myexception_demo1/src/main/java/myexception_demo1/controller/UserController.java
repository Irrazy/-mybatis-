package myexception_demo1.controller;

import myexception_demo1.exception.UserException;
import myexception_demo1.exception.common.ResultResponse;
import myexception_demo1.exception.enums.ResultCodeEnum;
import myexception_demo1.pojo.User;
import myexception_demo1.serviceImpl.UserServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @program: tk-generator-lombok
 * @description: userController
 * @author: 作者
 * @create: 2021-12-23 14:17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/selectAll")
    public List<User> selectAll(){
        List<User> list1=userServiceImpl.selectAll();
        list1.forEach(System.out::println);
        return list1;
    }


    @RequestMapping("/findId")
    public User findById(@Valid @RequestParam("id") Long id)  {
        User user=userServiceImpl.findById(id);
        System.out.println(user);
        return  user;
    }

    //测试1--参数为空
    @RequestMapping("/findAccount/{account}")
    public ResultResponse findAccount(@Valid @PathVariable String account)  {
        System.out.println("coming in1....");
        return new ResultResponse(ResultCodeEnum.SUCCESS);
    }

    //测试2--参数校验+自定义参数异常
    @Validated
    @RequestMapping("/findAccount2/{account}")
    public ResultResponse findByAccount2(@Valid @PathVariable String account)  {
        System.out.println("coming in1....");
        if (account == null || "".equals(account) || "null".equals(account)){
            System.out.println("coming in2....");
            throw new UserException();
        }
        return new ResultResponse(ResultCodeEnum.SUCCESS);
    }

    //测试3--参数体为空异常
    @RequestMapping("/add")
    public ResultResponse add(@Valid @RequestBody User user){
       return new ResultResponse(ResultCodeEnum.SUCCESS);
    }



    @RequestMapping("/insert")
    public String insert(User user){
        //user.setId(null);
        user.setAccount("Lily");
        user.setPassword("678976");
        int a=userServiceImpl.insertSelective(user);
        //int a=userServiceImpl.insert(user);
        System.out.println(a); //成功的话a的值是1
        return  "sucessful";
    }

    @RequestMapping("/updateByPrimaryKey")
    public String updateByPrimaryKey(){
        User user1=userServiceImpl.findById(27L);
        user1.setAccount("Lily4");
        user1.setPassword("0000");
        int a = userServiceImpl.updateByPrimaryKey(user1);
        System.out.println(a);
        if(a==1){
            return  "sucessful";
        }else{
            return  "erro";
        }
    }

    @RequestMapping("/deleteById")
    public String deleteById(){
        int a = userServiceImpl.delete(32L);
        System.out.println(a);
        if(a==1){
            return  "sucessful";
        }else{
            return  "erro";
        }

    }





}
