package com.ssafy.db.repository;

import com.ssafy.db.entity.CrimeTime;
import com.ssafy.db.mapping.CrimeTimeMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrimeTimeRepository extends JpaRepository<CrimeTime,Long> {
    List<CrimeTimeMapping> findAllByCrimeType_IdAndYear(Long crimeId,int year);
}
