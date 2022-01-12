package com.cl.service;

import com.cl.dao.UserMapper;
import com.cl.pojo.User;
import com.cl.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Jack
 * @create 2021-12-02 14:12
 */

public class UserServiceTest {

	@Test
	public void exsitsUsernameTest(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserServiceImpl userService = applicationContext.getBean("userServiceImpl", UserServiceImpl.class);
		boolean username = userService.exsitsUsername("Tom123");
		System.out.println(username);
	}
	@Test
	public void saveTest(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserServiceImpl userServiceImpl = applicationContext.getBean("userServiceImpl", UserServiceImpl.class);
		User user=new User(null,"xxx","123456","xxx@qq.com");
		System.out.println("输出");

		userServiceImpl.registUser(user);

	}
	@Test
	public void loginTest(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserServiceImpl userServiceImpl = applicationContext.getBean("userServiceImpl", UserServiceImpl.class);
		User user=new User(null,"tom555","123456","tom1@qq.com");
		User login = userServiceImpl.login(user);
		System.out.println(login);


	}
}
