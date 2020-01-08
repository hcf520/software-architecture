package com.unisoc.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.unisoc.config.security.AuthenticationInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebViewConfig implements WebMvcConfigurer {

	/**
	 * @Description: 注册jsp视图解析器
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewNames("*");
		resolver.setOrder(2);
		return resolver;
	}

	/**
	 * @Description: 注册html视图解析器
	 */
	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setTemplateMode("HTML");
		templateResolver.setPrefix("classpath:/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("utf-8");
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	/**
	 * @Description: 将自定义tml视图解析器添加到模板引擎并注册到ioc
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	/**
	 * @Description: Thymeleaf视图解析器配置
	 */
	@Bean
	public ThymeleafViewResolver viewResolverThymeLeaf() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("utf-8");
		viewResolver.setViewNames(new String[] { "/*" });
		viewResolver.setOrder(1);
		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * 配置实现静态文件映射
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");// 静态资源路径css,js,img等
		registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");// 视图
		registry.addResourceHandler("/mapper/**").addResourceLocations("classpath:/mapper/");// mapper.xml
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/public/");// 映射favicon.ico
		// Swagger 静态资源处理
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");// 开放资源
	}

	/**
	 * URL定向
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/index");// 默认视图跳转
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/article").setViewName("/article");
		registry.addViewController("/error/404").setViewName("/error/404");
		registry.addViewController("/error/500").setViewName("/error/500");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	/**
	 * 跨域配置
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")// 配置允许跨域的路径
				.allowedOrigins("*")// 配置允许访问的跨域资源的请求域名
				.allowedMethods("PUT,POST,GET,DELETE,OPTIONS")// 配置允许访问该跨域资源服务器的请求方法，如：POST、GET、PUT、DELETE等
				.allowedHeaders("*"); // 配置允许请求header的访问，如 ：X-TOKEN
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/**","/"); // 拦截的路径
		// .excludePathPatterns("/user/**", "/", "/statics/**",
		// "/favicon.ico","/public/**"); // 放行的路径
		registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**"); // 拦截所有请求，通过判断是否有
																						// @LoginRequired
																						// 注解
																						// 决定是否需要登录
	}

	@Bean
	public AuthenticationInterceptor authenticationInterceptor() {
		return new AuthenticationInterceptor();
	}

	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
}
