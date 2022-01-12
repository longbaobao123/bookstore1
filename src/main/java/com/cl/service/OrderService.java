package com.cl.service;

import com.cl.pojo.Cart;

/**
 * @author Jack
 * @create 2021-12-13 14:51
 */
public interface OrderService {
	//创建订单
	String createOrder(Cart cart ,Integer userId);
}
