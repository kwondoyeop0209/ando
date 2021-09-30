package com.ssafy.db.repository;

import com.ssafy.db.entity.Dong;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface dongRepository extends JpaRepository<Dong,Long> {

    Optional<Dong> findByDongAndSiGunGu_Gu(String dong,String gu);
//    List<Dong> findALL();
}

