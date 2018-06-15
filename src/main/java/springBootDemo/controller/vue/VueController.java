package springBootDemo.controller.vue;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*http://localhost:8081/vue/hello*/
@Controller
@RequestMapping("vue")
public class VueController {

	@RequestMapping("test")
	public String test(Map<String, Object> map) {
		map.put("msg", "Hello vue");
		return "/vue/test";
	}

	@RequestMapping("hello")
	public String hello(Map<String, Object> map) {
		map.put("msg", "Hello vue");
		return "/vue/hello";
	}

	// 事件
	@RequestMapping("event")
	public String event(Map<String, Object> map) {
		map.put("msg", "Hello vue");
		return "/vue/event";
	}

	// 属性绑定和双向数据绑定
	@RequestMapping("bind")
	public String bind(Map<String, Object> map) {
		map.put("msg", "Hello vue");
		return "/vue/bind";
	}

	// bindClass
	@RequestMapping("bindClass")
	public String bindClass(Map<String, Object> map) {
		map.put("msg", "Hello vue");
		return "/vue/bindClass";
	}

	// 计算属性和侦听器
	@RequestMapping("computeAndWatch")
	public String computeAndWatch(Map<String, Object> map) {
		map.put("msg", "Hello vue");
		return "/vue/computeAndWatch";
	}

	// v-if v-show v-for
	@RequestMapping("vCommand")
	public String vCommand(Map<String, Object> map) {
		map.put("msg", "Hello vue");
		return "/vue/vCommand";
	}

	// todoList
	@RequestMapping("todoList")
	public String todoList(Map<String, Object> map) {
		map.put("msg", "Hello vue");
		return "/vue/todoList";
	}

	// component
	@RequestMapping("component")
	public String component(Map<String, Object> map) {
		map.put("msg", "Hello vue");
		return "/vue/component";
	}

	// life 生命周期
	@RequestMapping("life")
	public String life(Map<String, Object> map) {
		map.put("msg", "Hello vue");
		return "/vue/life";
	}
}
