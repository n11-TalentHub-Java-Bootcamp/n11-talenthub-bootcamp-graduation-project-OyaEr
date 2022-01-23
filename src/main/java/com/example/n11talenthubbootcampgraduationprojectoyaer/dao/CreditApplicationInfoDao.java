package com.example.n11talenthubbootcampgraduationprojectoyaer.dao;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.CreditApplicationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditApplicationInfoDao extends JpaRepository<CreditApplicationInfoEntity,Long> {
}
