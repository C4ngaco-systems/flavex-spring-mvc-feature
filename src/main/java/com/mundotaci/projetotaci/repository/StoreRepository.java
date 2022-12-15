package com.mundotaci.projetotaci.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mundotaci.projetotaci.entities.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(value = "select * from Store s where s.name_store like %:keyword%", nativeQuery = true)
    List<Store> findAllFilter(@Param("keyword") String keyword);

}
