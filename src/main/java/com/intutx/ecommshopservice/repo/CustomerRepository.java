package com.intutx.ecommshopservice.repo;

import org.springframework.data.repository.CrudRepository;

import com.intutx.ecommshopservice.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

		Customer findByLoginId(String loginId);
        // Use query as necessary
        //@Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
        //List<Customer> fetchArticles(@Param("title") String title, @Param("category") String category);
}
