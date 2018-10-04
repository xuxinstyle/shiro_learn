package com.xuxin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxin.po.User;
@Controller
public class UserController {
	@RequestMapping(value="/subLogin" ,method=RequestMethod.POST,
			produces="application/json;charset=utf-8")
	
	@ResponseBody
	public String subLogin(User user){
		Subject subject=SecurityUtils.getSubject();
		Md5Hash md5Hash=new Md5Hash(user.getPassword(),"hhh");
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),md5Hash.toString());
		subject.login(token);
		try{
			subject.login(token);
		}catch(AuthenticationException e){
			return e.getMessage();
			
		}
		return "登录成功";
		
	}
}
