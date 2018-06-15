package springBootDemo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springBootDemo.model.User;

/*
 * http://localhost:8081/swagger/swagger/name=11
 * http://localhost:8081/swagger-ui.html
 * */
@Api(value = "Swagger2测试", tags = { "测试接口" })
@Controller
@RequestMapping("swagger")
public class Swagger2Controller {
	@ApiOperation("获取用户信息")
	@ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query")
	@RequestMapping("/swagger/{name}")
	@ResponseBody
	public User swagger(@PathVariable("name") String name) {
		User user = new User();
		user.setId(1);
		user.setUsername(name);
		user.setPassword("jack123");
		user.setBirthday(new Date());
		return user;
	}
}
