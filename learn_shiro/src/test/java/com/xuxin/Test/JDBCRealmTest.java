package com.xuxin.Test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;


public class JDBCRealmTest {
	DruidDataSource dataSource=new DruidDataSource();
	{
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
	}
	@Test
	public void testAuthentication(){
		
		JdbcRealm jdbcRealm=new JdbcRealm();
		jdbcRealm.setDataSource(dataSource);
		//1.构件SecurityManager环境
		DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
		defaultSecurityManager.setRealm(jdbcRealm);
		
		//2.主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject=SecurityUtils.getSubject();
		
		UsernamePasswordToken token=new UsernamePasswordToken("xuxin","admin");
		subject.login(token);
		
		System.out.println("isAuthenticated:"+subject.isAuthenticated());
		
		/*//检验该用户是否有admin角色
		subject.checkRoles("admin1");
		
		//检验该用户是否有删除权限
		subject.checkPermission("user:delete");*/
	}
}
