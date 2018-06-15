package springBootDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*http://localhost:8081/helloworld*/
@RestController
public class TestController {
	 private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/helloworld")
    public String helloworld() {
        return "helloworld";
    }
    @GetMapping("/helloworld1")
    public String helloworld1() {
    	LOGGER.info("helloworld1");
        return "helloworld1";
    }
}