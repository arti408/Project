package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.training.entity.Product;

public interface ProductRepository extends CrudRepository<Product,String> {

	List<Product> findDistinctBycategory(String category);
	List<Product> findDistinctByproductname(String productname);
	@Query("SELECT a FROM Product a WHERE a.productname=:productname and a.category=:category")
    List<Product> fetchProducts(@Param("productname") String productname, @Param("category") String category);
}
