package com.dfm.app.service;

import java.util.List;

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



public interface EmployeeService {

	List<Product> findAllProducts();

	Product getProductById(Long id);

	void updateProduct(Product product);

	void deleteProduct(Long id);

	List<Cow> findAllCows();

	void updateCow(Cow cow);

	void deleteCow(Long id);

	Cow getCowById(Long id);

	void saveCow(Cow cow);
	
	List<Milk> findAllMilk();

    Milk getMilkById(Long id);

    void updateMilk(Milk milk);

    void deleteMilk(Long id);

    void saveMilk(Milk milk);

	List<CowSale> findAllCowSale();

	void saveCowSale(CowSale cowsale);

	CowSale getCowSaleById(Long id);
	
	Cow getCowByControlNumber(String controlNumber);

	void updateCowSale(CowSale cowsale);

	void deleteCowSale(Long id);

	List<MilkSale> findAllMilkSale();

	void saveMilkSale(MilkSale milksale);

	MilkSale getMilkSaleById(Long id);

	void updateMilkSale(MilkSale milksale);

	void deleteMilkSale(Long id);

	List<Feed> findAllFeed();

	void saveFeed(Feed feed);

	Feed getFeedById(Long id);

	void updateFeed(Feed feed);

	void deleteFeed(Long id);

	List<Vaccine> findAllVaccine();

	void saveVaccine(Vaccine vaccine);

	Vaccine getVaccineById(Long id);

	void updateVaccine(Vaccine vaccine);

	void deleteVaccine(Long id);

	List<Breed> findAllBreed();

	Breed getBreedById(Long id);

	void updateBreed(Breed breed);

	void deleteBreed(Long id);

	void saveBreed(Breed breed);

	void saveProduct(Product product);

	void deleteProductSale(Long id);

	void updateProductSale(ProductSale productsale);

	ProductSale getProductSaleById(Long id);

	void saveProductSale(ProductSale productsale);

	List<ProductSale> findAllProductSale();

	List<Order> getUserOrders();

	void updateOrderStatus(Long id, String status);

	Order getOrderById(Long id);

	List<ProductSale> getAllProductSale();

	List<User> getAllSatff();
	
	
	
	
	

	

	
	

}
