package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.LoginDTO;
import com.infy.dto.SellerDTO;
import com.infy.entity.Seller;
import com.infy.entity.SellerLogin;
import com.infy.exception.InfyOrderException;
import com.infy.repository.SellerLoginRepository;
import com.infy.repository.SellerRepository;

@Service(value = "sellerService")
@Transactional
public class SellerService {

	@Autowired
	private SellerRepository sellerRepository;
	@Autowired
	private SellerLoginRepository sellerLoginRepository;
	
	

	public void addSeller(SellerDTO sellerDTO) throws InfyOrderException{
		Optional<Seller> optional = sellerRepository.findById(sellerDTO.getSellerId());
		if (optional.isPresent())
			throw new InfyOrderException("Service.BUYER_FOUND");
		Seller seller = new Seller();
		seller.setSellerId(sellerDTO.getSellerId());
		seller.setEmailId(sellerDTO.getEmailId());
		seller.setName(sellerDTO.getName());   
		seller.setPhoneNo(sellerDTO.getPhoneNo());
		seller.setPassword(sellerDTO.getPassword());
		seller.setIsActive(sellerDTO.getIsActive());
		sellerRepository.save(seller);
	}	
	
	public String login(LoginDTO loginDTO) throws InfyOrderException{          
		SellerLogin sellerlogin=sellerLoginRepository.findById(loginDTO.getEmailId()).orElse(null);
		if(sellerlogin == null)
			return "Seller does not exits. Invalid credential...";
		if(loginDTO.getEmailId() == null)
			return "EmailId is Incorrect...";
		if(!sellerlogin.getPassword().equals(loginDTO.getPassword()))
			return "Password is Incorrect ...";
		sellerlogin.setIsActive("Y");;
		sellerLoginRepository.save(sellerlogin);
		return "Login Successfully..";
	}
	
	
	public void deleteSeller(Integer sellerId) throws InfyOrderException{
		Optional<Seller> optional = sellerRepository.findById(sellerId);
		optional.orElseThrow(() -> new InfyOrderException("Service.BUYER_NOT_FOUND"));
		sellerRepository.deleteById(sellerId);
	}
	
	public List<SellerDTO> getAllSellers() {
		List<Seller> sellers = (List<Seller>) sellerRepository.findAll();
		List<SellerDTO>sellerDTOs = new ArrayList<SellerDTO>();
		for(Seller seller : sellers) {
			SellerDTO sellerDTO = new SellerDTO();
			sellerDTO.setSellerId(seller.getSellerId());
			sellerDTO.setName(seller.getName());
			sellerDTO.setEmailId(seller.getEmailId());
			sellerDTO.setPhoneNo(seller.getPhoneNo());
			sellerDTO.setPassword(seller.getPassword());
			sellerDTO.setIsActive(seller.getIsActive());
			sellerDTOs.add(sellerDTO);
		}
		return sellerDTOs;
	}

	
}
