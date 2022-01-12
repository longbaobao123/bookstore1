package com.cl.controller;

import com.cl.pojo.Cart;
import com.cl.pojo.User;
import com.cl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Jack
 * @create 2021-12-13 14:48
 */
@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	@RequestMapping("/createOrder")
	public String createOrder(HttpSession httpSession){
		Cart cart = (Cart) httpSession.getAttribute("cart");
		User  loginUser = (User) httpSession.getAttribute("login");
		Integer id = loginUser.getId();
		String orderId = orderService.createOrder(cart, id);
		httpSession.setAttribute("orderId", orderId);
		return "cart/checkout";
	}
	@RequestMapping("/myOrderPage")
	public String myOrderPage(){
		return "order/order";
	}

}
