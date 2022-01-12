package com.cl.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jack
 * @create 2021-10-08 13:07
 */
@Component
public class Cart {
//	private Integer totalCount;	//商品总数
//	private BigDecimal totalPrice;//商品总价格
	private Map<Integer,CartItem> items=new LinkedHashMap<Integer, CartItem>();//购物车商品信息
	public Cart() {
	}
	public Integer getTotalCount() {
		Integer totalCount=0;
		for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
			totalCount+=entry.getValue().getCount();
		}
		return totalCount;
	}


	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice=new BigDecimal(0);
		for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
			totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
		}
		return totalPrice;
	}



	public Map<Integer, CartItem> getItems() {
		return items;
	}

	public void setItems(Map<Integer, CartItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Cart{" +
				"totalCount=" + getTotalCount() +
				", totalPrice=" + getTotalPrice() +
				", items=" + items +
				'}';
	}

	/**
	 * 添加商品信息
	 * @param cartItem
	 */
	public void addItem(CartItem cartItem){
		//先查看购物车中是否添加过此商品，如果添加，则更新商品数量，和总金额，如果没有添加，则将此商品存放到集合cartItem中
		CartItem item = items.get(cartItem.getId());

		if(item==null){
			//没有添加过此商品
			items.put(cartItem.getId(),cartItem);
		}else{
			//添加过此商品
			item.setCount(item.getCount()+1);//更新数量
			item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
		}

	}

	/**
	 * 删除指定商品
	 * @param id
	 */
	public void delete(Integer id){
		items.remove(id);
	}

	/**
	 * 清空购物车
	 */
	public void clear(){
		items.clear();

	}

	/**
	 * 修改该商品数量
	 * @param id
	 * @param count
	 */
	public void updateCount(Integer id,Integer count){
		//查找此商品通过id
		CartItem cartItem = items.get(id);
		//如果有
		if(cartItem!=null){
			//设置商品数量
			cartItem.setCount(count);
			//更新总金额
			cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));

		}
	}




}
