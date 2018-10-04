package com.xuxin.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuxin.admin.User;
import com.xuxin.dao.UserDao;
import com.xuxin.mapper.UsersMapper;
import com.xuxin.po.Users;
import com.xuxin.po.UsersExample;
import com.xuxin.po.UsersExample.Criteria;

public class CustomRealm extends AuthorizingRealm{
	 @Autowired
	private UsersMapper usersMapper;
	/*Map<String ,String> map=new HashMap<String, String>();
	{
		 
		map.put("xuxin", "849fbd23d48264a3a7e8a9bb8b222088");
		super.setName("customRealm");
	}*/
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username=(String)principals.getPrimaryPrincipal();
		//从数据库获缓存中获取角色数据
		Set<String> roles=getRolesByUserName(username);
		//从数据库获缓存中获取权限数据
		Set<String> permissions=getPermissionByUserName(username);
		
		SimpleAuthorizationInfo simpleAuthorizationInfo =new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(roles);
		simpleAuthorizationInfo.setStringPermissions(permissions);
		
		
		return simpleAuthorizationInfo;
	}
	private Set<String> getPermissionByUserName(String username) {
		Set<String> sets=new HashSet<String>();
		sets.add("update");
		sets.add("delete");
		return sets;
	}
	//模拟从缓存中获取权限数据
	private Set<String> getRolesByUserName(String username) {
	/*	List<String> list=.getRolesByUserName(username);
		Set<String> sets=new HashSet<String>(list);
		*/
		return null;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取传过来的用户名
		String username = (String) token.getPrincipal();
		//获取传过来的密码
		//String password = (String) token.getCredentials();
		//从数据库读取密码
		String tpassword=getPasswordByUserName(username);
		System.out.println(tpassword);
		if(tpassword==null){
			return null;
		}
		
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username,tpassword,"CustomRealm");
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("hhh"));
		
		return authenticationInfo;
	}
	//模拟访问数据库
	private String getPasswordByUserName(String username) {
		UsersExample example=new UsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<Users> list = usersMapper.selectByExample(example);
		System.out.println(list+"list");
		if(list!=null){
			return list.get(0).getPassword();
		}
		return null;
	}
	public static void main(String[] args) {
		//给密码加盐，即加噪声
		Md5Hash md5Hash=new Md5Hash("admin","hhh");
		System.out.println(md5Hash.toString());
	}
	
}
