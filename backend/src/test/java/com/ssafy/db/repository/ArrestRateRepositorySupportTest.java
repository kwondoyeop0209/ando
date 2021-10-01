package com.ssafy.db.repository;

import com.ssafy.db.dto.arrestrate.GetGuCrimeListDto;
import com.ssafy.db.dto.arrestrate.GetTotalCrimeListDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ArrestRateRepositorySupportTest {

    @Autowired
    ArrestRateRepositorySupport arrestRateRepositorySupport;


    @Test
    public void getTotalSeoul(){
        List<GetTotalCrimeListDto> list = arrestRateRepositorySupport.findSeoulCrime();
        for (GetTotalCrimeListDto totalCrimeListDto : list){
            System.out.println(totalCrimeListDto.getYear() + "    "+ "    "+totalCrimeListDto.getCount());
        }
    }


    @Test
    public void getGuCrime(){
        Long id = Long.valueOf(1);
        List<GetGuCrimeListDto> list = arrestRateRepositorySupport.findGuCrime(id,2018);
        for (GetGuCrimeListDto dto : list){
            System.out.println(dto.getCrimeType() + "   "+ "   "+ dto.getCount());
        }
    }




}