package com.nagarro.nagp;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String hello(Map<String, Object> model) {
		model.put("user", "Sahil Sharma");
		return "welcome";
	}

	@RequestMapping("/")
	public String initialize(Map<String, Object> model) {
		model.put("user", "Sahil Sharma");
		return "index";
	}
}
