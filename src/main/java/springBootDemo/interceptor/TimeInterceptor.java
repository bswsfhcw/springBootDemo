package springBootDemo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 * 使用 @Component 让 Spring 管理其生命周期：
 * @author bswsfhcw
 * 写拦截器后，我们还需要将其注册到拦截器链中，如下配置：
 * @Configuration
	public class WebConfig extends WebMvcConfigurerAdapter{
	    @Autowired
	    private TimeInterceptor timeInterceptor;
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(timeInterceptor);
	    }
	}
 */

@Component
public class TimeInterceptor  implements HandlerInterceptor {
	 private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
        	LOGGER.info("========preHandle=========");
        	LOGGER.info(((HandlerMethod)handler).getBean().getClass().getName());
        	LOGGER.info(((HandlerMethod)handler).getMethod().getName());
            request.setAttribute("startTime", System.currentTimeMillis());
            return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return true;
    	
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    	try {
    		LOGGER.info("========postHandle=========");
            Long start = (Long) request.getAttribute("startTime");
            LOGGER.info("耗时:"+(System.currentTimeMillis() - start));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
    	try {
    		LOGGER.info("========afterCompletion=========");
            Long start = (Long) request.getAttribute("startTime");
            LOGGER.info("耗时:"+(System.currentTimeMillis() - start));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
    	
    }

}