package com.ssafy.api.service.safety;

import com.ssafy.db.dto.dong.DongDtoMapper;
import com.ssafy.db.dto.dong.GetTopSafetyListDto;
import com.ssafy.db.entity.Dong;
import com.ssafy.db.repository.dongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SafetyServiceImpl implements SafetyService{
    @Autowired
    dongRepository dongRepository;

    @Override
    public List<GetTopSafetyListDto> getTopSafetyListDtoList() {
        List<Dong> list = dongRepository.findTop10ByOrderBySafetyIndexDesc();
        return list.stream().map(DongDtoMapper::toSafety).collect(Collectors.toList());
    }

}
