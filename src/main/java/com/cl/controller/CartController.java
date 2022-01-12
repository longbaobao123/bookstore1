package com.cl.controller;

import com.cl.pojo.Book;
import com.cl.pojo.Cart;
import com.cl.pojo.CartItem;
import com.cl.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.net.httpserver.HttpsServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Jack
 * @create 2021-12-10 11:11
 */
@Controller
public class CartController {
	@Autowired
	@Qualifier("bookServiceImpl")
	BookService bookService;
	/**
	 * 首页显示商品
	 */
	@RequestMapping(value = {"/"})
	public String ItemShow(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
						 @RequestParam(required = false,defaultValue = "4",value ="pageSize") Integer pageSize,ModelMap modelMap){
		PageHelper.startPage(pageNum,pageSize);
		List<Book> books = bookService.queryBookList();
		PageInfo<Book> pageInfo=new PageInfo<Book>(books,pageSize);
		modelMap.addAttribute("pageInfo", pageInfo);
		modelMap.addAttribute("bookByPage", books);
		return "client/index";
	}
	@RequestMapping("/{pageNum}")
	public String ItemShowByPage(@PathVariable("pageNum") Integer pageNum,
								 @RequestParam(required = false,defaultValue = "4",value ="pageSize") Integer pageSize,ModelMap modelMap){
		PageHelper.startPage(pageNum,pageSize);
		List<Book> books = bookService.queryBookList();
		PageInfo<Book> pageInfo=new PageInfo<Book>(books,pageSize);
		modelMap.addAttribute("pageInfo", pageInfo);
		modelMap.addAttribute("bookByPage", books);
		return "client/index";

	}
	@RequestMapping("/addItem/{bookId}")
	public String addItem(@PathVariable("bookId") Integer id, HttpSession httpSession, @RequestHeader("Referer") String referer){
		//查询图书通过id
		Book book = bookService.queryBookById(id);
		//把图书信息转化成商品项
		CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
		//获取购物车
		Cart cart = (Cart)httpSession.getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			httpSession.setAttribute("cart", cart);
		}
		cart.addItem(cartItem);
		httpSession.setAttribute("lastName", cartItem.getName());
		String PageNum = referer.substring(referer.length() - 1);
		if("/".equals(PageNum)){
			PageNum="";
		}
		return "redirect:/"+PageNum;
	}
	/**
	 * 查询价格区间
	 */
	@RequestMapping("/queryPrice")
	public String queryPrice(@RequestParam("min") Integer min,@RequestParam("max") Integer max,ModelMap modelMap){
		List<Book> books = bookService.queryBookByPrice(min, max);
		modelMap.addAttribute("bookByPage", books);
		System.out.println("git test");
		return "client/index";

	}
	//删除商品项
	@RequestMapping("/cart/deleteItems/{bookId}")
	public String deleteItemByCart(@PathVariable("bookId")Integer id,HttpSession httpSession){
		//从session域中获取Cart
		Cart cart = (Cart) httpSession.getAttribute("cart");
		if(cart !=null){
			cart.delete(id);
			//重定向回原来的界面
			return "redirect:/toCartPage";
		}else{
			return null;
		}



	}
	//修改数量
	@RequestMapping("/updateCount")
	public String updateItemsCount(@RequestParam("id") Integer id,@RequestParam("count")Integer count,HttpSession httpSession ){
		Cart cart = (Cart) httpSession.getAttribute("cart");
		if(cart!=null){
			cart.updateCount(id, count);
			return "redirect:/toCartPage";
		}
		return null;

	}
	/**
	 * 清空购物车
	 */
	@RequestMapping("/clearItem")
	public String clearCart(HttpSession httpSession){
		Cart cart = (Cart) httpSession.getAttribute("cart");
		if(cart!=null){
			cart.clear();
			return "redirect:/toCartPage";
		}
		return null;

	}

	/**
	 * 跳转到购物车界面
	 * @return
	 */
	@RequestMapping("/toCartPage")
	public String toCartPage(){
		return "cart/cartPage";
	}


}
