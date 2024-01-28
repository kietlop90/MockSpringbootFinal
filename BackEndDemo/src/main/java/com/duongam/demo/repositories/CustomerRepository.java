package com.duongam.demo.repositories;


import com.duongam.demo.dto.response.fordetail.DResponseCustomer;
import com.duongam.demo.dto.response.forlist.LResponseCustomer;
import com.duongam.demo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<LResponseCustomer> findAllBy();

    Customer findByEmail(String email);
}
