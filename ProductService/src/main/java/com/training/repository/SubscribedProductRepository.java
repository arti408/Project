package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.training.entity.SubscribedProduct;

public interface SubscribedProductRepository extends CrudRepository<SubscribedProduct,String> {
	
	
	@Query("SELECT a FROM SubscribedProduct a WHERE a.productId=:productId and a.buyerId=:buyerId")
    List<SubscribedProduct> fetchSubProducts(@Param("productId") String productId, @Param("buyerId") String buyerId);
}
