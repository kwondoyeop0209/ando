package com.ssafy.db.repository;

import com.ssafy.db.entity.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface barRepository extends JpaRepository<Bar,Long> {
}
