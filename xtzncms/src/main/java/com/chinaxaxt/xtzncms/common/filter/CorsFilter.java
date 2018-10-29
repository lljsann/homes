package com.chinaxaxt.xtzncms.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "corsFilter")
public class CorsFilter implements Filter {

	 public void init(FilterConfig filterConfig) throws ServletException {

	    }

	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
	        //*表示允许所有域名跨域
	        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
	        httpResponse.addHeader("Access-Control-Allow-Headers",
	                "Origin, X-Requested-With, Content-Type, Accept");
	        //允许跨域的Http方法
	        httpResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,*");
	        filterChain.doFilter(servletRequest, servletResponse);
	    }

	    public void destroy() {

	    }
}
