package com.example.BillGenerator.Service;

import com.example.BillGenerator.Model.Customer;
import com.example.BillGenerator.Model.Order;
import com.example.BillGenerator.Model.Product;
import com.example.BillGenerator.Repository.BillRepository;
import com.example.BillGenerator.Repository.OrderRepository;
import com.example.BillGenerator.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BillService service;

    public String createOrder(Order order) {
        Product product = productRepository.findById(order.getProduct().getPid()).get();
//        order.setGst(product.getGst());
//        order.setMrp(product.getMrp());
//        order.setFinalPrice((order.getQuantity()* product.getGst()/100)* product.getMrp());
        order.setDate(LocalDate.now());
        return service.GenerateBill(order);
    }
}