package com.ssafy.api.service.riskindex;


import com.ssafy.db.dto.riskindex.RiskIndexDto;
import com.ssafy.db.entity.RiskIndex;
import com.ssafy.db.repository.RiskIndexRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RiskIndexServiceImpl implements RiskIndexService {

    final RiskIndexRepository riskIndexRepository;

    @Override
    public List<RiskIndexDto> getRiskIndexList(Long id) throws NotFoundException {
        List<RiskIndex> list = riskIndexRepository.findBySiGunGu_Id(id);

        if(list.isEmpty())
            throw new NotFoundException("RiskIndex is empty!");
        List<RiskIndexDto> riskIndexDtoList = new ArrayList<>();

        for (RiskIndex riskIndex: list){
            RiskIndexDto dto = new RiskIndexDto();
            dto.setDay(riskIndex.getDay());
            dto.setPrtds(riskIndex.getPrtds());
            dto.setCrimeType(riskIndex.getCrime_type().getType());
            dto.setTime(riskIndex.getTime());
            dto.setSpot(riskIndex.getSpot());
            riskIndexDtoList.add(dto);
        }
        return riskIndexDtoList;
    }
}
