package com.ssafy.api.service.riskindex;

import com.ssafy.db.dto.riskindex.RiskIndexDto;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RiskIndexServiceImplTest {

    @Autowired
    RiskIndexService riskIndexService;

    public void getRiskIndexList() throws NotFoundException {
        List<RiskIndexDto> list = riskIndexService.getRiskIndexList(Long.valueOf(1));
        for(RiskIndexDto risk : list){
            System.out.println(risk.getCrimeType());
            System.out.println(risk.getDay());
            System.out.println(risk.getSpot());
            System.out.println(risk.getTime());
            System.out.println(risk.getPrtds());
            System.out.println("-------------------------------");
        }
    }
}