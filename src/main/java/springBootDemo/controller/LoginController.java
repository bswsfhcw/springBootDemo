package springBootDemo.controller;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
  
@Controller  
public class LoginController {  
	//http://localhost:8081/
    @RequestMapping("/")  
    public String index(){  
        return "/index";  
    }  
}  
