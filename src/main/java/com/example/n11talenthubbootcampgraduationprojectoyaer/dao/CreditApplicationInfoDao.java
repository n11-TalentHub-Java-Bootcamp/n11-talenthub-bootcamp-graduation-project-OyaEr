package com.example.n11talenthubbootcampgraduationprojectoyaer.dao;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CreditApplicationInfoDao extends JpaRepository<CreditApplicationInfo,Long> {

    List<CreditApplicationInfo> findByCustomerId(Long customerId);

}
