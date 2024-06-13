package com.dfm.app.service;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfm.app.dao.CartRepo;
import com.dfm.app.dao.OrderRepo;
import com.dfm.app.dao.ProductRepo;
import com.dfm.app.dao.UserRepo;
import com.dfm.app.model.Cart;
import com.dfm.app.model.Order;
import com.dfm.app.model.Product;
import com.dfm.app.model.User;




@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	
	public int saveUser(User user) {
		userRepo.save(user);
		if(userRepo.save(user)!=null) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public User findUser(String email) {
		List<User> user = userRepo.findAll();
		System.out.println("----"+user.size());
		if(user.size() == 0) {
			return null;
		}
		List<User> veifiedUser = user.stream().filter(n -> n.getEmail().equals(email)).collect(Collectors.toList());
		if(veifiedUser.size() > 0) {
			return veifiedUser.get(0);
		}
		else {
			return null;
		}
		
	}
	
	public User authenticateUser(User user) {
		
		if(user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("admin")) {
			
			user.setUsertype("admin");
			
			return user;
		}
		
		List<User> users = userRepo.findAll();
		List<User> veifiedUser = users.stream().filter(n -> (n.getEmail().equals(user.getEmail()) || n.getUsername().equals(user.getEmail())) && n.getPassword().equals(user.getPassword())).collect(Collectors.toList());
		
		if(veifiedUser.size() ==1) {
			return veifiedUser.get(0);
		}
		else {
			return null;
		}
			
	}
	
	public User findUserByUsername(String username) {
		
		List<User> users = userRepo.findAll();
		List<User> veifiedUser = users.stream().filter(n -> n.getUsername().equals(username)).collect(Collectors.toList());
		
		if(veifiedUser.size() > 0) {
			System.out.println("find user by name");
			System.out.println(veifiedUser.get(0));
			return veifiedUser.get(0);
		}
		else {
			return null;
		}
		
	}
	
	public int validatePassword(User usermodel, String securityQuestion, String securityAnswer) {
		List<User> users = userRepo.findAll();
		List<User> verifiedUser = users.stream().filter(n -> n.getEmail().equals(usermodel.getEmail())).collect(Collectors.toList());
		if(verifiedUser.size() ==1) {
			List<User> userSecurities = userRepo.findAll();
			
			List<User> securedUser = userSecurities.stream().filter(security -> security.getSecurityQuestion().equals(securityQuestion) && security.getSecurityAnswer().equals(securityAnswer)
					
					).collect(Collectors.toList());
			if(securedUser.size() == 1) {
				return 1;
			}
			else {
				return 2;
			}
		}
		else {
			return 0;
		}
	}
	
	public void saveNewPassword(User usermodel) {
			
			User user = userRepo.findbyEmail(usermodel.getEmail());
			System.out.println("user#########"+user.toString());
			user.setPassword(usermodel.getPassword());
			userRepo.save(user);
	}
	
	public void deleteUser(Long id) {
			
			userRepo.deleteById(id);
			
	}

	@Override
	public User getUserById(Long id) {
			User user = userRepo.getById(id);
		return user;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepo.getById(id);
	}

	@Override
	public void addToCart(Cart cart) {
		// TODO Auto-generated method stub
		cartRepo.save(cart);
	}

	@Override
	public List<Cart> getUserCart(String email) {
		// TODO Auto-generated method stub
		List<Cart> carts = cartRepo.findAll().stream().filter(c -> c.getUserEmail().equals(email)).collect(Collectors.toList());
		return carts;
	}

	@Override
	public void deleteFromCart(Long id) {
		// TODO Auto-generated method stub
		cartRepo.deleteById(id);
		
	}
	
	@Override
	public void increaseCart(Long id) {
		// TODO Auto-generated method stub
		
		Cart cart = cartRepo.getById(id);
		
		int q = Integer.parseInt(cart.getQuantity()) + 1;
		int t = q * Integer.parseInt(cart.getPrice());
		
		cart.setQuantity(String.valueOf(q));
		cart.setTotalPrice(String.valueOf(t));
		cartRepo.save(cart);
		
		
	}

	@Override
	public void reduceQuantity(Long id) {
		// TODO Auto-generated method stub
	Cart cart = cartRepo.getById(id);
		if(cart.getQuantity().equals("1")) {
			cartRepo.delete(cart);
		}
		else {
		int q = Integer.parseInt(cart.getQuantity()) - 1;
		int t = q * Integer.parseInt(cart.getPrice());
		cart.setQuantity(String.valueOf(q));
		cart.setTotalPrice(String.valueOf(t));
		cartRepo.save(cart);
		}
	}

	@Override
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		orderRepo.save(order);
		
	}

	@Override
	public List<Order> getCustomerOrders(String email) {
		// TODO Auto-generated method stub
		return orderRepo.findAll().stream().filter(o -> o.getEmail().equals(email)).collect(Collectors.toList());
	}

	@Override
	public Order getOrder(Long id) {
		// TODO Auto-generated method stub
		return orderRepo.getById(id);
	}

	@Override
	public void cancelOrder(Long id) {
		// TODO Auto-generated method stub
		orderRepo.deleteById(id);
		}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}
	
	
}
