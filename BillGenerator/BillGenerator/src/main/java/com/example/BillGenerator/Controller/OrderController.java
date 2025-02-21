package com.example.BillGenerator.Controller;

import com.example.BillGenerator.Model.Order;
import com.example.BillGenerator.Model.Product;
import com.example.BillGenerator.Repository.ProductRepository;
import com.example.BillGenerator.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("create")
    //http://localhost:8081/order/create
    public String  create(@RequestBody Order order)
    {
        return orderService.createOrder( order);
    }


}
