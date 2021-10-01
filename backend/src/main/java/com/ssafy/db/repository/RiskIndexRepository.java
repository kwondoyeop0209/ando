package com.ssafy.db.repository;

import com.ssafy.db.dto.riskindex.RiskIndexDto;
import com.ssafy.db.entity.RiskIndex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiskIndexRepository extends JpaRepository<RiskIndex,Long> {

    List<RiskIndex> findBySiGunGu_Id(Long id);
}
