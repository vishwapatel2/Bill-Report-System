package com.example.BillGenerator.Repository;

import com.example.BillGenerator.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
