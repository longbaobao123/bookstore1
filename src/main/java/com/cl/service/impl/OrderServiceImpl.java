package com.cl.service.impl;

import com.cl.dao.BookMapper;
import com.cl.dao.OrderItemMapper;
import com.cl.dao.OrderMapper;
import com.cl.pojo.*;
import com.cl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author Jack
 * @create 2021-12-13 14:53
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderItemMapper orderItemMapper;
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	BookMapper bookMapper;
	public String createOrder(Cart cart, Integer userId) {

		//生成唯一订单编号
		String orderId=System.currentTimeMillis()+""+userId;
		//创建订单对象
		Order order=new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
		//保存订单
		orderMapper.saveOrder(order);


		//遍历购物车中的每个商品项转换为订单项保存到数据库中
		for(Map.Entry<Integer,CartItem> entry:cart.getItems().entrySet()) {
			//获取商品项
			CartItem cartItem = entry.getValue();
			//转换为订单项
			OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(),
					orderId);
			//保存到数据库
			orderItemMapper.saveOrderItem(orderItem);
			//更新库存和销量
			Book book = bookMapper.queryBookById(cartItem.getId());
			book.setSales(book.getSales() + cartItem.getCount());
			book.setStock(book.getStock() - cartItem.getCount());
			bookMapper.updateBook(book);
		}
			//清空购物车
			cart.clear();
			return orderId;
	}
}
