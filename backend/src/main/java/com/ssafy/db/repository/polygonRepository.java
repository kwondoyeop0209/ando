package com.ssafy.db.repository;

import com.ssafy.db.entity.Polygon;
import com.ssafy.db.entity.cctv;
import org.springframework.data.jpa.repository.JpaRepository;


public interface polygonRepository extends JpaRepository<Polygon,Long> {

}
