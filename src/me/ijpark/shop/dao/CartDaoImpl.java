package me.ijpark.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import me.ijpark.shop.model.Cart;
import me.ijpark.shop.utils.JDBCUtils;
import me.ijpark.shop.dao.CartDao;


public class CartDaoImpl implements CartDao {

	private static final String INSERT_SHOP_SQL = "INSERT INTO CARTS"
			+ "  (itemnm, username, price,  is_done, ipaddr) VALUES " + " (?, ?,  ?, ?, ?)";

	private static final String SELECT_SHOP_BY_ID = "select id,itemnm,username,price,target_date,is_done from CARTS where id =?";
	private static final String SELECT_ALL_SHOPS = "select * from CARTS where username = ? order by id desc LIMIT 30";
	private static final String DELETE_SHOP_BY_ID = "delete from CARTS where id = ?";
	private static final String UPDATE_SHOP = "update CARTS set itemnm = ?, username= ?, price =?, target_date =?, is_done = ? where id = ?";

	public CartDaoImpl() {
		
	}

	
	public void insertCart(Cart cart) throws SQLException {
		//System.out.println(INSERT_SHOP_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SHOP_SQL)) {
			preparedStatement.setString(1, cart.getItemnm());
			preparedStatement.setString(2, cart.getUsername());
			preparedStatement.setFloat(3, cart.getPrice());
			
			//java.util.Date today = new java.util.Date();
			//java.sql.Date datet = new java.sql.Date(today.getTime());
			
			//preparedStatement.setDate(4, JDBCUtils.getSQLDate(cart.getTargetDate()));
			//preparedStatement.setDate(4, datet);
			preparedStatement.setBoolean(4, cart.getStatus());
			preparedStatement.setString(5, cart.getIpaddr());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
			connection.close();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
	}

	
	public Cart selectCart(long cartId) {
		Cart cart = null;
		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SHOP_BY_ID);) {
			preparedStatement.setLong(1, cartId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("id");
				String itemnm = rs.getString("itemnm");
				String username = rs.getString("username");
				float price = rs.getFloat("price");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				cart = new Cart(id, itemnm, username, price, targetDate, isDone);
			}
			
			connection.close();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return cart;
	}


	public List<Cart> selectAllCarts(String userid) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Cart> carts = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();

				
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SHOPS);) {
			preparedStatement.setString(1, userid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				long id = rs.getLong("id");
				String itemnm = rs.getString("itemnm");
				String username = rs.getString("username");
				float price = rs.getFloat("price");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
	
				carts.add(new Cart(id, itemnm, username, price, targetDate, isDone));
			}
			
			connection.close();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return carts;
	}


	public boolean deleteCart(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SHOP_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
			
			connection.close();
		}
		return rowDeleted;
	}


	public boolean updateCart(Cart cart) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SHOP);) {
			statement.setString(1, cart.getItemnm());
			statement.setString(2, cart.getUsername());
			statement.setFloat(3, cart.getPrice());
			statement.setDate(4, JDBCUtils.getSQLDate(cart.getTargetDate()));
			statement.setBoolean(5, cart.getStatus());
			statement.setLong(6, cart.getId());

			rowUpdated = statement.executeUpdate() > 0;
			
			connection.close();
		}
		return rowUpdated;
	}
}
