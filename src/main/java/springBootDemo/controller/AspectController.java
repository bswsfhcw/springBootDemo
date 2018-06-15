package springBootDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * http://localhost:8081/hello?name=1
*/	
@RestController
public class AspectController {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public String hello(@RequestParam String name) {
		return "Hello " + name;
	}
}
