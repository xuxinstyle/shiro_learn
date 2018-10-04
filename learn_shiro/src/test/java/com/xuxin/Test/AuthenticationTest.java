package com.xuxin.Test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;


public class AuthenticationTest {
	SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();
	@Before(value = "")
	public void addUser(){
		simpleAccountRealm.addAccount("xuxin", "admin","admin");
	}
	@Test
	public void testAuthentication(){
		
		//1.构件SecurityManager环境
		DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
		defaultSecurityManager.setRealm(simpleAccountRealm);
		
		//2.主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject=SecurityUtils.getSubject();
		
		UsernamePasswordToken token=new UsernamePasswordToken("xuxin","admin","user");
		subject.login(token);
		
		System.out.println("isAuthenticated:"+subject.isAuthenticated());
		
		//检验该用户是否有admin角色
		subject.checkRoles("admin","user");
	}
}
