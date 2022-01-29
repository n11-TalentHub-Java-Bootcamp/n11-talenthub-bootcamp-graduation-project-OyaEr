package com.example.n11talenthubbootcampgraduationprojectoyaer.dao;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository <Customer,Long> {

    Optional<Customer> findByIdNum(String idNum);

    Optional<Customer> findByPhoneNum(String phoneNumber);

    void deleteByIdNum(String idNum);
}
