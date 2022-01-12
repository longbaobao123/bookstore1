package com.cl.dao;

import com.cl.pojo.Order;

import java.util.List;

public interface OrderMapper {

	int saveOrder(Order order);
	List<Order> queryOrders();

	Integer changeOrderStatus(String orderId, int status);
}
