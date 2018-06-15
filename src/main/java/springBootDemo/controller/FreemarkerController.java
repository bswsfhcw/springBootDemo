package springBootDemo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*http://localhost:8081/freemarker/hello*/
@Controller
@RequestMapping("freemarker")
public class FreemarkerController {
    @RequestMapping("hello")
    public String hello(Map<String,Object> map) {
        map.put("msg", "Hello Freemarker");
        return "hello";
    }
}
