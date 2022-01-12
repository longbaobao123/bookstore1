package com.cl.service;

import com.cl.pojo.Book;
import com.cl.service.impl.BookServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jack
 * @create 2021-12-08 17:24
 */
public class bookServiceTest {
	@Test
	public void testQueryBookById(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookServiceImpl bookServiceImpl = applicationContext.getBean("bookServiceImpl", BookServiceImpl.class);
		Book book = bookServiceImpl.queryBookById(1);
		System.out.println(book);
	}
}
