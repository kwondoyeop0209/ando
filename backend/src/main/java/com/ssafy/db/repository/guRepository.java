package com.ssafy.db.repository;

import com.ssafy.db.entity.Gu;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface guRepository extends JpaRepository<Gu,Long> {

     Optional<Gu> findByGu(String gu);

}
