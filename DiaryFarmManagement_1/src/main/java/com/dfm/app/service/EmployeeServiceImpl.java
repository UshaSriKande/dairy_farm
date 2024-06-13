package com.dfm.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfm.app.dao.BreedRepo;
import com.dfm.app.dao.CowRepo;
import com.dfm.app.dao.CowSaleRepo;
import com.dfm.app.dao.FeedRepo;
import com.dfm.app.dao.MilkRepo;
import com.dfm.app.dao.MilkSaleRepo;
import com.dfm.app.dao.OrderRepo;
import com.dfm.app.dao.ProductRepo;
import com.dfm.app.dao.ProductSaleRepo;
import com.dfm.app.dao.UserRepo;
import com.dfm.app.dao.VaccineRepo;
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




@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CowRepo cowRepo;
	
	@Autowired
	private CowSaleRepo cowSaleRepo;
	
	@Autowired
	private MilkRepo milkRepo;
	
	@Autowired
	private MilkSaleRepo milkSaleRepo;
	
	@Autowired
	private FeedRepo feedRepo;
	
	@Autowired
	private VaccineRepo vaccineRepo;
	
	@Autowired
	private BreedRepo breedRepo;
	
	@Autowired
	private ProductSaleRepo productSaleRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	
	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepo.getById(id);
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		productRepo.save(product);
		
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productRepo.deleteById(id);
		
	}

	@Override
	public List<Cow> findAllCows() {
		// TODO Auto-generated method stub
		return cowRepo.findAll();
	}

	@Override
	public void updateCow(Cow cow) {
		// TODO Auto-generated method stub
		cowRepo.save(cow);
		
	}

	@Override
	public void deleteCow(Long id) {
		// TODO Auto-generated method stub
		cowRepo.deleteById(id);
		
	}

	@Override
	public Cow getCowById(Long id) {
		// TODO Auto-generated method stub
		return cowRepo.getById(id);
	}

	@Override
	public void saveCow(Cow cow) {
		// TODO Auto-generated method stub
		cowRepo.save(cow);
	}

	@Override
    public List<Milk> findAllMilk() {
        return milkRepo.findAll();
    }

    @Override
    public Milk getMilkById(Long id) {
        return milkRepo.getById(id);
    }

    @Override
    public void updateMilk(Milk milk) {
        milkRepo.save(milk);
    }

    @Override
    public void deleteMilk(Long id) {
        milkRepo.deleteById(id);
    }

    @Override
    public void saveMilk(Milk milk) {
        milkRepo.save(milk);
    }
    
	@Override
	public List<CowSale> findAllCowSale() {
		// TODO Auto-generated method stub
		return cowSaleRepo.findAll();
	}

	@Override
	public void saveCowSale(CowSale cowsale) {
		// TODO Auto-generated method stub
		cowSaleRepo.save(cowsale);
	}

	@Override
	public CowSale getCowSaleById(Long id) {
		// TODO Auto-generated method stub
		return cowSaleRepo.getById(id);
	}

	@Override
    public Cow getCowByControlNumber(String controlNumber) {
        return cowRepo.findByControlNumber(controlNumber);
    }
	
	@Override
	public void updateCowSale(CowSale cowsale) {
		// TODO Auto-generated method stub
		cowSaleRepo.save(cowsale);
		
	}

	@Override
	public void deleteCowSale(Long id) {
		// TODO Auto-generated method stub
		cowSaleRepo.deleteById(id);
		
	}

	@Override
	public List<MilkSale> findAllMilkSale() {
		// TODO Auto-generated method stub
		return milkSaleRepo.findAll();
	}

	@Override
	public void saveMilkSale(MilkSale milksale) {
		// TODO Auto-generated method stub
		milkSaleRepo.save(milksale);
	}

	@Override
	public MilkSale getMilkSaleById(Long id) {
		// TODO Auto-generated method stub
		return milkSaleRepo.getById(id);
	}

	@Override
	public void updateMilkSale(MilkSale milksale) {
		// TODO Auto-generated method stub
		milkSaleRepo.save(milksale);
	}

	@Override
	public void deleteMilkSale(Long id) {
		// TODO Auto-generated method stub
		milkSaleRepo.deleteById(id);
		
	}

	@Override
	public List<Feed> findAllFeed() {
		// TODO Auto-generated method stub
		return feedRepo.findAll();
	}

	@Override
	public void saveFeed(Feed feed) {
		// TODO Auto-generated method stub
		feedRepo.save(feed);
	}

	@Override
	public Feed getFeedById(Long id) {
		// TODO Auto-generated method stub
		return feedRepo.getById(id);
	}

	@Override
	public void updateFeed(Feed feed) {
		// TODO Auto-generated method stub
		feedRepo.save(feed);
		
	}

	@Override
	public void deleteFeed(Long id) {
		// TODO Auto-generated method stub
		feedRepo.deleteById(id);
		
		
	}

	@Override
	public List<Vaccine> findAllVaccine() {
		// TODO Auto-generated method stub
		return vaccineRepo.findAll();
	}

	@Override
	public void saveVaccine(Vaccine vaccine) {
		// TODO Auto-generated method stub
		vaccineRepo.save(vaccine);
		
	}

	@Override
	public Vaccine getVaccineById(Long id) {
		// TODO Auto-generated method stub
		return vaccineRepo.getById(id);
	}

	@Override
	public void updateVaccine(Vaccine vaccine) {
		// TODO Auto-generated method stub
		vaccineRepo.save(vaccine);
		
	}

	@Override
	public void deleteVaccine(Long id) {
		// TODO Auto-generated method stub
		vaccineRepo.deleteById(id);
		
	}

	@Override
	public List<Breed> findAllBreed() {
		// TODO Auto-generated method stub
		return breedRepo.findAll();
	}

	@Override
	public Breed getBreedById(Long id) {
		// TODO Auto-generated method stub
		return breedRepo.getById(id);
	}

	@Override
	public void updateBreed(Breed breed) {
		// TODO Auto-generated method stub
		breedRepo.save(breed);
	}

	@Override
	public void deleteBreed(Long id) {
		// TODO Auto-generated method stub
		breedRepo.deleteById(id);
	}

	@Override
	public void saveBreed(Breed breed) {
		// TODO Auto-generated method stub
		breedRepo.save(breed);
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		productRepo.save(product);
	}

	@Override
	public void deleteProductSale(Long id) {
		// TODO Auto-generated method stub
		productSaleRepo.deleteById(id);

	}

	@Override
	public void updateProductSale(ProductSale productsale) {
		// TODO Auto-generated method stub
		productSaleRepo.save(productsale);
	}

	@Override
	public ProductSale getProductSaleById(Long id) {
		// TODO Auto-generated method stub
		return productSaleRepo.getById(id);
		
	}

	@Override
	public void saveProductSale(ProductSale productsale) {
		// TODO Auto-generated method stub
		productSaleRepo.save(productsale);
	}

	@Override
	public List<ProductSale> findAllProductSale() {
		// TODO Auto-generated method stub
		return productSaleRepo.findAll();
	}

	@Override
	public List<Order> getUserOrders() {
		// TODO Auto-generated method stub
		return orderRepo.findAll();
	}

	@Override
	public void updateOrderStatus(Long id, String status) {
		// TODO Auto-generated method stub
		List<Order> order = orderRepo.findAll().stream().filter(o -> o.getId().equals(id)).collect(Collectors.toList());
				
			
		
		if(order.size() == 1) {
			Order o = order.get(0);
			o.setStatus(status);
			orderRepo.save(o);
		}
	}

	@Override
	public Order getOrderById(Long id) {
		// TODO Auto-generated method stub
		return orderRepo.getById(id);
	}

	@Override
	public List<ProductSale> getAllProductSale() {
		// TODO Auto-generated method stub
		return productSaleRepo.findAll();
	}

	@Override
	public List<User> getAllSatff() {
		// TODO Auto-generated method stub
		return userRepo.findAll().stream().filter(u -> u.getUsertype().equals("employee")||u.getUsertype().equals("doctor")).collect(Collectors.toList());
	}

	
	
	

}
