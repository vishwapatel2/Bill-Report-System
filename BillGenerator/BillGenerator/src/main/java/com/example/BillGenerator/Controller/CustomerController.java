package com.example.BillGenerator.Controller;

import com.example.BillGenerator.Model.Customer;
import com.example.BillGenerator.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("save")
    //http://localhost:8081/customer/save
    private List<Customer> save(@RequestBody List<Customer> customer)
    {
        return customerService.save(customer);
    }
}
