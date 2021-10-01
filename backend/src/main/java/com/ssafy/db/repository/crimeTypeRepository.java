package com.ssafy.db.repository;

import com.ssafy.db.entity.CrimeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface crimeTypeRepository extends JpaRepository<CrimeType,Long> {

    Optional<CrimeType> findByType(String type);
}
