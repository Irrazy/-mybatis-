package com.lzy.exceptionHandler;

import com.lzy.enums.ResultEnum;
import com.lzy.exception.DIYException;
import com.lzy.util.ResultVOUtil;
import com.lzy.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: myexception_demo2
 * @description: 全局统一异常处理类
 * 该类的处理逻辑:
 *          （1）对请求参数异常做单独处理
 *          （2）我们自定义的异常做单独处理
 *          （3）对可能出现的未知异常进行单独处理
 * @author: 作者
 * @create: 2022-02-09 11:01
 */
@RestControllerAdvice(basePackages = "com.lzy.controller")
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 可能出现的未知异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResultVO handler(Exception e){
        log.error("【系统异常】{}",e);
        return ResultVOUtil.error(ResultEnum.FAILURE);
    }


    /**【参数校验异常一】
     * 参数异常处理 -- ConstraintviolationException()
     * 用于处理类似http://localhost:8080/user/getUser?age=30&name=yoyo请求中age和name的校验引发的异常
     *
     */
    /**
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST) //http请求状态码
    public ResultVO urlParamExceptionHandler(ConstraintViolationException e){
        log.error("【请求参数异常】：{}",e);
        //收集所有错误信息
        List<String> errorMsg = e.getConstraintViolations()
                .stream()
                .map(s ->s.getMessage())
                .collect(Collectors.toList());
        return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),errorMsg.toString());
    }

    /**【参数校验异常二+三】
     * 参数异常 --MethodArgumentNotValidException和 BindException
     * MethodArgumentNotValidException -- 用于处理请求参数为实体类时校验引发的异常 --Content-Type为application/json
     * BindException -- 用于处理请求参数为实体类时校验引发的异常
     * 1.BindException --Content-Type为application/x-www-form-urlencoded
     * 2.BindException --仅对于表单提交有效，对于以json格式提交将会失效
     */
    /**
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO bodyExceptionHandler(Exception e){
        log.error("【请求参数异常】：{}",e);
        BindingResult bindingResult = null;
        if(e instanceof MethodArgumentNotValidException){
            //instanceof关键字：测试左边对象是否是右边的类/该类子类创建的对象，返回boolean类型
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            bindingResult = ex.getBindingResult();
        }else{
            BindException ex = (BindException) e;
            bindingResult = ex.getBindingResult();
        }

        if(bindingResult != null){
            //收集所有错误信息
            //FieldErrors表单验证错误
            List<String> errorMsg = bindingResult.getFieldErrors()
                    .stream()
                    .map(s ->s.getDefaultMessage())
                    .collect(Collectors.toList());
        }
        return ResultVOUtil.error(ResultEnum.PARAM_ERROR);
    }

    /**
     * ************将三种参数校验异常合并到一个方法种**************************
     */
    @ExceptionHandler(value = {ConstraintViolationException.class,MethodArgumentNotValidException.class,BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO allParamExceptionHandler(Exception e){
        //参数异常类型一：ConstraintViolationException
        if(e instanceof ConstraintViolationException){
            ConstraintViolationException ex = (ConstraintViolationException)e;
            log.error("【方法参数约束校验异常】：{}",ex);
            //收集所有错误信息
            List<String>  errorMsg= ex.getConstraintViolations().stream().map(s ->s.getMessage()).collect(Collectors.toList());
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),errorMsg.toString());
        }else if (e instanceof MethodArgumentNotValidException){
            //因为异常类型二和三的异常返回结果都为e.getBindingResult()
            //而且MethodArgumentNotValidException是BindException的子类
            BindingResult br = null;
            //参数异常类型二：MethodArgumentNotValidException
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException)e;
            log.error("【参数体校验异常】：{}",ex);
            br = ex.getBindingResult();
            if(br !=null){
                //收集所有错误信息
                List<String> errorMsg = br.getFieldErrors().stream().map(s ->s.getDefaultMessage()).collect(Collectors.toList());
                return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),errorMsg.toString());
            }
        }
        //异常三：BindException
        log.error("【请求参数异常】：{}",e);
       // return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),e.getMessage());
        return ResultVOUtil.error(ResultEnum.PARAM_ERROR);
    }

    /**
     * 自定义异常 -- 自定义异常一般不要设置为ERROR级别,因为我们用自定义的异常主要是为了辅助我们处理业务逻辑,
     *           -- 它们其实并不能被真正当作异常来看待
     */
    @ExceptionHandler(value = DIYException.class)
    @ResponseStatus(HttpStatus.IM_USED)//此状态码表示服务器已满足资源请求
    public ResultVO DIYExceptionHandler(DIYException e){
        log.warn("【自定义异常】",e);
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }


}
