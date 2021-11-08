package com.infy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.BuyerDTO;
import com.infy.dto.CartDTO;
import com.infy.dto.LoginDTO;
import com.infy.dto.SellerDTO;
import com.infy.dto.WishlistDTO;
import com.infy.service.BuyerService;
import com.infy.service.SellerService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class UserServiceController {
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private SellerService sellerService;
	

	@PostMapping(value = "buyer/login")
	public ResponseEntity<String> buyerlogin(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
		String message=buyerService.login(loginDTO);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.OK);
		return response;
	}

	@PostMapping(value = "seller/login")
	public ResponseEntity<String> sellerlogin(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
		String message=sellerService.login(loginDTO);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.OK);
		return response;
	}

	
	@GetMapping(value="/buyer")
	public List<BuyerDTO> getAllBuyer() {
		return buyerService.getAllBuyers();
	}
	
	@PostMapping(value = "/buyer/Registration", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addBuyer(@Valid @RequestBody BuyerDTO buyerDTO) throws Exception {
		buyerService.addBuyer(buyerDTO);
		String messege="Registration done successfully";
		return new ResponseEntity<>(messege,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value = "/buyer/{buyerId}")
	public ResponseEntity<String> deleteBuyer(@PathVariable Integer buyerId) throws Exception {
		buyerService.deletebuyer(buyerId);
		String messege="Accont deleted successfully";
		return new ResponseEntity<>(messege, HttpStatus.OK);
	}
	
	@GetMapping(value="/seller")
	public List<SellerDTO> getAllseller() {
		return sellerService.getAllSellers();
	}
	
	@PostMapping(value = "/seller/Registration")
	public ResponseEntity<String> addSeller(@Valid @RequestBody SellerDTO sellerDTO) throws Exception {
	 sellerService.addSeller(sellerDTO);
	 String messege="Registration done successfully";
		return new ResponseEntity<>(messege,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value = "/seller/{sellerId}")
	public ResponseEntity<String> deleteSeller(@PathVariable Integer sellerId) throws Exception {
		sellerService.deleteSeller(sellerId);
		String messege="account deleted successfully";
		return new ResponseEntity<>(messege, HttpStatus.OK);
	}

// add products from product details page to whishlist
	@PostMapping(value = "buyer/addTowishlist", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToWhishList(@RequestBody WishlistDTO wishlistDTO) throws Exception {
		String message=buyerService.addToWhishList(wishlistDTO);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.CREATED);
		return response;
	}
	
//	remove product from wishlist and add to cart
	@PostMapping(value = "buyer/wishlistTocart", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addWishlistToCart(@RequestBody CartDTO cartDTO) {
		String message=buyerService.addToCart(cartDTO);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.CREATED);
		return response;
	}
	
//	add product to cart
	@PostMapping(value = "/buyer/addTocart", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO) {
		String message=buyerService.addProductToCart(cartDTO);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.CREATED);
		return response;
	}
	
//	Remove product from wishlist
	@DeleteMapping(value = "/buyer/wishlist/{prodId}")
	public ResponseEntity<String> deletProductFromWhishlist(@PathVariable Integer prodId) {
		String message=buyerService.deletProductFromWhishlist(prodId);
		ResponseEntity<String> response=new ResponseEntity<String>(message, HttpStatus.CREATED);
		return response;
	}
	
}