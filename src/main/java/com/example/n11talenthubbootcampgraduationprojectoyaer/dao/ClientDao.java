package com.example.n11talenthubbootcampgraduationprojectoyaer.dao;

import com.example.n11talenthubbootcampgraduationprojectoyaer.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ClientDao extends JpaRepository <ClientEntity,Long> {

    ClientEntity findByIdNum(String idNum);

    void deleteByIdNum(String idNum);
}
