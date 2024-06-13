package com.dfm.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dfm.app.model.Cart;
import com.dfm.app.model.Order;
import com.dfm.app.model.Product;
import com.dfm.app.model.User;
import com.dfm.app.service.UserService;





@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
    @GetMapping("/user")
	public String getUserWelcomePage(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        model.addAttribute("role", userdata.getUsertype());
        model.addAttribute("username", userdata.getUsername());
		return "user/welcomeuser";
	}
	
	@GetMapping("/products")
	public String products(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        
        List<Product> products = userService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("username", userdata.getUsername());
        
        model.addAttribute("role", userdata.getUsertype());
		return "user/products";
	}
	
	@PostMapping("/addToCart/{id}")
	public String addToCart(Model model, HttpSession session, @PathVariable(name="id") Long id, @RequestParam("quantity") String quantity) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		String guest = "";
		
			guest = messages.get(0);
		
		

		Product product = userService.getProductById(id);
		Cart cart = new Cart();
		
		
		cart.setUserEmail(guest);
		cart.setDescription(product.getDescription());
		cart.setName(product.getName());
		
		cart.setQuantity(quantity);
		cart.setPrice(product.getPrice());
		int totalPrice = Integer.parseInt(product.getPrice())* Integer.parseInt(quantity);
		cart.setTotalPrice(String.valueOf(totalPrice));
		
		userService.addToCart(cart);
		
        model.addAttribute("sessionMessages", messages);
		
		return "redirect:/cart";
	}
	
	@GetMapping("/cart")
	public String cart(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		
		List<Cart> carts = userService.getUserCart(messages.get(0));
		 User userdata = userService.findUser(messages.get(0));
		model.addAttribute("carts", carts);
		
		int finalPrice = 0;
		for(int i=0;i<carts.size();i++) {
			finalPrice = finalPrice + Integer.parseInt(carts.get(i).getTotalPrice());
		}
		
		if(finalPrice > 0) {
			model.addAttribute("finalprice", finalPrice);
		}
		else {
			model.addAttribute("finalprice", "0");
		}
		
		
		int cartSize = carts.size();
		if(cartSize > 0) {
			model.addAttribute("flag", 1);
		}
		else {
			model.addAttribute("flag", 0);
		}
		
		
		model.addAttribute("cartsize", cartSize);
		System.out.println(carts.size()+"------");
		model.addAttribute("username", userdata.getUsername());
		
		return "user/cart";
		
	}
	

	@PostMapping("/removeFromCart/{id}")
	public String removeFromCart(@PathVariable(name="id") Long id)
	{
		userService.deleteFromCart(id);
		
		return "redirect:/cart";
	}
	
	@PostMapping("/increaseQuantity/{id}")
	public String increaseQuantity(@PathVariable(name="id") Long id,Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error2";
		}
		User userdata = userService.findUser(messages.get(0));
		
		userService.increaseCart(id);
		
		return "redirect:/cart";
	}
	
	
	@PostMapping("/reduceQuantity/{id}")
	public String reduceQuantity(@PathVariable(name="id") Long id,Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error2";
		}
		User userdata = userService.findUser(messages.get(0));
		
		userService.reduceQuantity(id);
		
		return "redirect:/cart";
	}
	
	@GetMapping("/placeOrder")
	public String placeOrder(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		
		User userdata = userService.findUser(messages.get(0));
		Order order = new Order();
		List<Cart> cart =userService.getUserCart(messages.get(0));
		
		int finalCost = 0;
		for(int i=0;i<cart.size(); i++) {
			
			
			
			finalCost = finalCost + Integer.parseInt(cart.get(i).getTotalPrice());
		}
		
		
		model.addAttribute("cost", finalCost);
		model.addAttribute("order", order);
		model.addAttribute("username", userdata.getUsername());
		
		return "user/payment";
		
	}
	
	@PostMapping("makePayment")
	public String makePayment(@ModelAttribute("order") Order order,HttpSession session, Model model)
	{
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		String guest = "";
		
			guest = messages.get(0);
	
		
		List<Cart> cart =userService.getUserCart(guest);
		
		StringJoiner name = new StringJoiner(",");
		StringJoiner price = new StringJoiner(",");
		StringJoiner quantity = new StringJoiner(",");
		StringJoiner totalCost = new StringJoiner(",");
		
		 
		double finalCost = 0;
		
		for(int i=0;i<cart.size(); i++) {
			
			name.add(cart.get(i).getName());
			
			price.add(cart.get(i).getPrice());
			
			quantity.add(cart.get(i).getQuantity());
			
			
			
			totalCost.add(cart.get(i).getTotalPrice());
			
			finalCost = finalCost + Integer.parseInt(cart.get(i).getTotalPrice());
		}
		
	
		
		order.setName(name.toString());
		order.setPrice(price.toString());
		order.setQuantity(quantity.toString());
		order.setTotalCost(totalCost.toString());
		order.setEmail(guest);
		order.setFinalBill(String.valueOf(finalCost));
		order.setStatus("ordered");
		
		
		
		
		userService.saveOrder(order);
		
		
		
		
		return "redirect:/orders";
	}
	
	@GetMapping("/orders")
	public String orders(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		if(userdata == null) {
			model.addAttribute("errormsg", "Guest cannot access orders");
			return "home/error";
		}
		
		List<Order> orders = userService.getCustomerOrders(messages.get(0));
		
		if(orders.size() > 0 ) {
			model.addAttribute("flag", 1);
		}
		else {
			model.addAttribute("flag", 0);
		}

		model.addAttribute("orders", orders);

		model.addAttribute("username", userdata.getUsername());
		
		return "user/orders";
		
	}
	
	@PostMapping("/cancelOrder/{id}")
	public String cancelOrder(@PathVariable(name="id") Long id)
	{
		Order order = userService.getOrder(id);
		
		
		userService.cancelOrder(id);
		
		return "redirect:/orders";
	}

}
