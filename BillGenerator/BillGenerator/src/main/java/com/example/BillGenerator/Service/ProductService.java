package com.example.BillGenerator.Service;

import com.example.BillGenerator.Model.Product;
import com.example.BillGenerator.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> save(List<Product> product)
    {
        return productRepository.saveAll(product);
    }


}
