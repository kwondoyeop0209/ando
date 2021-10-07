package com.ssafy.db.repository;

import com.ssafy.db.entity.GuardHouse;
import com.ssafy.db.mapping.CctvInfoMapping;
import com.ssafy.db.mapping.GuardHouseInfoMapping;
import java.util.List;

import com.ssafy.db.mapping.LightCountMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface guardHouseRepository extends JpaRepository<GuardHouse,Long> {
    List<GuardHouseInfoMapping> findAllByDongId(Long dongId);

    @Query(value = "select dong_id as dongid, count(*) as count from guard_house group by dong_id;",nativeQuery = true)
    List<LightCountMapping> getCount();
}
