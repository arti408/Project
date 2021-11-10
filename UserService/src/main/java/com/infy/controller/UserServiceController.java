package com.infy.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.dto.BuyerDTO;
import com.infy.dto.CartDTO;
import com.infy.dto.LoginDTO;
import com.infy.dto.ProductDTO;
import com.infy.dto.SellerDTO;
import com.infy.dto.WishlistDTO;
import com.infy.service.BuyerService;
import com.infy.service.SellerService;
import com.infy.entity.Buyer;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class UserServiceController {
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private SellerService sellerService;

	//get buyer by buyer id
	@RequestMapping("/getbuyer/buyerid/{buyerId}")
	public ResponseEntity<Buyer> getProductsbyProductId(@PathVariable Integer buyerId) throws Exception {
		Buyer buyer = buyerService.getbuyerbybuyerId(buyerId);
		ResponseEntity<Buyer> response = new ResponseEntity<Buyer>(buyer,HttpStatus.OK);
		return response;
	}
	
    //Buyer Login
	@PostMapping(value = "buyer/login")
	public ResponseEntity<String> buyerlogin(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
		String message=buyerService.login(loginDTO);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.OK);
		return response;
	}

	//Seller Login
	@PostMapping(value = "seller/login")
	public ResponseEntity<String> sellerlogin(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
		String message=sellerService.login(loginDTO);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.OK);
		return response;
	}

    
	//Get all Buyers
	@GetMapping(value="/buyer")
	public List<BuyerDTO> getAllBuyer() {
		return buyerService.getAllBuyers();
	}
	
	//Buyer Registration
	@PostMapping(value = "/buyer/Registration", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addBuyer(@Valid @RequestBody BuyerDTO buyerDTO) throws Exception {
		buyerService.addBuyer(buyerDTO);
		String messege="You have Successfully Registered";
		return new ResponseEntity<>(messege,HttpStatus.CREATED);
	}
	
	//delete buyer account
	@DeleteMapping(value = "/buyer/{buyerId}")
	public ResponseEntity<String> deleteBuyer(@PathVariable Integer buyerId) throws Exception {
		buyerService.deletebuyer(buyerId);
		String messege="Accont deleted successfully";
		return new ResponseEntity<>(messege, HttpStatus.OK);
	}
	
	//Get all Sellers
	@GetMapping(value="/seller")
	public List<SellerDTO> getAllseller() {
		return sellerService.getAllSellers();
	}
	
	//Seller Registration
	@PostMapping(value = "/seller/Registration")
	public ResponseEntity<String> addSeller(@Valid @RequestBody SellerDTO sellerDTO) throws Exception {
	 sellerService.addSeller(sellerDTO);
	 String messege="You have Successfully Registered";
		return new ResponseEntity<>(messege,HttpStatus.CREATED);
	}
	
	
	//delete Seller account
	@DeleteMapping(value = "/seller/{sellerId}")
	public ResponseEntity<String> deleteSeller(@PathVariable Integer sellerId) throws Exception {
		sellerService.deleteSeller(sellerId);
		String messege="account deleted successfully";
		return new ResponseEntity<>(messege, HttpStatus.OK);
	}

   // add product to whishlist
	@PostMapping(value = "buyer/addTowishlist", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToWhishList(@RequestBody WishlistDTO wishlistDTO) throws Exception {
		
		ProductDTO productDTO=new RestTemplate().getForObject("http://localhost:8200/api/getproducts/productid/"+wishlistDTO.getProductId(), ProductDTO.class);
		 if(Integer.parseInt(productDTO.getProductId())==wishlistDTO.getProductId()) {
			 String message=buyerService.addToWhishList(wishlistDTO);
			 ResponseEntity<String> response=new ResponseEntity<String>("Product added to wishlist successfully", HttpStatus.CREATED);
				return response;
		 }
		 else {
			 ResponseEntity<String> response=new ResponseEntity<String>("This Product is not available..", HttpStatus.CREATED);
				return response; 
		 }
	}
	
    //remove product from wishlist and add to cart
	@PostMapping(value = "buyer/wishlistTocart", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addWishlistToCart(@RequestBody CartDTO cartDTO) {
		String message=buyerService.addToCart(cartDTO);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.CREATED);
		return response;
	}
	
	//	add product to cart
	@PostMapping(value = "/buyer/addTocart", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO) {
		ProductDTO productDTO=new RestTemplate().getForObject("http://localhost:8200/api/getproducts/productid/"+cartDTO.getProductId(), ProductDTO.class);
		if(Integer.parseInt(productDTO.getProductId())==cartDTO.getProductId()) {
			 String message=buyerService.addToCart(cartDTO);
			 ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.CREATED);
				return response;
		 }
		 else {
			 ResponseEntity<String> response=new ResponseEntity<String>("This Product is not available..", HttpStatus.CREATED);
				return response; 
		 }
	}
	
	//	Remove product from wishlist
	@DeleteMapping(value = "/buyer/wishlist/{prodId}")
	public ResponseEntity<String> deletProductFromWhishlist(@PathVariable Integer prodId) {
		String message=buyerService.deletProductFromWhishlist(prodId);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.CREATED);
		return response;
	}
	
//	Remove product from cart
	@DeleteMapping(value = "/buyer/cart/{prodId}")
	public ResponseEntity<String> deletProductFromCart(@PathVariable Integer prodId) {
		String message=buyerService.deletProductFromCart(prodId);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.CREATED);
		return response;
	}
	
/*	//Add product
	@PostMapping(value = "seller/product")
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) throws Exception {
		sellerService.sellerState(productDTO.getSellerId());
		String message=new RestTemplate().postForObject("http://localhost:8200/api/addproducts", productDTO, String.class);
		return new ResponseEntity<String>("Product Successfully added..", HttpStatus.OK);
	}
	
	//Delete Product
	@RequestMapping(method=RequestMethod.DELETE ,value = "seller/product/{prodId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteProduct(@PathVariable String prodId) throws Exception {
		String sellerId= new RestTemplate().getForObject("http://localhost:8200/api//getproducts/sellerid/"+prodId,String.class);
		Integer sellerid=Integer.parseInt(sellerId);
		sellerService.sellerState(sellerid);
		new RestTemplate().delete("http://localhost:8200/api/products/delete/"+prodId,String.class);
		return new ResponseEntity<String>("Product Successfully deleted..", HttpStatus.OK);
	}
	
	//Update Stock
	@PutMapping(value = "seller/product/updatestock/{prodId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> UpdateStock(@RequestBody ProductDTO productDTO,@PathVariable String prodId) throws Exception {
		sellerService.sellerState(productDTO.getSellerId());
		new RestTemplate().put("http://localhost:8200/api/products/update/"+prodId, productDTO, List.class);
		return new ResponseEntity<String>("Product Successfully updated..", HttpStatus.OK);
	}
	
*/
}