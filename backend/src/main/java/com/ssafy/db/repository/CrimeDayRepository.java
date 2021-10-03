package com.ssafy.db.repository;

import com.ssafy.db.entity.CrimeDay;
import com.ssafy.db.mapping.CrimeDayMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrimeDayRepository extends JpaRepository<CrimeDay,Long> {

    List<CrimeDayMapping> findAllByCrimeType_IdAndYear(Long crimeId,int year);
}
