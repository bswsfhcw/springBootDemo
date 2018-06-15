package springBootDemo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义监听器
 * @author bswsfhcw
 * 注册监听器为 Bean，在 WebConfig 配置类中添加如下代码：
 * 	@Bean
	public ServletListenerRegistrationBean<ListenerTest> servletListenerRegistrationBean() {
	    return new ServletListenerRegistrationBean<ListenerTest>(new ListenerTest());
	}
 */
public class ListenerTest implements ServletContextListener {
	 private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) { 
        LOGGER.info("监听器初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}