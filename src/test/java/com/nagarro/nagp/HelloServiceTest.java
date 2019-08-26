package com.nagarro.nagp;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.nagarro.nagp.service.impl.HelloServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class HelloServiceTest {

	@InjectMocks
	private HelloServiceImpl helloService;

	@Test
	public void testGetUserName() {
		String answer = this.helloService.getUserName();
		MatcherAssert.assertThat(answer, equalTo("Sahil Sharma"));
	}

	@Test
	public void testGetWelcomeText() {
		String answer = this.helloService.getWelcomeText();
		MatcherAssert.assertThat(answer, equalTo("Welcome to the sample application created for devops assignment"));
	}

}
