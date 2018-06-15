package springBootDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*http://localhost:8081/websocket*/
@Controller
public class WebsocketController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("websocket")
	public String websocket() { 
		LOGGER.info("WebsocketController websocket");
		return "/websocket";
	}
}