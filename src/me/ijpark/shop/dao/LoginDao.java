package me.ijpark.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.ijpark.shop.model.LoginBean;
import me.ijpark.shop.utils.JDBCUtils;

public class LoginDao {

	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
		boolean status = false;

		//Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
				// Step 2:Create a statement using connection object
				
			PreparedStatement preparedStatement = connection.prepareStatement("select * from USERS where username = ? and passwd = ? ")) {
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
			
			
			connection.close();

		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
		} catch (Exception e) {	
			System.err.println(e.toString());
			
		}
		return status;
	}
}
