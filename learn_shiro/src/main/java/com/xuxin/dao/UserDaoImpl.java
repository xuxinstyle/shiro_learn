package com.xuxin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xuxin.admin.User;

@Component
public class UserDaoImpl implements UserDao{

	@Override
	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRolesByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	/*@Resource
	private JdbcTemplate jdbcTemplate;
	@Override
	public User getUserByUserName(String username) {
		String sql="select username,password from users where username=?";
		System.out.println("------");
		List<User> list = jdbcTemplate.query(sql, new String[]{username},new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet arg0, int arg1) throws SQLException {
				User user=new User();
				user.setUsername(arg0.getString("username"));
				user.setPassword(arg0.getString("password"));
				System.out.println(user.getPassword());
				
				return user;
			}
			
			
		});
		System.out.println(list);
		
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	@Override
	public List<String> getRolesByUserName(String username) {
		String sql="select role_name from user_roles where username=?";
		
		return jdbcTemplate.query(sql, new String[]{username},new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet arg0, int arg1) throws SQLException {
				
				return arg0.getString("role_name");
			}
			
			
		});
	}*/

}
