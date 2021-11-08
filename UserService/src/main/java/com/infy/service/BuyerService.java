package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.infy.dto.BuyerDTO;
import com.infy.dto.CartDTO;
import com.infy.dto.LoginDTO;
import com.infy.dto.WishlistDTO;
import com.infy.entity.Buyer;
import com.infy.entity.BuyerLogin;
import com.infy.entity.Cart;
import com.infy.entity.Wishlist;
import com.infy.exception.InfyOrderException;
import com.infy.repository.BuyerLoginRepository;
import com.infy.repository.BuyerRepository;
import com.infy.repository.CartRepository;
import com.infy.repository.WishlistRepository;

@Service(value = "buyerService")
@Transactional
public class BuyerService{

	@Autowired
	private BuyerRepository buyerRepository;
	@Autowired
	private BuyerLoginRepository buyerLoginRepository;
	@Autowired
	private WishlistRepository wishlistRepository;
	@Autowired
	private CartRepository cartRepository;
	
	public void addBuyer(BuyerDTO buyerDTO) throws InfyOrderException{
		Optional<Buyer> optional = buyerRepository.findById(buyerDTO.getBuyerId());
		if (optional.isPresent())
			throw new InfyOrderException("Service.BUYER_FOUND");
		Buyer buyer = new Buyer();
		buyer.setBuyerId(buyerDTO.getBuyerId());
		buyer.setName(buyerDTO.getName());
		buyer.setEmailId(buyerDTO.getEmailId());
		buyer.setPhoneNo(buyerDTO.getPhoneNo());
		buyer.setPassword(buyerDTO.getPassword());
		buyer.setIsPrivileged(buyerDTO.getIsPrivileged());
		buyer.setRewardPoints(buyerDTO.getRewardPoints());
		buyer.setIsActive(buyerDTO.getIsActive());
		buyerRepository.save(buyer);
	}	
	
	public String login(LoginDTO loginDTO) throws InfyOrderException{          
		BuyerLogin buyerlogin=buyerLoginRepository.findById(loginDTO.getEmailId()).orElse(null);
		if(buyerlogin == null)
			return "Buyer does not exits. Invalid credential...";
		if(loginDTO.getEmailId() == null)
			return "EmailId is Incorrect...";
		if(!buyerlogin.getPassword().equals(loginDTO.getPassword()))
			return "Password is Incorrect ...";
		buyerlogin.setIsActive("Y");;
		buyerLoginRepository.save(buyerlogin);
		return "Login Successfull..";
		

	}
	
	public void deletebuyer(Integer buyerId) throws InfyOrderException{
		Optional<Buyer> optional = buyerRepository.findById(buyerId);
		optional.orElseThrow(() -> new InfyOrderException("Service.BUYER_NOT_FOUND"));
		buyerRepository.deleteById(buyerId);
	}
	
	public List<BuyerDTO> getAllBuyers() {
		List<Buyer> buyers = (List<Buyer>) buyerRepository.findAll();
		List<BuyerDTO> buyerDTOs = new ArrayList<BuyerDTO>();
		for(Buyer buyer : buyers) {
			BuyerDTO buyerDTO = new BuyerDTO();
			buyerDTO.setBuyerId(buyer.getBuyerId());
			buyerDTO.setName(buyer.getName());
			buyerDTO.setEmailId(buyer.getEmailId());
			buyerDTO.setPhoneNo(buyer.getPhoneNo());
			buyerDTO.setPassword(buyer.getPassword());
			buyerDTO.setIsPrivileged(buyer.getIsPrivileged());
			buyerDTO.setRewardPoints(buyer.getRewardPoints());
			buyerDTO.setIsActive(buyer.getIsActive());
			buyerDTOs.add(buyerDTO);
		}
		return buyerDTOs;
	}
	
	public String errormsg="Buyer is Inactive. Please Login";
	public boolean buyerState(Integer buyerId) {
		Buyer buyer = buyerRepository.findById(buyerId).get();
		if(buyer.getIsActive()=="N")
			return false;
		return true;
	}
	
	public String addToWhishList(WishlistDTO wishlistDTO) {
		Wishlist whishlist=wishlistRepository.findById(wishlistDTO.getBuyerId()).orElse(null);
		if(buyerState(wishlistDTO.getBuyerId())) {
			if(whishlist!=null)
				return "Product Already exist...";
			Wishlist product = new Wishlist();
			product.setBuyerId(wishlistDTO.getBuyerId());
			product.setProductId(wishlistDTO.getProductId());
			wishlistRepository.save(product);
			return "Product addedto whishlist";
		}
			return errormsg;
	}

	public String addToCart(CartDTO cartDTO) {
		Cart cart=cartRepository.findById(cartDTO.getBuyerId()).orElse(null);
		Wishlist whishlist=wishlistRepository.findById(cartDTO.getBuyerId()).orElse(null);
		if(buyerState(cartDTO.getBuyerId())) {
			if(whishlist==null)
				return "product does not exist in whishlist";
			if(cart!=null)
				return "Product Already exist in cart...";
			Cart product = new Cart();
			product.setBuyerId(cartDTO.getBuyerId());
			product.setProductId(cartDTO.getProductId());
			product.setQuantity(cartDTO.getQuantity());
			Integer product2=product.getProductId();
			cartRepository.save(product);
			wishlistRepository.deleteById(product2);
			return "Product moved from whishlist to cart";
		}
		return errormsg;
	}
	
	public String addProductToCart(CartDTO cartDTO) {
		Cart cart=cartRepository.findById(cartDTO.getBuyerId()).orElse(null);
		if(buyerState(cartDTO.getBuyerId())) {
			if(cart!=null)
				return "Product Already exist in cart...";
			Cart product = new Cart();
			product.setBuyerId(cartDTO.getBuyerId());
			product.setProductId(cartDTO.getProductId());
			product.setQuantity(cartDTO.getQuantity());
			cartRepository.save(product);
			return "successfully added Product to cart";
		}
		return errormsg;
	}

	public String deletProductFromWhishlist(Integer prodId) {
		Wishlist whishlist=wishlistRepository.findById(prodId).orElse(null);
		if(whishlist==null)
			return "Product does not exist";
		if(buyerState(whishlist.getBuyerId())) {
			wishlistRepository.deleteById(prodId);
			return "product deleted from whishlist";
		}
		return errormsg;
	}
	
}
