package com.ssafy.db.repository;

import com.ssafy.db.entity.PoliceOffice;
import com.ssafy.db.mapping.GuardHouseInfoMapping;
import com.ssafy.db.mapping.LightCountMapping;
import com.ssafy.db.mapping.PoliceInfoMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface police_officeRepository extends JpaRepository<PoliceOffice,Long> {
    List<PoliceInfoMapping> findAllByDongId(Long dongId);

    @Query(value = "select dong_id as dongid, count(*) as count from police_office group by dong_id;",nativeQuery = true)
    List<LightCountMapping> getCount();
}
