package com.example.BillGenerator.Repository;

import com.example.BillGenerator.Model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Integer> {
}
