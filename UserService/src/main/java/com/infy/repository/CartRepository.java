package com.infy.repository;


import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Cart;



public interface CartRepository extends CrudRepository<Cart, Integer> {

}
