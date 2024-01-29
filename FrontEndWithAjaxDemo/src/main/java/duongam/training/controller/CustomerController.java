package duongam.training.controller;

import duongam.training.dto.request.forcreate.CRequestCustomer;
import duongam.training.dto.request.forupdate.URequestCustomer;
import duongam.training.dto.response.fordetail.DResponseCustomer;
import duongam.training.dto.response.forlist.LResponseCustomer;
import duongam.training.service.HttpCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private HttpCustomer httpCustomer;

    @GetMapping("/list")
    public String list(Model model) {
        List<LResponseCustomer> customers = httpCustomer.getAll();
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @PostMapping("/add")
    @ResponseBody
    public DResponseCustomer addDatabase(@ModelAttribute("customer") CRequestCustomer customer) {
        return httpCustomer.add(customer);
    }

    @GetMapping("/update/{id}")
    @ResponseBody
    public DResponseCustomer updateForm(@PathVariable("id") Long customerId) {
        return httpCustomer.getById(customerId);
    }

    @PostMapping("/update")
    @ResponseBody
    public DResponseCustomer updateDatabase(@ModelAttribute("customer") URequestCustomer customer) {
        return httpCustomer.update(customer);
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Long delete(@PathVariable("id") Long customerId) {
        httpCustomer.deleteById(customerId);
        return customerId;
    }
}
