package com.ssafy.db.repository;

import com.ssafy.db.entity.Dong;
import com.ssafy.db.mapping.LightCountMapping;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class security_lightRepositoryTest {

    @Autowired
    security_lightRepository security_lightRepository;

    @Autowired
    dongRepository dongRepository;

    @Autowired
    barRepository barRepository;

    @Autowired
    cctvRepository cctvRepository;

    @Autowired
    guardHouseRepository guardHouseRepository;

    @Autowired
    police_officeRepository police_officeRepository;

    public void getCount(){
        List<LightCountMapping> countMappingList = guardHouseRepository.getCount();
        List<Dong> dongList = dongRepository.findAllByOrderByIdAsc();
        int count = 0;
//        for (LightCountMapping countMapping : countMappingList){
//            System.out.println(countMapping.getDongid() + "   " + countMapping.getCount());
//        }
        for(int i=0;i<dongList.size();i++){
            if(count >= countMappingList.size()){
                count--;
            }
            Dong dong = dongList.get(i);
            int lightCount = countMappingList.get(count).getCount();
            Long lightDongId = countMappingList.get(count).getDongid();

            if(dong.getId().toString().trim().equals(lightDongId.toString().trim())){
                dong.setGuardHouseCnt(lightCount);
                count++;
            }else{
                System.out.println("-----------------------------------");
                System.out.println(dong.getDong()+ " 원래 갯수 " + dong.getGuardHouseCnt());
                dong.setGuardHouseCnt(0);
            }
            System.out.println("동 이름  =  "+dong.getDong() + " dong id = "+ dong.getId()+ "   " +"bar id  = " + lightDongId + "  dong count  = "+ dong.getGuardHouseCnt() + "   " +" bar count "+ lightCount +" bar/area "  + lightCount/dong.getArea());
            dongRepository.save(dong);
        }
        System.out.println(count);
    }
}