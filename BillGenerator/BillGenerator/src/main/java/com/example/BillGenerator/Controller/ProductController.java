package com.example.BillGenerator.Controller;

import com.example.BillGenerator.Model.Product;
import com.example.BillGenerator.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("save")
    //http://localhost:8081/product/save
    public List<Product> save(@RequestBody List<Product> product)
    {
        return productService.save(product);
    }

}
