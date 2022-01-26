package com.example.n11talenthubbootcampgraduationprojectoyaer.dao;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository <Customer,Long> {

    Customer findByIdNum(String idNum);

    void deleteByIdNum(String idNum);
}
