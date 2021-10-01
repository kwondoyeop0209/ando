package com.ssafy.db.repository;

import com.ssafy.db.dto.arrestrate.GetGuCrimeListDto;
import com.ssafy.db.dto.arrestrate.GetTotalCrimeListDto;
import com.ssafy.db.dto.arrestrate.TopGuListDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ArrestRateRepositorySupportTest {

    @Autowired
    ArrestRateRepositorySupport arrestRateRepositorySupport;



    public void getTotalSeoul(){
        List<GetTotalCrimeListDto> list = arrestRateRepositorySupport.findSeoulCrime();
        for (GetTotalCrimeListDto totalCrimeListDto : list){
            System.out.println(totalCrimeListDto.getYear() + "    "+ "    "+totalCrimeListDto.getCount());
        }
    }



    public void getGuCrime(){
        Long id = Long.valueOf(1);
        List<GetGuCrimeListDto> list = arrestRateRepositorySupport.findGuCrime(id,2018);
        for (GetGuCrimeListDto dto : list){
            System.out.println(dto.getCrimeType() + "   "+ "   "+ dto.getCount());
        }
    }


    public void getRate(){
        Long id = Long.valueOf(1);
        int totalCrimeCount = arrestRateRepositorySupport.getTotalCount("발생",2020);
        int guCrimeCount = arrestRateRepositorySupport.getGuCount("발생",2020,id);
        int totalArrestCount = arrestRateRepositorySupport.getTotalCount("검거",2020);
        int guArrestCount = arrestRateRepositorySupport.getGuCount("검거",2020,id);
        System.out.println("전체 발생 :  "+totalCrimeCount);
        System.out.println("구 발생 : "+guCrimeCount);
        System.out.println("전체 검거 : "+totalArrestCount);
        System.out.println("구 검거 : " +guArrestCount);
    }


    public void getTopList(){
        List<TopGuListDto> crimeList = arrestRateRepositorySupport.getTopGu("발생");
        for (TopGuListDto topGuListDto : crimeList){
            System.out.println("구 이름: "+ topGuListDto.getGu() + "    "+ "건수 : "+topGuListDto.getCount());
        }
        System.out.println("---------------");
        List<TopGuListDto> arrestList = arrestRateRepositorySupport.getTopGu("검거");
        for (TopGuListDto topGuListDto : arrestList){
            System.out.println("구 이름: "+ topGuListDto.getGu() + "    "+ "건수 : "+topGuListDto.getCount());
        }
          }




}