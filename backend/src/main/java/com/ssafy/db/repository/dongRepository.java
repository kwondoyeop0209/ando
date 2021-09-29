package com.ssafy.db.repository;

import com.ssafy.db.entity.dong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface dongRepository extends JpaRepository<dong,Long> {

    Optional<dong> findByDongAndSiGunGu_Gu(String dong,String gu);

}

