package com.dfm.app.service;

import java.util.List;

import com.dfm.app.model.Cart;
import com.dfm.app.model.Order;
import com.dfm.app.model.Product;
import com.dfm.app.model.User;



public interface UserService {
	
	int saveUser(User user);
	
	User findUser(String email);
	
	User authenticateUser(User user);
	
	User findUserByUsername(String username);
	
	int validatePassword(User usermodel, String securityQuestion, String securityAnswer);
	
	void saveNewPassword(User usermodel);
	
	void deleteUser(Long id);

	User getUserById(Long id);

	List<Product> getAllProducts();

	Product getProductById(Long id);

	void addToCart(Cart cart);

	List<Cart> getUserCart(String email);

	void deleteFromCart(Long id);

	void increaseCart(Long id);

	void reduceQuantity(Long id);

	void saveOrder(Order order);

	List<Order> getCustomerOrders(String string);

	Order getOrder(Long id);

	void cancelOrder(Long id);

	List<User> getAllUsers();
	
	

}
