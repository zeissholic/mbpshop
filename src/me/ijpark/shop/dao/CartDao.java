package me.ijpark.shop.dao;

import java.sql.SQLException;
import java.util.List;

import me.ijpark.shop.model.Cart;

public interface CartDao {

	void insertCart(Cart cart) throws SQLException;

	Cart selectCart(long cartId);

	List<Cart> selectAllCarts(String userid);

	boolean deleteCart(int id) throws SQLException;

	boolean updateCart(Cart cart) throws SQLException;

}