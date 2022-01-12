package com.cl.dao;

import com.cl.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jack
 * @create 2021-12-03 10:14
 */
public interface BookMapper {
	/**
	 * 添加图书
	 */
	int addBook(Book book);
	/**
	 * 修改图书
	 *
	 */
	int updateBook(Book book);
	/**
	 * 删除图书
	 */
	int deleteBookById(@Param("id") Integer id);

	/**
	 * 查询指定id的图书
	 */
	Book queryBookById(@Param("id") Integer id);
	/**
	 * 查询所有图书
	 *
	 */
	List<Book> queryBooks();
	/**
	 *通过价格查询图书
	 */
	List<Book> queryBookByPrice(Integer min,Integer max);



}
