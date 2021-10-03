package com.ssafy.api.service.crimeday;

import com.ssafy.db.mapping.CrimeDayMapping;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CrimeDayServiceImplTest {

    @Autowired
    CrimeDayService crimeDayService;

    public void getCrimeDayList() throws NotFoundException {
        List<CrimeDayMapping> list = crimeDayService.getCrimeDayList(Long.valueOf(1),2020);
        for (CrimeDayMapping crimeDayMapping : list){
            System.out.println(crimeDayMapping.getDay());
            System.out.println(crimeDayMapping.getCount());
            System.out.println();
        }
    }
}