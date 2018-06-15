package springBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
/**
该注解指定项目为springboot，由此类当作程序入口
自动装配 web 依赖的环境

**/
@EnableCaching
@SpringBootApplication
public class SpringbootApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootApplication.class);
    }
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}