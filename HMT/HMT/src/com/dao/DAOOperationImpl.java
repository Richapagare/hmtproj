package com.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;
import com.utility.Connector;

public class DAOOperationImpl implements DAOOperation {

	@Override
	public String authenticateUser(User user) {

		Connection con = Connector.connection();
		// String query=;

		try {

			PreparedStatement ps = con.prepareStatement("select password from register where email=?");
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(user.getPassword())) {
					return "welcome";

				} // inside if ends

				else {

					return "invalid";

				} // else ends

			} // outside if ends

		} // try ends

		catch (Exception e) {
			e.printStackTrace();
		}

		return "invalid";

	}// authenticateUser ends

	@Override
	public String registerUser(User user) throws SQLException {
		Connection con = Connector.connection();
		PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?,?)");
		ps.setInt(1, user.getEmpNumber());
		ps.setString(2, user.getUserName());
		ps.setString(3, user.getRole());
		ps.setString(4, user.getEmail());
		ps.setInt(5, user.getSeniorEmpNumber());
		ps.setString(6, user.getPassword());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return "User Inserted";
		} else {
			return "Not Inserted";
		}
	}

	@Override
	public String changePassword(User user) throws Exception {
		// TODO Auto-generated method stub
		Connection con = Connector.connection();
		PreparedStatement ps = con.prepareStatement("update register set password=? where email=?");
		ps.setString(1, user.getPassword());
		ps.setString(2, user.getEmail());
		int rs = ps.executeUpdate();
		if (rs != 0) {
			return "updated";
		} else {
			return "error";
		}

	}

	@Override
	public ResultSet makeRequest() throws Exception {

		Connection con = Connector.connection();
		// String query=;

		try {

			PreparedStatement ps = con.prepareStatement("select * from stationary");

			ResultSet rs = ps.executeQuery();
			
			return rs;

		} // try ends

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}// DAOOperationImpl ends
