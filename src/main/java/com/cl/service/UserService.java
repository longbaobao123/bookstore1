package com.cl.service;

import com.cl.pojo.User;

public interface UserService {
	/**
	 *注册用户,并将数据保存到数据库
	 * @param user
	 */
	 void registUser(User user);

	/**
	 * 用户登录
	 * @return 如果返回有值表明登录成功
	 */
	 User login(User user);

	/**
	 * 检查用户名是否可用
	 * @param username
	 * @return  返回为true表示用户名已存在，返回为false表示用户名不存在；
	 */
	 boolean exsitsUsername(String username);
}
