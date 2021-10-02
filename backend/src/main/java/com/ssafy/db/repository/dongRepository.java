package com.ssafy.db.repository;

import com.ssafy.db.entity.Dong;
import com.ssafy.db.mapping.CctvCorrelationMapping;
import com.ssafy.db.mapping.DongRanking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface dongRepository extends JpaRepository<Dong,Long> {

    Optional<Dong> findByDongAndSiGunGu_Gu(String dong,String gu);
    List<Dong> findTop10ByOrderBySafetyIndexDesc();
    List<Dong> findBySiGunGu_Id(Long id);

    @Query(value = "SELECT g.gu as gu, sum(d.cctv_cnt) as cnt from dong d join gu g on g.id = d.gu_id group by d.gu_id",nativeQuery = true)
    List<CctvCorrelationMapping> findCCTVGroupBySiGunGu_Id();
    @Query(value = "SELECT g.gu as gu, sum(d.bar_cnt) as cnt from dong d join gu g on g.id = d.gu_id group by d.gu_id",nativeQuery = true)
    List<CctvCorrelationMapping> findBarGroupBySiGunGu_Id();
    @Query(value = "SELECT g.gu as gu, sum(d.police_cnt) as cnt from dong d join gu g on g.id = d.gu_id group by d.gu_id",nativeQuery = true)
    List<CctvCorrelationMapping> findPoliceGroupBySiGunGu_Id();
    @Query(value = "SELECT g.gu as gu, sum(d.guard_house_cnt) as cnt from dong d join gu g on g.id = d.gu_id group by d.gu_id",nativeQuery = true)
    List<CctvCorrelationMapping> findGuardGroupBySiGunGu_Id();
    @Query(value = "SELECT g.gu as gu, sum(d.light_cnt) as cnt from dong d join gu g on g.id = d.gu_id group by d.gu_id",nativeQuery = true)
    List<CctvCorrelationMapping> findLightGroupBySiGunGu_Id();


    Optional<Dong> findById(Long id);

}

