package com.xuxin.dao;

import java.util.List;

import com.xuxin.admin.User;


public interface UserDao {

	User getUserByUserName(String username);

	List<String> getRolesByUserName(String username);

}
