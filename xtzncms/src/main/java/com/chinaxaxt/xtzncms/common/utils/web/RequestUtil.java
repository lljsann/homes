package com.chinaxaxt.xtzncms.common.utils.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求工具类
 * @author 吴佳涛
 *
 */
@SuppressWarnings("unchecked")
public class RequestUtil {

	public static Map<String, String> getHeadersInfo(HttpServletRequest request) {

	    Map<String, String> map = new HashMap<String, String>(0);

	    Enumeration<?> headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        map.put(key, value);
	    }

	    return map;
	  }
	
	
	/**
	 * 
	 * @param request
	 * @param key  uid iat ext platform
	 * @return
	 */
	public static Object getTokenDataByKey(String key) {
		 Map<String, Object> map=(Map<String, Object>) getCurrentRequest().getAttribute("tokendata");
		 return map.get(key);
	}
	
	public static Object getTokenDataByKey(HttpServletRequest request,String key) {
		 Map<String, Object> map=(Map<String, Object>) request.getAttribute("tokendata");
		 return map.get(key);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param key  uid iat ext platform
	 * @return
	 */
	public static String getCurrentUid() {
		 return (String) getTokenDataByKey("uid");
	}
	
	public static HttpServletRequest getCurrentRequest() throws IllegalStateException {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request 上下文");
        }
        return attrs.getRequest();
    }
	
}
