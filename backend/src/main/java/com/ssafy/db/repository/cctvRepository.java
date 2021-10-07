package com.ssafy.db.repository;

import com.ssafy.db.entity.Cctv;
import com.ssafy.db.mapping.CctvInfoMapping;
import java.util.List;

import com.ssafy.db.mapping.LightCountMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface cctvRepository extends JpaRepository<Cctv,Long> {
    List<CctvInfoMapping> findAllByDongId(Long dongId);

    @Query(value = "select dong_id as dongid, sum(number) as count from cctv group by dong_id;",nativeQuery = true)
    List<LightCountMapping> getCount();
}
