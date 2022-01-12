package com.cl.service;

import com.cl.pojo.Book;

import java.util.List;

public interface BookService {
	int addBook(Book book);

	int deleteBookById(Integer id);

	int updateBook(Book book);

	Book queryBookById(Integer id);

	List<Book> queryBookList();

	List<Book> queryBookByPrice(Integer min,Integer max);
}
