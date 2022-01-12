package com.cl.controller;

import com.cl.pojo.Cart;
import com.cl.pojo.User;
import com.cl.service.UserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.standard.expression.Each;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author Jack
 * @create 2021-12-02 13:25
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	/**
	 * 登录表单请求
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String username, String password, Model model, HttpSession httpSession){
		User user=new User(null,username,password,null);
		User login = userService.login(user);
		if(login==null){
			model.addAttribute("errorInfo", "用户名或密码错误");
			return "/user/login";
		}else{

			httpSession.setAttribute("login",login );
			return "user/login_success";
		}
	}
	/**
	 * 注册表单请求
	 */
	@RequestMapping(value = "/regist" )
	public String regist(HttpSession httpSession,String username,String password,String email,String code,Model model){
		String token =(String) httpSession.getAttribute(KAPTCHA_SESSION_KEY);
		httpSession.invalidate();
		User user=new User(null,username,password,email);
		if(token!=null&&token.equalsIgnoreCase(code)){
			if(userService.exsitsUsername(username)){
				model.addAttribute("msg", "用户名已存在");
				model.addAttribute("username", username);
				model.addAttribute("email", email);
				return "user/regist";
			}else {
				//保存在数据库中
				userService.registUser(user);
				//请求转发
				return "user/regist_success";
			}
		}else {
				//回显信息，验证码错误
			model.addAttribute("msg", "验证码错误");
			model.addAttribute("username", username);
			model.addAttribute("email", email);
			return "user/regist";
		}
	}
	/**
	 * 注销登录
	 */
	@GetMapping("/loginout")
	public String loginout(HttpSession httpSession){
		httpSession.invalidate();
		return "redirect:/user/login";

	}
	/**
	 * 登录按钮请求
	 * @return
	 */
	@RequestMapping("/loginbtn")
	public String loginbtn(){
		return "/user/login";
	}
	/**
	 * 立即注册按钮请求
	 */
	@RequestMapping("/registbtn")
	public String registbtn(){
		return "/user/regist";
	}




}
