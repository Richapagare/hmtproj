package com.dao;

import java.sql.ResultSet;

import com.model.User;

public interface DAOOperation {

	public String authenticateUser(User user);
	public String registerUser(User user) throws Exception;
	public String changePassword(User user) throws Exception;
	public ResultSet makeRequest() throws Exception;
	
}
