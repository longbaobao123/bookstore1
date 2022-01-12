package com.cl.service.impl;

import com.cl.dao.UserMapper;
import com.cl.pojo.User;
import com.cl.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Jack
 * @create 2021-12-02 12:57
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	public void registUser(User user) {
		userMapper.saveUser(user);
	}

	public User login(User user) {
		return userMapper.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	public boolean exsitsUsername(String username) {
		if(userMapper.queryUserByUsername(username)==null){
			return false;
		}else {
			return true;
		}

	}


}
