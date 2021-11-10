package com.training.controller;

import org.slf4j.Logger;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.training.dto.ProductDTO;
import com.training.dto.BuyerDTO;
import com.training.dto.SubscribedProductDTO;
import com.training.entity.Product;
import com.training.entity.SubscribedProduct;
import com.training.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/getproducts")
	public ResponseEntity<Iterable<Product>>getAllProducts() throws Exception {
		logger.info("Searching all products");
		Iterable<Product> products = productService.getProducts();
		ResponseEntity<Iterable<Product>> response = new ResponseEntity<Iterable<Product>>(products,HttpStatus.OK);
		logger.info("Fetched all products Successfully...!");
		return response;
	}
	@RequestMapping("/getsubproducts")
	public ResponseEntity<Iterable<SubscribedProduct>>getAllsubProducts() throws Exception {
		logger.info("Searching subscribed products");
		Iterable<SubscribedProduct> products = productService.getSProducts();
		ResponseEntity<Iterable<SubscribedProduct>> response = new ResponseEntity<Iterable<SubscribedProduct>>(products,HttpStatus.OK);
		logger.info("Found subscribed products Successfully...!");
		return response;
	}
	
	@RequestMapping("/getproducts/{category}")
	public ResponseEntity<Iterable<Product>> getProductsbyCategory(@PathVariable String category) throws Exception {
		logger.info("Searching products categorically");
		Iterable<Product> products = productService.getProductsbyCategory(category);
		ResponseEntity<Iterable<Product>> response = new ResponseEntity<Iterable<Product>>(products,HttpStatus.OK);
		logger.info("Fetched products categorically...!");
		return response;
	}
	
	@RequestMapping("/getproducts/productname/{productname}")
	public ResponseEntity<Iterable<Product>> getProductsbyProductName(@PathVariable String productname) throws Exception {
		logger.info("Searching products by product name");
		Iterable<Product> products = productService.getProductsbyProductName(productname);
		ResponseEntity<Iterable<Product>> response = new ResponseEntity<Iterable<Product>>(products,HttpStatus.OK);
		logger.info("Fetched products by product name...!");
		return response;
	}
	
	@RequestMapping("/getproducts/productid/{productId}")
	public ResponseEntity<Product> getProductsbyProductId(@PathVariable String productId) throws Exception {
		logger.info("Searching products by product Id");
		Product products = productService.getProductsbyProductId(productId);
		ResponseEntity<Product> response = new ResponseEntity<Product>(products,HttpStatus.OK);
		logger.info("Fetched product by product Id...!");
		return response;
	}
	
	@RequestMapping("/getproducts/{productname}/{category}")
	public ResponseEntity<Iterable<Product>> fetchproducts(@PathVariable String productname,@PathVariable String category) throws Exception {
		logger.info("Searching products through product Name and category");
		Iterable<Product> products = productService.fetchProducts(productname,category);
		ResponseEntity<Iterable<Product>> response = new ResponseEntity<Iterable<Product>>(products,HttpStatus.OK);
		logger.info("Fetched products through product Name and category...!");
		return response;
	}
	
	@RequestMapping("/getsubproducts/{productId}/{buyerId}")
	public ResponseEntity<Iterable<SubscribedProduct>> fetchSubproducts(@PathVariable String productId,@PathVariable String buyerId) throws Exception {
		logger.info("Searching details through productId and BuyerId");
		Iterable<SubscribedProduct> products = productService.fetchSubProducts(productId,buyerId);
		ResponseEntity<Iterable<SubscribedProduct>> response = new ResponseEntity<Iterable<SubscribedProduct>>(products,HttpStatus.OK);
		logger.info("Fetched details through  productId and BuyerId...!");
		return response;
	}
	
	@RequestMapping(value = "/getproducts/sellerid/{productId}")
	public ResponseEntity<String> getSellerId(@PathVariable String productId) throws Exception {
		logger.info("Getting sellerid");
		String product = productService.getSellerId(productId);
		String successMessage = product;
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
		logger.info("Fetched sellerId and "+successMessage);
		return response ;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addproducts")
	public ResponseEntity<String> addProduct(@RequestBody Product product) throws Exception {
		logger.info("Ready to Add  Product");
		productService.addProduct(product);
		String successMessage = "Product added Successfully with ID:"+product.getProductId();
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
		logger.info("Added Product  Successfully...!!!");
		return response;	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addsubproducts")
	public ResponseEntity<String> addProducttoWish(@RequestBody SubscribedProduct product) throws Exception {
		logger.info("Ready to Add Subscribed Product");
		BuyerDTO buyerDTO=new RestTemplate().getForObject("http://localhost:8090/api/getbuyer/buyerid/"+product.getBuyerId(), BuyerDTO.class);
		if(buyerDTO.getIsPrivileged().equals("Y")){
			productService.addProducttoWish(product);
			String successMessage = "Product added Successfully with ID:"+product.getProductId();
			ResponseEntity<String> response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
			logger.info("Added Product into subscribed product Successfully...!!!");
			return response;	
		}
		else {
			String successMessage = "Subcription Failed! You have to be privileged buyer";
			ResponseEntity<String> response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
			return response;	
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/products/update/{productId}")
	public ResponseEntity<String> updateProductStock(@RequestBody ProductDTO product, @PathVariable String productId) throws Exception {
		logger.info("Ready to update Product Stock");
		Product products=productService.updateProduct(product, productId);
		String successMessage = "Product Stock Updated Successfully with ID:"+products.getProductId();
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
		logger.info("updated Product Stock Successfully...!!! ");
		return response;	
	}
	//ENTER BUYERID IN PLACE OF PRODUCTID
	@RequestMapping(method=RequestMethod.PUT, value="/subproducts/update/{productId}")
	public ResponseEntity<String> updateSProduct(@RequestBody SubscribedProductDTO product, @PathVariable String productId) throws Exception {
		logger.info("Ready to update Subscribed Product Quantity");
		SubscribedProduct products=productService.updateSProduct(product, productId);
		String successMessage = "Product Quantity Updated Successfully with ID:"+products.getProductId();
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
		logger.info("Updated Subscribed Product Quantity Successfully...!!!");
		return response;	
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/products/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable String productId) throws Exception {
		logger.info("!!!...Ready to Delete Product...!!!");
		productService.deleteProduct(productId);
		String successMessage = "Product Deleted Successfully";
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
		logger.info("!!!...Deleted Product...!!!");
		return response;	
}
	//ENTER BUYERID IN PLACE OF PRODUCTID
	@RequestMapping(method=RequestMethod.DELETE, value="/subproducts/delete/{productId}")
	public ResponseEntity<String> deleteSProduct(@PathVariable String productId) throws Exception {
		logger.info("!!!...Ready to Remove Subscribed Product...!!!");
		productService.deleteSProduct(productId);
		String successMessage = "Subscribed Product Deleted Successfully";
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
		logger.info("!!!...Removed Subscribed Product...!!! ");
		return response;
}
}