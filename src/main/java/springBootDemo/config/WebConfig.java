package springBootDemo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import springBootDemo.filter.TimeFilter;
import springBootDemo.interceptor.TimeInterceptor;
import springBootDemo.listener.ListenerTest;
import springBootDemo.servlet.ServletTest;

@SuppressWarnings("deprecation")
@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter{
	
	@Autowired
    private TimeInterceptor timeInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
	
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;

		return new HttpMessageConverters(converter);

	}

	/*http://localhost:8081/servletTest*/
	@Bean
	public ServletRegistrationBean<ServletTest> servletRegistrationBean() {
		return new ServletRegistrationBean<ServletTest>(new ServletTest(), "/servletTest");
	}
	
	@Bean
	public FilterRegistrationBean<TimeFilter> timeFilter() {
	    FilterRegistrationBean<TimeFilter> registrationBean = new FilterRegistrationBean<TimeFilter>();
	    TimeFilter timeFilter = new TimeFilter();
	    registrationBean.setFilter(timeFilter);
	    List<String> urls = new ArrayList<>();
	    urls.add("/*");
	    registrationBean.setUrlPatterns(urls); 
	    return registrationBean;
	}
	
	@Bean
	public ServletListenerRegistrationBean<ListenerTest> servletListenerRegistrationBean() {
	    return new ServletListenerRegistrationBean<ListenerTest>(new ListenerTest());
	}
}