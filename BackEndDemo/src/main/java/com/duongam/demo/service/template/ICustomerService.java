package com.duongam.demo.service.template;

import com.duongam.demo.dto.request.forcreate.CRequestCustomer;
import com.duongam.demo.dto.request.forupdate.URequestCustomer;
import com.duongam.demo.dto.response.fordetail.DResponseCustomer;
import com.duongam.demo.dto.response.forlist.LResponseCustomer;

import java.util.List;

public interface ICustomerService {
     List<LResponseCustomer> getAll();

     DResponseCustomer save(CRequestCustomer cRequestCustomer);

     DResponseCustomer update(URequestCustomer uRequestCustomer);

     DResponseCustomer findById(Long id);

     DResponseCustomer findByEmail(String email);

     DResponseCustomer deleteById(Long id);
}
