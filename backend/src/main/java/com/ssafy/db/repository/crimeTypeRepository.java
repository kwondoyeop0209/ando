package com.ssafy.db.repository;

import com.ssafy.db.entity.arrest_rate;
import com.ssafy.db.entity.crime_type;
import com.ssafy.db.entity.gu;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface crimeTypeRepository extends JpaRepository<crime_type,Long> {

    Optional<crime_type> findByType(String type);
}
