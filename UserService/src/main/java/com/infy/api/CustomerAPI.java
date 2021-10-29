package com.infy.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.BuyerDTO;
import com.infy.service.BuyerService;

@RestController
@RequestMapping(value = "/infyorder")
public class CustomerAPI {
	
	@Autowired
	private BuyerService buyerService;
	
	
	@PostMapping(value = "/buyer")
	public ResponseEntity<String> addBuyer(@Valid @RequestBody BuyerDTO buyerDTO) throws Exception {
		buyerService.addBuyer(buyerDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value = "/buyers/{buyerId}")
	public ResponseEntity<String> deleteBuyer(@PathVariable Integer buyerId) throws Exception {
		buyerService.deletebuyer(buyerId);
		return new ResponseEntity<>( HttpStatus.OK);
	}
}