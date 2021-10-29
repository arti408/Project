package com.infy.service;

import com.infy.dto.BuyerDTO;
import com.infy.exception.InfyOrderException;

public interface BuyerService {
	/*public Integer addCustomer(SellerDTO customerDTO) throws InfyBankException;
	public SellerDTO getCustomer(Integer customerId) throws InfyBankException;
	public void updateCustomer(Integer customerId, String emailId)throws InfyBankException;
	public void deleteCustomer(Integer customerId)throws InfyBankException;
	public List<SellerDTO> getAllCustomers() throws InfyBankException;
*/
	public void addBuyer(BuyerDTO buyerDTO) throws InfyOrderException;
	public void login(String emailId,String password) throws InfyOrderException;
	public void deletebuyer(Integer buyerId) throws InfyOrderException;
}
