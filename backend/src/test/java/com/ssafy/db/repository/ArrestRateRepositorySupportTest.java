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


    public void getTotalSeoul(){
        List<GetTotalCrimeListDto> list = arrestRateRepositorySupport.findSeoulCrime();
        for (GetTotalCrimeListDto totalCrimeListDto : list){
            System.out.println(totalCrimeListDto.getYear() + "    "+ totalCrimeListDto.getType()+ "    "+totalCrimeListDto.getCount());
        }
    }


    public void getGuCrime(){
        Long id = Long.valueOf(1);
        List<GetGuCrimeListDto> list = arrestRateRepositorySupport.findGuCrime(id);
        for (GetGuCrimeListDto dto : list){
            System.out.println(dto.getCrimeType() + "   "+ dto.getType() + "     "+ dto.getCount());
        }
    }




}