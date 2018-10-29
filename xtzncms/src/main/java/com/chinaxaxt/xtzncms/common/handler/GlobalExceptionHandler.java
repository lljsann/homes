package com.chinaxaxt.xtzncms.common.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.chinaxaxt.xtzncms.common.exception.CustomException;
import com.chinaxaxt.xtzncms.common.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author 陕西协通智能科技有限公司
 *
 */
@ControllerAdvice
@ResponseBody 
public class GlobalExceptionHandler {
	
	/**
	 * 异常捕获
	 * @param req
	 * @param e
	 * @return
	 */
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e)  {
    	//异常后期做记录维护
        e.printStackTrace();
        if(e instanceof CustomException) {
        	return JSON.toJSONString(ResponseVO.error(e.getMessage()));
        }else {
        	return JSON.toJSONString(ResponseVO.error("未知异常，请与管理员联系"));
        }
        
    }
    
}
