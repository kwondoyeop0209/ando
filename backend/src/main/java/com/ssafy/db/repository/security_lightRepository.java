package com.ssafy.db.repository;

import com.ssafy.db.entity.SecurityLight;
import com.ssafy.db.mapping.GuardHouseInfoMapping;
import com.ssafy.db.mapping.SecurityLightInfoMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface security_lightRepository extends JpaRepository<SecurityLight,Long> {
    List<SecurityLightInfoMapping> findAllByDongId(Long dongId);
}
