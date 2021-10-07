package com.ssafy.db.repository;

import com.ssafy.db.entity.Bar;
import com.ssafy.db.mapping.BarInfoMapping;
import com.ssafy.db.mapping.CctvInfoMapping;
import java.util.List;

import com.ssafy.db.mapping.LightCountMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface barRepository extends JpaRepository<Bar,Long> {
    List<BarInfoMapping> findAllByDongId(Long dongId);

    @Query(value = "select dong_id as dongid, count(*) as count from bar group by dong_id;",nativeQuery = true)
    List<LightCountMapping> getCount();
}
