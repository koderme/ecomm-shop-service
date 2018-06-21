package com.intutx.ecommshopservice.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.intutx.ecommshopservice.model.CustomerProfile;

public interface CustomerProfileRepository extends CrudRepository<CustomerProfile, Long>{

        CustomerProfile findByLoginId(String loginId);
        List<CustomerProfile> findByFirstName(String firstName);
        List<CustomerProfile> findByLastName(String lastName);

        // Use query as necessary
        //@Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
        //List<Customer> fetchArticles(@Param("title") String title, @Param("category") String category);
}
