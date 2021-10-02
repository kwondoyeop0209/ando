package com.ssafy.db.repository;

import com.ssafy.db.entity.Polygon;
import com.ssafy.db.mapping.PolygonMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PolygonRepository extends JpaRepository<Polygon,Long> {

    Optional<PolygonMapping> findByDong_Id(Long id);
}
