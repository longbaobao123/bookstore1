package com.cl.controller;

import com.cl.pojo.Book;
import com.cl.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**利用restFul对图书进行增删改查
 * @author Jack
 * @create 2021-12-07 10:37
 */
@Controller
@RequestMapping("/book")
public class BookController{
	//注入bookService
	@Autowired
	@Qualifier("bookServiceImpl")
	BookService bookService;

	/**
	 * 查询所有图书,分页展示
	 * @param
	 * @return
	 */
	@GetMapping(value = {"/bookPage/{pageNum}"})
	public ModelAndView showBookByPage(@PathVariable(value = "pageNum")Integer pageNum,@RequestParam(required = false,defaultValue = "4",value = "pageSize")Integer pageSize){
		ModelAndView modelAndView=new ModelAndView();
		if(pageNum==null){
			pageNum=1;
		}
		if(pageNum<=0){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=4;
		}
		PageHelper.startPage(pageNum,pageSize);
		try {
			List<Book> books = bookService.queryBookList();
			PageInfo<Book> pageInfo = new PageInfo<Book>(books,pageSize);
			System.out.println(pageInfo);
			modelAndView.addObject("bookByPage", books);
			modelAndView.addObject("pageInfo", pageInfo);
		}finally {
			PageHelper.clearPage();
		}
		modelAndView.setViewName("manager/book_manager");
		return modelAndView;


	}
	/**
	 * 删除指定id图书
	 */
	@DeleteMapping(value = "/{id}" )
	public String deleteBookById(@PathVariable("id") Integer id,@RequestHeader("Referer") String referer){
		int rows = bookService.deleteBookById(id);
		//截取字符串的最后一位
		String pageNum = referer.substring(referer.length() - 1);
		System.out.println("wwo");
		return "redirect:/book/bookPage/"+pageNum;
	}

	/**
	 * 进入修改图书界面，回显信息
	 */
	@GetMapping("/updatePage/{id}")
	public String updatePage(@PathVariable("id") Integer id, ModelMap modelMap){
		//查询图书信息
		Book book = bookService.queryBookById(id);
		//存入modelMap中
		modelMap.addAttribute("returnBook", book);
		return "manager/book_edit";
	}
	/**
	 * 修改图书信息,并回到图书管理界面
	 */
	@PutMapping("/updateOradd")
	public String updateBook(Book book){
		int rows = bookService.updateBook(book);
		return "redirect:/book/";

	}
	@GetMapping("/toAddPage")
	public String toAddPage(){
		return "manager/book_edit";
	}
	/**
	 * 添加图书信息
	 *
	 */
	@PostMapping("/updateOradd")
	public String addBook(Book book){
		int rows = bookService.addBook(book);
		return "redirect:/book/";

	}
}
