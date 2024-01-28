package com.duongam.demo.controller;

import com.duongam.demo.dto.request.forcreate.CRequestCustomer;
import com.duongam.demo.dto.request.forupdate.URequestCustomer;
import com.duongam.demo.dto.response.fordetail.DResponseCustomer;
import com.duongam.demo.dto.response.forlist.LResponseCustomer;

import com.duongam.demo.service.template.ICustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Customer", description = "Customer API's")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<List<LResponseCustomer>> list(Model model) {
        List<LResponseCustomer> customers = customerService.getAll();
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping("/add")
    public ResponseEntity<DResponseCustomer> add(@Valid @RequestBody CRequestCustomer cRequestCustomer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        DResponseCustomer dResponseCustomer = customerService.save(cRequestCustomer);
        return ResponseEntity.ok().body(dResponseCustomer);
    }

    @PutMapping("/update")
    public ResponseEntity<DResponseCustomer> update(@Valid @RequestBody URequestCustomer uRequestCustomer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        DResponseCustomer dResponseCustomer = customerService.update(uRequestCustomer);
        return ResponseEntity.ok().body(dResponseCustomer);
    }

    @RequestMapping(value = "/get/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<DResponseCustomer> getByEmail(@PathVariable(value = "email") String email) {
        DResponseCustomer customer = customerService.findByEmail(email);
        return ResponseEntity.ok().body(customer);
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<DResponseCustomer> getById(@PathVariable(value = "id") Long id) {
        DResponseCustomer customer = customerService.findById(id);
        return ResponseEntity.ok().body(customer);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DResponseCustomer> delete(@PathVariable("id") Long customerId) {
        DResponseCustomer dResponseCustomer = customerService.deleteById(customerId);
        return ResponseEntity.ok().body(dResponseCustomer);
    }
}
