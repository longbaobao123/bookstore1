package com.cl.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jack
 * @create 2021-12-06 9:56
 */
public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request){
		String verifyCodeExpected = (String)request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//这里相当于 request.getParameter("verifyCodeActual");
		String verifyCodeActual =request.getParameter("verifyCodeActual");
		if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)){
			return false;
		}
		return true;
	}
}
