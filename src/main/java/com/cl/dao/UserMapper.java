package com.cl.dao;

import com.cl.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserMapper {
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return  返回为null没有这个用户
	 */
	 User queryUserByUsername(String username);

	/**
	 * 保存用户信息
	 * @param user
	 * @return  -1表示操作失败，其他表示影响的行数
	 */
	 int saveUser(User user);

	/**
	 * 查询用户通过用户名和密码，用于用户登录页面
	 * @param username
	 * @param password
	 * @return
	 */
	 User queryUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
