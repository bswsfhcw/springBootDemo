package springBootDemo.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springBootDemo.model.User;

/*http://localhost:8081/error/test
 *http://localhost:8081/error/test404
 * */
@Controller
@RequestMapping("error")
public class ErrorController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@RequestMapping("/test")
	@ResponseBody
	public User test() {
		User user = new User();
		user.setId(1);
		user.setUsername("jack");
		user.setPassword("jack123");
		user.setBirthday(new Date());
		// 模拟异常
        int i = 1/0;
        LOGGER.info("ErrorController:"+i);
		return user;
	}
}
