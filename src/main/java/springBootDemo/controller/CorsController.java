package springBootDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*http://localhost:8081/helloworld*/
@Controller
public class CorsController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("cors")
	public String test() { 
		return "/cors";
	}
}