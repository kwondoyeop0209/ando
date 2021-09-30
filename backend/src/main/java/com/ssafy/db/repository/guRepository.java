package com.ssafy.db.repository;

import com.ssafy.db.entity.gu;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface guRepository extends JpaRepository<gu,Long> {

     Optional<gu> findByGu(String gu);


}
