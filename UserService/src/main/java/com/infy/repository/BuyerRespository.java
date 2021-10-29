package com.infy.repository;


import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Buyer;


public interface BuyerRespository extends CrudRepository<Buyer, Integer> {

}
