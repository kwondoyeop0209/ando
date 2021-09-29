package com.ssafy.db.repository;

import com.ssafy.db.entity.dong;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class dongRepositoryTest {

    @Autowired
    dongRepository dongRepository;

    @Test
    public void getDong(){
        dong dong = dongRepository.findByDongAndSiGunGu_Gu("신사동","강남구").get();
        System.out.println(dong.getDong());
        System.out.println(dong.getSiGunGu());
        System.out.println(dong.getId());
    }

}