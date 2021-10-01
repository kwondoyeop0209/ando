package com.ssafy.db.repository;

import com.ssafy.db.entity.Bar;
import com.ssafy.db.mapping.BarInfoMapping;
import com.ssafy.db.mapping.CctvInfoMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface barRepository extends JpaRepository<Bar,Long> {
    List<BarInfoMapping> findAllByDongId(Long dongId);
}
