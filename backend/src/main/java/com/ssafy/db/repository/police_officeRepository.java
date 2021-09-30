package com.ssafy.db.repository;

import com.ssafy.db.entity.PoliceOffice;
import com.ssafy.db.mapping.GuardHouseInfoMapping;
import com.ssafy.db.mapping.PoliceInfoMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface police_officeRepository extends JpaRepository<PoliceOffice,Long> {
    List<PoliceInfoMapping> findAllByDongId(Long dongId);
}
