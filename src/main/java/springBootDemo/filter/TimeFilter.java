package springBootDemo.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义过滤器/第三方过滤器\
 * @author bswsfhcw
 *注册过滤器,要是该过滤器生效，有两种方式：
 *
 *	1) 使用 @Component 注解
	2) 添加到过滤器链中，此方式适用于使用第三方的过滤器。将过滤器写到 WebConfig 类中，如下：
	@Bean
public FilterRegistrationBean timeFilter() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    TimeFilter timeFilter = new TimeFilter();
    registrationBean.setFilter(timeFilter);
    List<String> urls = new ArrayList<>();
    urls.add("/*");
    registrationBean.setUrlPatterns(urls);
    return registrationBean;
}
 */
public class TimeFilter implements Filter {
	 private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("=======初始化过滤器=========");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		long start = System.currentTimeMillis();
		filterChain.doFilter(request, response);
		LOGGER.info("filter 耗时：" + (System.currentTimeMillis() - start));

	}

	@Override
	public void destroy() {
		LOGGER.info("=======销毁过滤器=========");
	}

}