package com.infy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.BuyerDTO;
import com.infy.entity.Buyer;
import com.infy.exception.InfyOrderException;
import com.infy.repository.BuyerRespository;

@Service(value = "BuyerService")
@Transactional
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private BuyerRespository buyerRespository;

	@Override
	public void addBuyer(BuyerDTO buyerDTO) throws InfyOrderException{
		Optional<Buyer> optional = buyerRespository.findById(buyerDTO.getBuyerId());
		if (optional.isPresent())
			throw new InfyOrderException("Service.BUYER_FOUND");
		Buyer buyer = new Buyer();
		buyer.setBuyerId(buyerDTO.getBuyerId());
		buyer.setEmailId(buyerDTO.getEmailId());
		buyer.setName(buyerDTO.getName());
		buyer.setPhoneNo(buyerDTO.getPhoneNo());
		buyer.setPassword(buyerDTO.getPassword());
		buyer.setIsPrivileged(buyerDTO.getIsPrivileged());
		buyer.setRewardPoints(buyerDTO.getRewardPoints());
		buyer.setIsActive(buyerDTO.getIsActive());
		buyerRespository.save(buyer);
	}	
	
	public void login(String emailId,String password) throws InfyOrderException{          
		
	}
	
	@Override
	public void deletebuyer(Integer buyerId) throws InfyOrderException{
		Optional<Buyer> optional = buyerRespository.findById(buyerId);
		optional.orElseThrow(() -> new InfyOrderException("Service.BUYER_NOT_FOUND"));
		buyerRespository.deleteById(buyerId);
	}
	
}
