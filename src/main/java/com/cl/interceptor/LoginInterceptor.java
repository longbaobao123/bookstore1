package com.cl.interceptor;

import com.cl.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**拦截登录请求
 * @author Jack
 * @create 2021-12-10 12:16
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("login");
		if(loginUser==null){
			request.setAttribute("errorInfo", "没有权限请先登录");
			request.getRequestDispatcher("/user/loginbtn").forward(request, response);
			return false;
		}else {
			return true;
		}

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
