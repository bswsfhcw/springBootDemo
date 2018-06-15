package springBootDemo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 自定义 Servlet
 * 将 Servelt 注册成 Bean。在上文创建的 WebConfig 类中添加如下代码：
 * @Bean
	public ServletRegistrationBean servletRegistrationBean() {
	    return new ServletRegistrationBean(new ServletTest(),"/servletTest");
	}
 */
public class ServletTest extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write("自定义 Servlet");
	}
}