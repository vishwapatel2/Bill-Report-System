package com.example.BillGenerator.Repository;

import com.example.BillGenerator.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
