package com.cl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jack
 * @create 2021-12-07 10:10
 */
@Controller
public class ManagerController {
	//进入manager.html
	@RequestMapping("/managerPage")
	public ModelAndView mangerPage(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("manager/manager");
		return  modelAndView;
	}


}
