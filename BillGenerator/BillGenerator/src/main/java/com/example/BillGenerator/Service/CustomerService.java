package com.example.BillGenerator.Service;

import com.example.BillGenerator.Model.Customer;
import com.example.BillGenerator.Model.Order;
import com.example.BillGenerator.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> save(List<Customer> customerList)
    {
        return customerRepository.saveAll(customerList);
    }
}
