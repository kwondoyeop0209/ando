package com.ssafy.db.repository;

import com.ssafy.db.entity.Cctv;
import com.ssafy.db.mapping.CctvInfoMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface cctvRepository extends JpaRepository<Cctv,Long> {
    List<CctvInfoMapping> findAllByDongId(Long dongId);
}
