package com.ssafy.db.repository;

import com.ssafy.db.entity.ArrestRate;
import com.ssafy.db.mapping.ArrestMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface arrestRateRepository extends JpaRepository<ArrestRate,Long> {

    @Query(value = "select g.gu as gu, sum(count) as cnt from arrest_rate a join gu g on g.id = a.gu_id where type=\"발생\" group by g.gu;",nativeQuery = true)
    List<ArrestMapping> findGenerationCntByGu();

    @Query(value = "select g.gu as gu, sum(count) as cnt from arrest_rate a join gu g on g.id = a.gu_id where type=\"검거\" group by g.gu;",nativeQuery = true)
    List<ArrestMapping> findArrestCntByGu();
}

