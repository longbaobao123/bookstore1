package com.cl.service.impl;

		import com.cl.dao.BookMapper;
		import com.cl.pojo.Book;
		import com.cl.service.BookService;
		import com.github.pagehelper.PageHelper;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.beans.factory.annotation.Qualifier;
		import org.springframework.stereotype.Service;

		import java.util.List;

/**
 * @author Jack
 * @create 2021-12-07 10:46
 */

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookMapper bookMapper;
	public int addBook(Book book) {
		return bookMapper.addBook(book);
	}
	public int deleteBookById(Integer id) {
		return bookMapper.deleteBookById(id);
	}
	public int updateBook(Book book) {
		return bookMapper.updateBook(book);
	}
	public Book queryBookById(Integer id) {
		return bookMapper.queryBookById(id);
	}
	//分页查询
	public List<Book> queryBookList() {
		List<Book> books = bookMapper.queryBooks();
		return books;
	}

	public List<Book> queryBookByPrice(Integer min, Integer max) {
		return bookMapper.queryBookByPrice(min, max);
	}
}
