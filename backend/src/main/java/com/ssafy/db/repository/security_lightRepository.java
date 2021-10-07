package com.ssafy.db.repository;

import com.ssafy.db.entity.SecurityLight;
import com.ssafy.db.mapping.GuardHouseInfoMapping;
import com.ssafy.db.mapping.LightCountMapping;
import com.ssafy.db.mapping.SecurityLightInfoMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface security_lightRepository extends JpaRepository<SecurityLight,Long> {
    List<SecurityLightInfoMapping> findAllByDongId(Long dongId);

    @Query(value = "select dong_id as dongid, count(*) as count from security_light group by dong_id;",nativeQuery = true)
    List<LightCountMapping> getCount();
}
