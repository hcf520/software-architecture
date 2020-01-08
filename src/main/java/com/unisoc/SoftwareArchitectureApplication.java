package com.unisoc;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

@EnableTransactionManagement
@SpringBootApplication(exclude = { DruidDataSourceAutoConfigure.class })
public class SoftwareArchitectureApplication {

	private static ApplicationContext ctx;

	public static void main(String[] args) {
		ctx = SpringApplication.run(SoftwareArchitectureApplication.class, args);
		try {
			String host = InetAddress.getLocalHost().getHostAddress();
			TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) ctx
					.getBean("tomcatServletWebServerFactory");
			int port = tomcatServletWebServerFactory.getPort();
			String contextPath = tomcatServletWebServerFactory.getContextPath();
			System.out.println("http://" + host + ":" + port + contextPath + "/");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	@Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
