package com.intutx.ecommshopservice.repo;



import org.springframework.data.repository.CrudRepository;

import com.intutx.ecommshopservice.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

        Customer findByLoginId(String loginId);
}
