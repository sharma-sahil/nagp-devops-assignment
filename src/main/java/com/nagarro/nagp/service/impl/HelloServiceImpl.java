package com.nagarro.nagp.service.impl;

import org.springframework.stereotype.Service;

import com.nagarro.nagp.service.IHelloService;

/**
 * The Class HelloServiceImpl.
 *
 * @author sahilsharma
 */
@Service
public class HelloServiceImpl implements IHelloService {

	@Override
	public String getUserName() {
		return "Sahil Sharma - Prod branch";
	}

	@Override
	public String getWelcomeText() {
		return "Welcome to the sample application created for devops assignment, This is prod branch";
	}

}
