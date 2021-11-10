package com.training.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.dto.ProductDTO;
import com.training.dto.SubscribedProductDTO;
import com.training.entity.Product;
import com.training.entity.SubscribedProduct;
import com.training.repository.ProductRepository;
import com.training.repository.SubscribedProductRepository;

@Service(value="productService")
@Transactional
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SubscribedProductRepository subRepo;
	
	public Iterable<Product> getProducts() throws Exception
	{
		Iterable<Product> products = productRepository.findAll();
		if(products.iterator().hasNext())
		{
			logger.info("products are shown successfully");
			return products;
	}
		else
		{
			logger.info("products are not available");
			throw new Exception("No Products Available!!");
		}
	}
	public Iterable<Product> getProductsbyCategory(String category) throws Exception
	{
		Iterable<Product> products = productRepository.findDistinctBycategory(category);
		if(products.iterator().hasNext())
		{
			logger.info("products are shown according to category");
			return products;
	}
		else
		{
			logger.info("This category type products are not available");
			throw new Exception("No Products Available!!");
		}
	}
	public Iterable<Product> getProductsbyProductName(String productname) throws Exception
	{
		Iterable<Product> products = productRepository.findDistinctByproductname(productname);
		if(products.iterator().hasNext())
		{
			logger.info("products are shown according to product Name");
			return products;
	}
		else
		{
			logger.info("This product Name products are not available");
			throw new Exception("No Products Available!!");
		}
	}
	
	public Product getProductsbyProductId(String productId) throws Exception
	{
		Product products = productRepository.findById(productId).orElse(null);
		if(products!=null)
		{
			logger.info("products are shown according to product Id");
			return products;
	}
		else
		{
			logger.info("This product Id is not available");
			throw new Exception("No Product Available!!");
		}
	}
	
	public Iterable<Product> fetchProducts(String productname, String category) throws Exception
	{
		Iterable<Product> products = productRepository.fetchProducts(productname, category);
		if(products.iterator().hasNext())
		{
			logger.info("products are shown according to productname & category ");
			return products;
	}
		else
		{
			logger.info("This productname & category type products are not available");
			throw new Exception("No Products Available!!");
		}
	}
	public Iterable<SubscribedProduct> fetchSubProducts(String productId, String buyerId) throws Exception
	{
		Iterable<SubscribedProduct> products = subRepo.fetchSubProducts(productId, buyerId);
		if(products.iterator().hasNext())
		{
			logger.info("details are shown according to  productId & BuyerId ");
			return products;
	}
		else
		{
			logger.info("This  productId & BuyerId details are not available");
			throw new Exception("No Details Available!!");
		}
	}
	public String getSellerId(String productId) throws Exception {
		Product product = productRepository.findById(productId).orElse(null);
		if(product==null)
			throw new Exception("Product does Not Exist");
		return product.getSellerId();
	}
	
	public String getBuyerId(String buyerId) throws Exception {
		SubscribedProduct product = subRepo.findById(buyerId).orElse(null);
		if(product==null)
			throw new Exception("Product does Not Exist");
		return product.getBuyerId();
	}
	public String addProduct(Product product) throws Exception
	{
		Product products = productRepository.findById(product.getProductId()).orElse(null);
		if(products!=null)
		{
			throw new Exception("Product Already Exists");
		}
		productRepository.save(product);
		logger.info("added successfully");
		return "Product Added Successfully";
	}
	
	public Product updateProduct(ProductDTO product,String productId) throws Exception
	{
		Product existingproduct=productRepository.findById(productId).orElse(null);
		if(existingproduct!=null)
		{
			existingproduct.setStock(product.getStock());
			logger.info("STOCK VALUE IS UPDATED SUCCESSFULLY");
				return productRepository.save(existingproduct);
		}
		else {
			logger.info("PRODUCT NOT FOUND \t SO, STOCK VALUE IS NOT UPDATED!!!");
			throw new Exception("No Product Found");
		}
		
	}
	public void deleteProduct(String productId) throws Exception
	{
		Product product = productRepository.findById(productId).orElse(null);
		if(product==null)
		{
			throw new Exception("No Products available!!");
		}
		productRepository.deleteById(productId);
	}
	public Iterable<SubscribedProduct> getSProducts() throws Exception
	{
		Iterable<SubscribedProduct> products = subRepo.findAll();
		if(products.iterator().hasNext())
		{
			logger.info("subscribed products are shown successfully");
			return products;
	}
		else
		{
			logger.info("subscribed products are not present");
			throw new Exception("No Products Available!!");
		}
	}
	public void deleteSProduct(String productId) throws Exception
	{
		SubscribedProduct product = subRepo.findById(productId).orElse(null);
		if(product==null)
		{
			throw new Exception("No Products available!!");
		}
		subRepo.deleteById(productId);
	}
	public String addProducttoWish(SubscribedProduct product) throws Exception
	{
		SubscribedProduct products = subRepo.findById(product.getProductId()).orElse(null);
		if(products!=null)
		{
			throw new Exception("SUBProduct Already Exists");
		}
		subRepo.save(product);
		logger.info("added successfully");
		return "Product Added Successfully";
	}
	public SubscribedProduct updateSProduct(SubscribedProductDTO product,String productId) throws Exception
	{
		SubscribedProduct existingsubproduct=subRepo.findById(productId).orElse(null);
		if(existingsubproduct!=null)
		{
			existingsubproduct.setQuantity(product.getQuantity());
			logger.info("QUANTITY VALUE IS UPDATED SUCCESSFULLY");
				return subRepo.save(existingsubproduct);
		}
		else {
			logger.info("PRODUCT NOT FOUND \t SO, QUANTITY VALUE IS NOT UPDATED!!!");
			throw new Exception("No Subscribed Product Found");
		}
		
	}
}
