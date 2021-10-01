package com.ssafy.db.repository;

import com.ssafy.db.entity.GuardHouse;
import com.ssafy.db.mapping.CctvInfoMapping;
import com.ssafy.db.mapping.GuardHouseInfoMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface guardHouseRepository extends JpaRepository<GuardHouse,Long> {
    List<GuardHouseInfoMapping> findAllByDongId(Long dongId);
}
