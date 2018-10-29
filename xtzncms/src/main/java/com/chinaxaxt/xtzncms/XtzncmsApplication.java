package com.chinaxaxt.xtzncms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 * @author 吴佳涛
 *
 */
@SpringBootApplication
@MapperScan("com.chinaxaxt.xtzncms.mapper")
public class XtzncmsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx= SpringApplication.run(XtzncmsApplication.class, args);
	}
}
