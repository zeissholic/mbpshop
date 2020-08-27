package me.ijpark.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.ijpark.shop.model.User;
import me.ijpark.shop.utils.JDBCUtils;

public class UserDao {

	public int registerEmployee(User employee) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "insert into USERS"
				+ "  (first_name, last_name, username, passwd) values "
				+ " (?, ?, ?, ?)";

		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUsername());
			preparedStatement.setString(4, employee.getPassword());

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			result = preparedStatement.executeUpdate();
			
			connection.close();

		} catch (SQLException e) {
			// process sql exception
			JDBCUtils.printSQLException(e);
		}
		return result;
	}

}
