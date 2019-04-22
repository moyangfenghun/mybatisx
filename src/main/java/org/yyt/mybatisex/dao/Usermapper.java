package org.yyt.mybatisex.dao;

import java.util.List;

import org.yyt.mybatisex.bean.User;

public interface Usermapper {
	//多个参数使用注解@param("depId")
	//User selectByPrimaryKey(@param("depId")Integer depId，String name);
	public User selectByPrimaryKey(Integer depId);
	
	public int insert(User user);
	
	public List<User> selectuserlist(Integer pagesize,Integer pageNumber);
	
	public List<User> selectTestplugins();
}
