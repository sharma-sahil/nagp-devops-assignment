package com.nagarro.nagp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagarro.nagp.service.IHelloService;

/**
 * The Class HelloController.
 *
 * @author sahilsharma
 */
@Controller
public class HelloController {

	/** The hello service. */
	@Autowired
	private IHelloService helloService;

	/**
	 * Hello.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping("/hello")
	public String hello(Map<String, Object> model) {
		model.put("text", helloService.getWelcomeText());
		return "welcome";
	}

	/**
	 * Initialize.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping("/")
	public String initialize(Map<String, Object> model) {
		model.put("user", helloService.getUserName());
		return "index";
	}

}
