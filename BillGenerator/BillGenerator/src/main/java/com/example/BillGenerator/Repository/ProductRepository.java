package com.example.BillGenerator.Repository;

import com.example.BillGenerator.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
