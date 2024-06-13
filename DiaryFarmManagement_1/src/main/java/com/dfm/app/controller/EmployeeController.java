package com.dfm.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dfm.app.model.Breed;
import com.dfm.app.model.Cow;
import com.dfm.app.model.CowSale;
import com.dfm.app.model.Feed;
import com.dfm.app.model.Milk;
import com.dfm.app.model.MilkSale;
import com.dfm.app.model.Order;
import com.dfm.app.model.Product;
import com.dfm.app.model.ProductSale;
import com.dfm.app.model.User;
import com.dfm.app.model.Vaccine;
import com.dfm.app.service.AdminService;
import com.dfm.app.service.EmployeeService;
import com.dfm.app.service.UserService;




@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/staff")
	public String getEmployeeWelcomePage(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        List<User> staffs = employeeService.getAllSatff();
        List<Cow> cows = employeeService.findAllCows();
        List<Milk> milk = employeeService.findAllMilk();
        List<MilkSale> milksale = employeeService.findAllMilkSale();
        
        model.addAttribute("staffs", staffs.size());
        model.addAttribute("cows", cows.size());
        int tm=0;
        for(int i=0;i<milk.size();i++) {
        	
        	
        	
        	tm = tm + Integer.parseInt(milk.get(i).getLitre());
        }
        int tms = 0;
        
        for(int i=0;i<milksale.size();i++) {
        	
        	
        	
        	tms = tms + Integer.parseInt(milksale.get(i).getLitre());
        }
        
        model.addAttribute("milk", tm);
        model.addAttribute("milksale", tms);
        
        
        
        
		return "employee/welcomeemployee";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        List<User> staffs = employeeService.getAllSatff();
        List<Cow> cows = employeeService.findAllCows();
        List<Milk> milk = employeeService.findAllMilk();
        List<MilkSale> milksale = employeeService.findAllMilkSale();
        
        model.addAttribute("staffs", staffs.size());
        model.addAttribute("cows", cows.size());
        int tm=0;
        for(int i=0;i<milk.size();i++) {
        	
        	
        	
        	tm = tm + Integer.parseInt(milk.get(i).getLitre());
        }
        int tms = 0;
        
        for(int i=0;i<milksale.size();i++) {
        	
        	
        	
        	tms = tms + Integer.parseInt(milksale.get(i).getLitre());
        }
        
        model.addAttribute("milk", tm);
        model.addAttribute("milksale", tms);
        
        
        
        
		return "employee/dashboard";
	}
	
	@GetMapping("/doctor")
	public String getDoctorWelcomePage(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
		return "doctor/welcomedoctor";
	}
	
	@GetMapping("/product")
	public String product(Model model) {
		
		List<Product> products = employeeService.findAllProducts();
		model.addAttribute("products", products);
		Product product = new Product();
		model.addAttribute("product", product);
		return "employee/product";
		
	}
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product product, Model model, HttpSession session, @RequestParam("image") MultipartFile productImage)
	{
			try {
				product.setPhoto(Base64.getEncoder().encodeToString(productImage.getBytes()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			employeeService.saveProduct(product);
		
			return "redirect:/product";
		
	}

	@GetMapping("/editProduct/{id}")
	public String editProduct(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Product product = employeeService.getProductById(id);
		model.addAttribute("product", product);
		
        model.addAttribute("sessionMessages", messages);
		
		return "employee/updateproduct";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product, Model model, HttpSession session, @RequestParam("image") MultipartFile productImage)
	{
		System.out.println("product updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
       
        
        if(!productImage.getOriginalFilename().isEmpty()) {
        	try {
        		
				product.setPhoto(Base64.getEncoder().encodeToString(productImage.getBytes()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
        }
        else {
        	Product m = employeeService.getProductById(product.getId());
        	product.setPhoto(m.getPhoto());
        }
		
		employeeService.updateProduct(product);
		
			return "redirect:/product";
		
	}
	
	@PostMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteProduct(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/product";
		
	}
	
	@GetMapping("/productsale")
	public String productsale(Model model) {
		
		List<ProductSale> productsales = employeeService.findAllProductSale();
		model.addAttribute("productsales", productsales);
		ProductSale productsale = new ProductSale();
		model.addAttribute("productsale", productsale);
		List<Product> products = employeeService.findAllProducts();
		model.addAttribute("products", products);
		return "employee/productsale";
		
	}
	@PostMapping("/saveProductSale")
	public String saveProductSale(@ModelAttribute("productsale") ProductSale productsale, Model model, HttpSession session)
	{
			
			employeeService.saveProductSale(productsale);
		
			return "redirect:/productsale";
		
	}

	@GetMapping("/editProductSale/{id}")
	public String editProductSale(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		ProductSale productsale = employeeService.getProductSaleById(id);
		model.addAttribute("productsale", productsale);
		
        model.addAttribute("sessionMessages", messages);
		
		return "employee/updateproductsale";
	}
	
	@PostMapping("/updateProductSale")
	public String updateProductSale(@ModelAttribute("productsale") ProductSale productsale, Model model, HttpSession session)
	{
		System.out.println("product updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
       
      
		
		employeeService.updateProductSale(productsale);
		
			return "redirect:/productsale";
		
	}
	
	@PostMapping("/deleteProductSale/{id}")
	public String deleteProductSale(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteProductSale(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/productsale";
		
	}
	
	@GetMapping("/cow")
	public String cow(Model model) {
		
		List<Cow> cows = employeeService.findAllCows();
		model.addAttribute("cows", cows);
		Cow cow = new Cow();
		model.addAttribute("cow", cow);
		return "employee/cow";
		
	}
	
	@PostMapping("/saveCow")
	public String saveCow(@ModelAttribute("cow") Cow cow, Model model, HttpSession session)
	{
		System.out.println("cow updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.saveCow(cow);
		
			return "redirect:/cow";
		
	}

	@GetMapping("/editCow/{id}")
	public String editCow(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Cow cow = employeeService.getCowById(id);
		model.addAttribute("cow", cow);
		
        model.addAttribute("sessionMessages", messages);
		
		return "employee/updatecow";
	}
	
	@PostMapping("/updateCow")
	public String updateCow(@ModelAttribute("cow") Cow cow, Model model, HttpSession session)
	{
		System.out.println("cow updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.updateCow(cow);
		
			return "redirect:/cow";
		
	}
	
	@PostMapping("/deleteCow/{id}")
	public String deleteCow(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteCow(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/cow";
		
	}
	
	@GetMapping("/cowsale")
	public String cowsale(Model model) {
		
		List<CowSale> cowsales = employeeService.findAllCowSale();
		model.addAttribute("cowsales", cowsales);
		CowSale cowsale = new CowSale();
		model.addAttribute("cowsale", cowsale);
		return "employee/cowsale";
		
	}
	
	/* @PostMapping("/saveCowSale")
	public String saveCowSale(@ModelAttribute("cowsale") CowSale cowsale, Model model, HttpSession session)
	{
		System.out.println("cowsale updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.saveCowSale(cowsale);
		
			return "redirect:/cowsale";
		
	} */
	
	@PostMapping("/saveCowSale")
	public String saveCowSale(@ModelAttribute("cowsale") CowSale cowsale, Model model, HttpSession session) {
	    System.out.println("cowsale updated");

	    @SuppressWarnings("unchecked")
	    List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

	    if (messages == null) {
	        model.addAttribute("errormsg", "Session Expired. Please Login Again");
	        return "home/error";
	    }
	    model.addAttribute("sessionMessages", messages);

	    employeeService.saveCowSale(cowsale);

	    // Update cow status to "Not Available"
	    Cow cow = employeeService.getCowByControlNumber(cowsale.getCowNumber());
	    if (cow != null) {
	        cow.setStatus("Not Available");
	        employeeService.updateCow(cow);
	    } else {
	        // Handle case where cow is not found
	        // You can log an error or handle it based on your application's requirements
	    }

	    return "redirect:/cowsale";
	}


	@GetMapping("/editCowSale/{id}")
	public String editCowSale(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		CowSale cowsale = employeeService.getCowSaleById(id);
		model.addAttribute("cowsale", cowsale);
		
        model.addAttribute("sessionMessages", messages);
		
		return "employee/updatecowsale";
	}
	
	@PostMapping("/updateCowSale")
	public String updateCowsale(@ModelAttribute("cowsale") CowSale cowsale, Model model, HttpSession session)
	{
		System.out.println("cow updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.updateCowSale(cowsale);
		
			return "redirect:/cowsale";
		
	}
	
	@PostMapping("/deleteCowSale/{id}")
	public String deleteCowSale(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteCowSale(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/cowsale";
		
	}
	
	@GetMapping("/milk")
	public String milk(Model model) {
		
		List<Milk> milks = employeeService.findAllMilk();
		model.addAttribute("milks", milks);
		Milk milk = new Milk();
		model.addAttribute("milk", milk);
		return "employee/milk";
		
	}
	
	@PostMapping("/saveMilk")
	public String saveMilk(@ModelAttribute("milk") Milk milk, Model model, HttpSession session)
	{
		System.out.println("milk updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.saveMilk(milk);
		
			return "redirect:/milk";
		
	}

	@GetMapping("/editMilk/{id}")
	public String editMilk(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Milk milk = employeeService.getMilkById(id);
		model.addAttribute("milk", milk);
		
        model.addAttribute("sessionMessages", messages);
		
		return "employee/updatemilk";
	}
	
	@PostMapping("/updateMilk")
	public String updateMilk(@ModelAttribute("milk") Milk milk, Model model, HttpSession session)
	{
		System.out.println("cow updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.updateMilk(milk);
		
			return "redirect:/milk";
		
	}
	
	@PostMapping("/deleteMilk/{id}")
	public String deleteMilk(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteMilk(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/milk";
		
	}
	
	@GetMapping("/milksale")
	public String milksale(Model model) {
		
		List<MilkSale> milksales = employeeService.findAllMilkSale();
		model.addAttribute("milksales", milksales);
		MilkSale milksale = new MilkSale();
		model.addAttribute("milksale", milksale);
		return "employee/milksale";
		
	}
	
	@PostMapping("/saveMilkSale")
	public String saveMilkSale(@ModelAttribute("milksale") MilkSale milksale, Model model, HttpSession session)
	{
		System.out.println("milksale updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.saveMilkSale(milksale);
		
			return "redirect:/milksale";
		
	}

	@GetMapping("/editMilkSale/{id}")
	public String editMilkSale(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		MilkSale milksale = employeeService.getMilkSaleById(id);
		model.addAttribute("milksale", milksale);
		
        model.addAttribute("sessionMessages", messages);
		
		return "employee/updatemilksale";
	}
	
	@PostMapping("/updateMilkSale")
	public String updateMilkSale(@ModelAttribute("milksale") MilkSale milksale, Model model, HttpSession session)
	{
		System.out.println("cow updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.updateMilkSale(milksale);
		
			return "redirect:/milksale";
		
	}
	
	@PostMapping("/deleteMilkSale/{id}")
	public String deleteMilkSale(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteMilkSale(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/milksale";
		
	}
	
	@GetMapping("/feed")
	public String feed(Model model) {
		
		List<Feed> feeds = employeeService.findAllFeed();
		model.addAttribute("feeds", feeds);
		Feed feed = new Feed();
		model.addAttribute("feed", feed);
		return "employee/feed";
		
	}
	
	@PostMapping("/saveFeed")
	public String saveFeed(@ModelAttribute("feed") Feed feed, Model model, HttpSession session)
	{
		System.out.println("feed updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.saveFeed(feed);
		
			return "redirect:/feed";
		
	}

	@GetMapping("/editFeed/{id}")
	public String editFeed(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Feed feed = employeeService.getFeedById(id);
		model.addAttribute("feed", feed);
		
        model.addAttribute("sessionMessages", messages);
		
		return "employee/updatefeed";
	}
	
	@PostMapping("/updateFeed")
	public String updateFee(@ModelAttribute("feed") Feed feed, Model model, HttpSession session)
	{
		System.out.println("feed updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.updateFeed(feed);
		
			return "redirect:/feed";
		
	}
	
	@PostMapping("/deleteFeed/{id}")
	public String deleteFeed(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteFeed(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/feed";
		
	}
	
	@GetMapping("/vaccine")
	public String vaccine(Model model) {
		
		List<Vaccine> vaccines = employeeService.findAllVaccine();
		
		List<Cow> cows = employeeService.findAllCows();
		
		List<Cow> vaccinated = new ArrayList<>();
		List<Cow> notVaccinated = new ArrayList<>();
		
		for(int i=0;i<cows.size();i++) {
			int f = 0;
			for(int j=0;j<vaccines.size();j++) {
				if(cows.get(i).getControlNumber().equals(vaccines.get(j).getCowNumber())) {
					vaccinated.add(cows.get(i));
					f=1;
					break;
					
				}
			}
			if(f==0) {
				notVaccinated.add(cows.get(i));
			}
		}
		
		
		model.addAttribute("vaccinated", vaccines);
		model.addAttribute("notvaccinated", notVaccinated);
		Vaccine vaccine = new Vaccine();
		model.addAttribute("vaccine", vaccine);
		return "employee/vaccine";
		
	}
	
	@PostMapping("/saveVaccine")
	public String saveVaccine(@ModelAttribute("vaccine") Vaccine vaccine, Model model, HttpSession session)
	{
		System.out.println("vaccine updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.saveVaccine(vaccine);
		
			return "redirect:/vaccine";
		
	}

	@GetMapping("/editVaccine/{id}")
	public String editVaccine(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Vaccine vaccine = employeeService.getVaccineById(id);
		model.addAttribute("vaccine", vaccine);
		
        model.addAttribute("sessionMessages", messages);
		
		return "employee/updatevaccine";
	}
	
	@PostMapping("/updateVaccine")
	public String updateVaccine(@ModelAttribute("vaccine") Vaccine vaccine, Model model, HttpSession session)
	{
		System.out.println("vaccine updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.updateVaccine(vaccine);
		
			return "redirect:/vaccine";
		
	}
	
	@PostMapping("/deleteVaccine/{id}")
	public String deleteVaccine(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteVaccine(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/vaccine";
		
	}
	
	@GetMapping("/breed")
	public String breed(Model model) {
		
		List<Breed> breeds = employeeService.findAllBreed();
		model.addAttribute("breeds", breeds);
		Breed breed = new Breed();
		model.addAttribute("breed", breed);
		return "employee/breed";
		
	}
	
	@PostMapping("/saveBreed")
	public String saveBreed(@ModelAttribute("breed") Breed breed, Model model, HttpSession session)
	{
		System.out.println("breed updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.saveBreed(breed);
		
			return "redirect:/breed";
		
	}

	@GetMapping("/editBreed/{id}")
	public String editBreed(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Breed breed = employeeService.getBreedById(id);
		model.addAttribute("breed", breed);
		
        model.addAttribute("sessionMessages", messages);
		
		return "employee/updatebreed";
	}
	
	@PostMapping("/updateBreed")
	public String updateBreed(@ModelAttribute("breed") Breed breed, Model model, HttpSession session)
	{
		System.out.println("breed updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.updateBreed(breed);
		
			return "redirect:/breed";
		
	}
	
	@PostMapping("/deleteBreed/{id}")
	public String deleteBreed(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteBreed(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/breed";
		
	}
	
	@GetMapping("/milks")
	public String milks(Model model) {
		
		List<Milk> milks = employeeService.findAllMilk();
		model.addAttribute("milks", milks);
		Milk milk = new Milk();
		model.addAttribute("milk", milk);
		return "doctor/milks";
		
	}
	
	@PostMapping("/saveMilks")
	public String saveMilks(@ModelAttribute("milk") Milk milk, Model model, HttpSession session)
	{
		System.out.println("milk updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.saveMilk(milk);
		
			return "redirect:/milks";
		
	}

	@GetMapping("/editMilks/{id}")
	public String editMilks(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Milk milk = employeeService.getMilkById(id);
		model.addAttribute("milk", milk);
		
        model.addAttribute("sessionMessages", messages);
		
		return "doctor/updatemilks";
	}
	
	@PostMapping("/updateMilks")
	public String updateMilks(@ModelAttribute("milk") Milk milk, Model model, HttpSession session)
	{
		System.out.println("cow updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.updateMilk(milk);
		
			return "redirect:/milks";
		
	}
	
	@PostMapping("/deleteMilks/{id}")
	public String deleteMilks(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteMilk(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/milks";
		
	}
	
	@GetMapping("/vaccines")
	public String vaccines(Model model) {
		
		List<Vaccine> vaccines = employeeService.findAllVaccine();
		model.addAttribute("vaccines", vaccines);
		Vaccine vaccine = new Vaccine();
		model.addAttribute("vaccine", vaccine);
		return "doctor/vaccines";
		
	}
	
	@PostMapping("/saveVaccines")
	public String saveVaccines(@ModelAttribute("vaccine") Vaccine vaccine, Model model, HttpSession session)
	{
		System.out.println("vaccine updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.saveVaccine(vaccine);
		
			return "redirect:/vaccines";
		
	}

	@GetMapping("/editVaccines/{id}")
	public String editVaccines(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Vaccine vaccine = employeeService.getVaccineById(id);
		model.addAttribute("vaccine", vaccine);
		
        model.addAttribute("sessionMessages", messages);
		
		return "employee/updatevaccines";
	}
	
	@PostMapping("/updateVaccines")
	public String updateVaccines(@ModelAttribute("vaccine") Vaccine vaccine, Model model, HttpSession session)
	{
		System.out.println("vaccine updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
		
		employeeService.updateVaccine(vaccine);
		
			return "redirect:/vaccines";
		
	}
	
	@PostMapping("/deleteVaccines/{id}")
	public String deleteVaccines(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		employeeService.deleteVaccine(id);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
		
			return "redirect:/vaccines";
		
	}
	
	@PostMapping("/updateOrderStatus/{id}")
	public String updateOrderStatus(@PathVariable(name="id") Long id, @RequestParam("status") String status)
	{
		Order order = employeeService.getOrderById(id); 
		
		
		
		
		
		employeeService.updateOrderStatus(id, status);
		
		return "redirect:/userorders";
	}
	
	@GetMapping("/userorders")
	public String userorders(@ModelAttribute("order") Order order, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        
		 
		List<Order> orders = employeeService.getUserOrders();
		int oSize = orders.size();
		if(oSize > 0) {
			model.addAttribute("flag", 1);
		}
		else {
			model.addAttribute("flag", 0);
		}
	        
	    model.addAttribute("orders", orders);

		return "employee/userorders";
	}
	
	@GetMapping("/monthly_sale")
	public ResponseEntity<Map<String, Integer>> getDayGraph() {
		  
		  List<ProductSale> lis = employeeService.getAllProductSale();
		  Calendar mydate = new GregorianCalendar();
		  Map<String, Integer> g =new LinkedHashMap<>();
		  List<String> l = new ArrayList<>();
		  
		  for(int i=0;i<lis.size();i++)
		  {
			  String date = lis.get(i).getDate();
			  DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			  Date dat=null;
			try {
				dat = format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mydate.setTime(dat);
			
			  int key=mydate.get(Calendar.MONTH)+1;
			  String m = "";
			  if(key==1)
				 {
					 m+='"'+"January"+'"';
				 }
				 if(key==2)
				 {
					 m+='"'+"Frebuary"+'"';
				 }
				 if(key==3)
				 {
					 m+='"'+"March"+'"';
				 }
				 if(key==4)
				 {
					 m+='"'+"April"+'"';
				 }
				 if(key==5)
				 {
					 m+='"'+"May"+'"';
				 }
				 if(key==6)
				 {
					 m+='"'+"June"+'"';
				 }
				 if(key==7)
				 {
					 m+='"'+"July"+'"';
				 }
				 if(key==8)
				 {
					 m+='"'+"August"+'"';
				 }
				 if(key==9)
				 {
					 m+='"'+"September"+'"';
				 }
				 if(key==10)
				 {
					 m+='"'+"October"+'"';
				 }
				 if(key==11)
				 {
					 m+='"'+"November"+'"';
				 }
				 if(key==12)
				 {
					 m+='"'+"December"+'"';
				 }
			  
				 int money = g.containsKey(m)? g.get(m) : 0;
				 money = money + Integer.parseInt(lis.get(i).getTotal());
				 
				 g.put(m,money);
			  //System.out.println(month);
		  }
		  
		  
			
		  
//		  model.addAttribute("monthlist",l);
//		  model.addAttribute("monthlyMap", g);
		  return new ResponseEntity<>(g, HttpStatus.OK);
		 }
	
	@GetMapping("/graph")
	public String graph(Model model) {
		
		
		return "employee/monthly_graph";
		
	}
	
	@GetMapping("/products_graph")
	public String products_graph(Model model) {
		
		List<Product> products = employeeService.findAllProducts();
		model.addAttribute("products", products);
		
		return "employee/products_graph";
		
	}
	
	@PostMapping("/product_sale")
	public ResponseEntity<Map<String, Integer>> product_sale(@RequestParam("product") String product) {
		  
		  List<ProductSale> lis = employeeService.getAllProductSale();
		  Calendar mydate = new GregorianCalendar();
		  Map<String, Integer> g =new LinkedHashMap<>();
		  List<String> l = new ArrayList<>();
		  System.out.println("===="+product);
		  for(int i=0;i<lis.size();i++)
		  {
			  if(lis.get(i).getProductName().equals(product)) {
			  
			  
			  String date = lis.get(i).getDate();
			  DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			  Date dat=null;
			try {
				dat = format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mydate.setTime(dat);
			
			  int key=mydate.get(Calendar.MONTH)+1;
			  String m = "";
			  if(key==1)
				 {
					 m+='"'+"January"+'"';
				 }
				 if(key==2)
				 {
					 m+='"'+"Frebuary"+'"';
				 }
				 if(key==3)
				 {
					 m+='"'+"March"+'"';
				 }
				 if(key==4)
				 {
					 m+='"'+"April"+'"';
				 }
				 if(key==5)
				 {
					 m+='"'+"May"+'"';
				 }
				 if(key==6)
				 {
					 m+='"'+"June"+'"';
				 }
				 if(key==7)
				 {
					 m+='"'+"July"+'"';
				 }
				 if(key==8)
				 {
					 m+='"'+"August"+'"';
				 }
				 if(key==9)
				 {
					 m+='"'+"September"+'"';
				 }
				 if(key==10)
				 {
					 m+='"'+"October"+'"';
				 }
				 if(key==11)
				 {
					 m+='"'+"November"+'"';
				 }
				 if(key==12)
				 {
					 m+='"'+"December"+'"';
				 }
			  
				 int money = g.containsKey(m)? g.get(m) : 0;
				 money = money + Integer.parseInt(lis.get(i).getTotal());
				 
				 g.put(m,money);
			  //System.out.println(month);
			  }
		  }
		  
		  
			
		  
//		  model.addAttribute("monthlist",l);
//		  model.addAttribute("monthlyMap", g);
		  return new ResponseEntity<>(g, HttpStatus.OK);
		 }
	
	
	@PostMapping("/daily_sale")
	public ResponseEntity<Map<String, Integer>> productDailysale(@RequestParam("month") String month) {
		  
		  List<ProductSale> lis = employeeService.getAllProductSale();
		  Calendar mydate = new GregorianCalendar();
		  Calendar mydate1 = new GregorianCalendar();
		  Map<String, Integer> g =new LinkedHashMap<>();
		  List<String> l = new ArrayList<>();
		  System.out.println("===="+month);
		  for(int i=0;i<lis.size();i++)
		  {
			  
			  
			  
			  String date = lis.get(i).getDate();
			  DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			  DateFormat forma = new SimpleDateFormat("yyyy-MM");
			  Date dat=null;
			  Date dat1 = null;
			try {
				dat = format.parse(date);
				dat1 = forma.parse(month);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mydate.setTime(dat);
			mydate1.setTime(dat1);
			
			  int key = mydate.get(Calendar.MONTH)+1;
			  int year = mydate.get(Calendar.YEAR);
			  
			  int key1 = mydate1.get(Calendar.MONTH) + 1;
			  int year1 = mydate1.get(Calendar.YEAR);
			  
			  if(year==year1 && key == key1) {
				  
			  
				 int money = g.containsKey(date)? g.get(date) : 0;
				 money = money + Integer.parseInt(lis.get(i).getTotal());
				 
				 g.put(date,money);
			  }
			  //System.out.println(month);
			  
		  }
		  
		  
			
		  
//		  model.addAttribute("monthlist",l);
//		  model.addAttribute("monthlyMap", g);
		  return new ResponseEntity<>(g, HttpStatus.OK);
		 }
	
	@GetMapping("/product_graph")
	public String product_graph(Model model) {
		
		List<Product> products = employeeService.findAllProducts();
		model.addAttribute("products", products);
		
		
		return "employee/product_sale";
		
	}
	
	@GetMapping("/daily_graph")
	public String daily_graph(Model model) {
		
		
		
		return "employee/daily_graph";
		
	}
}
