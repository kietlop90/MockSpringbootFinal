package com.duongam.demo.service;

import com.duongam.demo.dto.request.forcreate.CRequestCustomer;
import com.duongam.demo.dto.request.forupdate.URequestCustomer;
import com.duongam.demo.dto.response.fordetail.DResponseCustomer;
import com.duongam.demo.dto.response.forlist.LResponseCustomer;
import com.duongam.demo.entities.Customer;
import com.duongam.demo.repositories.CustomerRepository;
import com.duongam.demo.service.template.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public List<LResponseCustomer> getAll() {
        return customerRepository.findAllBy();
    }

    @Override
    @Transactional
    public DResponseCustomer save(CRequestCustomer cRequestCustomer) {
        Customer customer = modelMapper.map(cRequestCustomer, Customer.class);
        return modelMapper.map(customerRepository.save(customer), DResponseCustomer.class);
    }

    @Override
    @Transactional
    public DResponseCustomer update(URequestCustomer uRequestCustomer) {
        System.out.println(uRequestCustomer);
        Customer customer = modelMapper.map(uRequestCustomer, Customer.class);
        return modelMapper.map(customerRepository.save(customer), DResponseCustomer.class);
    }

    @Override
    @Transactional
    public DResponseCustomer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(value -> modelMapper.map(value, DResponseCustomer.class)).orElse(null);
    }

    @Override
    @Transactional
    public DResponseCustomer findByEmail(String email) {
        return modelMapper.map(customerRepository.findByEmail(email), DResponseCustomer.class);
    }

    @Override
    @Transactional
    public DResponseCustomer deleteById(Long id) {
        DResponseCustomer dResponseCustomer = findById(id);
        customerRepository.deleteById(id);
        return dResponseCustomer;
    }
}
