package com.ssafy.db.repository;

import com.ssafy.db.entity.CrimeSpot;
import com.ssafy.db.mapping.CrimeSpotMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrimeSpotRepository extends JpaRepository<CrimeSpot,Long> {
    List<CrimeSpotMapping> findAllByCrimeType_IdAndYear(Long crimeId,int year);
}
