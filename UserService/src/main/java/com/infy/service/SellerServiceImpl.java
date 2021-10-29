package com.infy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.SellerDTO;
import com.infy.entity.Seller;
import com.infy.exception.InfyOrderException;
import com.infy.repository.SellerRespository;

@Service(value = "sellerService")
@Transactional
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerRespository sellerRespository;

	@Override
	public void addSeller(SellerDTO sellerDTO) throws InfyOrderException{
		Optional<Seller> optional = sellerRespository.findById(sellerDTO.getSellerId());
		if (optional.isPresent())
			throw new InfyOrderException("Service.BUYER_FOUND");
		Seller seller = new Seller();
		seller.setSellerId(sellerDTO.getSellerId());
		seller.setEmailId(sellerDTO.getEmailId());
		seller.setName(sellerDTO.getName());   
		seller.setPhoneNo(sellerDTO.getPhoneNo());
		seller.setPassword(sellerDTO.getPassword());
		seller.setIsActive(sellerDTO.getIsActive());
		sellerRespository.save(seller);
	}	
	
	public void login(String emailId,String password) throws InfyOrderException{          
		
	}
	
	@Override
	public void deleteSeller(Integer sellerId) throws InfyOrderException{
		Optional<Seller> optional = sellerRespository.findById(sellerId);
		optional.orElseThrow(() -> new InfyOrderException("Service.BUYER_NOT_FOUND"));
		sellerRespository.deleteById(sellerId);
	}
	
}
