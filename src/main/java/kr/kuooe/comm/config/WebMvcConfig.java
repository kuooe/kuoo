package kr.kuooe.comm.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.kuooe.comm.comp.ServiceConfig;
import kr.kuooe.comm.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private static final List<String> URL_PATTERNS = Arrays.asList("/Admin/*", "/Admin/*/*");
	
	private LoginInterceptor loginInterceptor;
	
	
	@Autowired
	public WebMvcConfig(LoginInterceptor loginInterceptor) {
		this.loginInterceptor = loginInterceptor;
	}
	
	// 인터셉터 처리
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.debug("WebMvcConfig - addInterceptors() =====> Start");
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns(URL_PATTERNS);
		log.debug("WebMvcConfig - addInterceptors() =====> End");
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/Login.do");
	}
	
	// 가상디렉터리 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/DocuFile/**").addResourceLocations("file:///"+ ServiceConfig.DocuPath +"/");
	}
	
}
