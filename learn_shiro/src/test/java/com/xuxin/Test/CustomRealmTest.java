package com.xuxin.Test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;

import org.apache.shiro.subject.Subject;
import org.junit.Test;


import com.xuxin.realm.CustomRealm;

public class CustomRealmTest {
	@Test
	public void testAuthentication(){
		CustomRealm customRealm=new CustomRealm();
		
		
		//1.构件SecurityManager环境
		DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
		defaultSecurityManager.setRealm(customRealm);
		//给密码加密，数据库中存的是加密后的密码
		HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("md5");
		matcher.setHashIterations(1);
		
		customRealm.setCredentialsMatcher(matcher);
		
		//2.主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject=SecurityUtils.getSubject();
		
		UsernamePasswordToken token=new UsernamePasswordToken("xuxin","admin");
		subject.login(token);
		
		System.out.println("isAuthenticated:"+subject.isAuthenticated());
		
		
	/*	subject.checkRoles("admin");
		
		
		subject.checkPermission("delete");*/
	}
	
}
