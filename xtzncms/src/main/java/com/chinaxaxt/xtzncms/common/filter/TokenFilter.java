package com.chinaxaxt.xtzncms.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.chinaxaxt.xtzncms.common.command.TokenState;
import com.chinaxaxt.xtzncms.common.command.WebStatus;
import com.chinaxaxt.xtzncms.common.utils.encrypt.Jwt;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "tokenFilter")
public class TokenFilter implements Filter{
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
			String login = "/login";
			String header = "/header";
			String druid = "/druid";
			String system = "/system";
			String stat = "/static";
			String uploadLogo = "/communication/uploadLogo";
			String uploadQrCode = "/communication/uploadQrCode";
			String api = "/api";
		    HttpServletRequest request = (HttpServletRequest) servletrequest;
	        HttpServletResponse response = (HttpServletResponse) servletresponse;
	        String path=request.getServletPath();
	        System.out.println("请求地址:"+path);
	        if(path.startsWith(login)||path.startsWith(header)||path.startsWith(druid)||path.startsWith(system)||path.startsWith(stat)||path.startsWith(uploadLogo)||path.startsWith(uploadQrCode)||path.startsWith(api)) {
	        	filterchain.doFilter(servletrequest, servletresponse);
	        	return;
	        }
	        String token=request.getHeader("token");
	        Map<String, Object> resultMap = Jwt.validToken(token);
	        PrintWriter out = null;
	        response.setCharacterEncoding("GBK");
	        TokenState state=TokenState.getTokenState((String)resultMap.get("state"));
			switch (state) {
			case VALID:
				request.getSession();
				request.setAttribute("tokendata", resultMap.get("data"));
				filterchain.doFilter(request, response);
				break;
			case EXPIRED:
	 	        out = response.getWriter();
	 	        out.write(JSON.toJSONString(WebStatus.EXPIRED));
	 	        out.flush();
	 	        out.close();
				break;
			case INVALID:
	 	        out = response.getWriter();
	 	        out.write(JSON.toJSONString(WebStatus.UNAUTHORIZED));
	 	        out.flush();
	 	        out.close();
				break;
			default:{
				
			}
			}
	       
	}	
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
	
}
