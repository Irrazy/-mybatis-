package myexception_demo1.exception;

import lombok.extern.slf4j.Slf4j;
import myexception_demo1.exception.common.ResultResponse;
import myexception_demo1.exception.enums.ResultCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @program: myexception_demo1
 * @description: 全局异常拦截器
 * @author: 作者
 * @create: 2022-01-24 15:19
 */
@Slf4j
@RestControllerAdvice(basePackages = "myexception_demo1.controller")//捕获指定包下的异常
//@RestControllerAdvice(assignableTypes = {UserController.class})//捕获指定类的异常
//此注解= @ControllerAdvice + @ResponseBody
//默认处理所有 controller 中被 @RequestMapping注解的方法
//处理全局异常：一旦项目中发生了异常，就会进入使用了RestControllerAdvice注解类中使用了ExceptionHandler注解的方法
public class GlobalMyExceptionHandler {
    /**
     处理自定义的用户业务异常，都是手动抛异常：throw new
     */
     //2.获取用户信息失败异常
     @ExceptionHandler(value = UserException.class)
     public ResultResponse handlerException2(UserException e){
     //log.error(getStackTrace (e));
     System.out.println("获取用户信息失败,用户为null");
     return ResultResponse.builder().status(ResultCodeEnum.GET_USER_INFO_FAILED.getCode()).message(ResultCodeEnum.GET_USER_INFO_FAILED.getMessage()).build();
     }


    /**
     * 自定义异常处理器--参数错误
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public ResultResponse myParamExceptionHandler(UserException e){
        log.error(getStackTrace (e));
        log.error("出错了",e);
        System.out.println("参数中出现了问题");
        /*
        //判断异常中是否有错误信息，如果存在使用异常中的消息，否则使用默认消息
         */
        if(!StringUtils.isEmpty(e.getMessage())){
            System.out.println("异常中本身就存在错误信息");
            return ResultResponse.builder().status(ResultCodeEnum.ParamNull.getCode()).message(e.getMessage()).build();
        }
        System.out.println("异常中不存在错误信息，使用自定义异常提醒消息");
        return new ResultResponse(ResultCodeEnum.ParamNull);
    }



    //捕获空指针异常
    @ExceptionHandler(value = NullPointerException.class)
    public ResultResponse handlerException(NullPointerException e){
        log.error(getStackTrace (e));
        return ResultResponse.builder().status(ResultCodeEnum.NullPointer.getCode()).message(e.getMessage()).build();
    }

    //捕获除以上外其他其他异常
    @ExceptionHandler(Exception.class)
    public ResultResponse handlerException(Exception e){
        log.error(getStackTrace (e));
        log.error(e.getMessage(),e);
        System.out.println("系统异常");
        return ResultResponse.builder().status(ResultCodeEnum.UNKNOWN.getCode()).message(ResultCodeEnum.UNKNOWN.getMessage()).build();
    }



    //1.操作用户失败异常

    @ExceptionHandler(value = UserException.class)//操作用户失败异常
    public ResultResponse handlerException(UserException e){
        log.error(getStackTrace (e));
        System.out.println("用户操作失败,请具体排查");
        return ResultResponse.builder().status(ResultCodeEnum.OPERATE.getCode()).message(ResultCodeEnum.OPERATE.getMessage()).build();
    }

    //获取堆栈信息
    private String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try{
            throwable.printStackTrace(pw);
            return "\n"+sw.toString();
        }finally {
            pw.close();
        }
    }




    /**
     * 缺少参数体的请求异常处理器
     */
    /**无效
     @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler(HttpMessageNotReadableException.class)
     public ResultResponse paramBodyNullExceptionHandler(HttpMessageNotReadableException e){
     log.error("",e);
     System.out.println(ResultCodeEnum.ParamNull.getMessage());
     return ResultResponse.builder().status(ResultCodeEnum.ParamNull.getCode()).message(ResultCodeEnum.ParamNull.getMessage()).build();
     }



     /**
      * 参数校验异常处理
     */
    /**无效
     @ResponseStatus(HttpStatus.BAD_REQUEST) //获取状态码
     @ExceptionHandler({MethodArgumentNotValidException.class, BindingException.class})
     public ResultResponse bodyparamExceptionHandler(MethodArgumentNotValidException e){
     System.out.println("come in 参数校验异常处理");
     //获取绑定了参数校验的参数异常信息
     BindingResult bindingResult = e.getBindingResult();
     //判断异常中是否有其他错误信息，如果有就使用其他错误提示信息，没有使用默认信息
     if(bindingResult.hasErrors()){
     List<ObjectError> errors = bindingResult.getAllErrors();
     if(!errors.isEmpty()){
     // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
     FieldError fieldError = (FieldError) errors.get(0);
     String msg = fieldError.getDefaultMessage();
     return ResultResponse.
     builder().
     status(ResultCodeEnum.ParamException.getCode()).
     message(msg).build();
     }
     }
     log.error("参数异常！msg:->" , e);
     return new ResultResponse(ResultCodeEnum.ParamNull);
     }
     **/

}
