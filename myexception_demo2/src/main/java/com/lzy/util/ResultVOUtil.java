package com.lzy.util;

import com.lzy.enums.ResultEnum;
import com.lzy.vo.ResultVO;

/**
 * @program: myexception_demo2
 * @description: 将要返回的数据处理成ResultVO的工具类
 * @author: 作者
 * @create: 2022-01-28 10:06
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultEnum.SUCCESS.getMsg());
        return resultVO;
    }

    public static ResultVO sucess(){
        return success(null);
    }

    //处理异常，带参：异常码及异常信息
    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    //处理自定义异常
    public static ResultVO error(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }


}
