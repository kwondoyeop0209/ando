package com.ssafy.api.service.guardhouse;

import com.ssafy.api.response.cctv.SpaceCorrelationGetRes;
import com.ssafy.db.mapping.GuardHouseInfoMapping;
import com.ssafy.db.repository.arrestRateRepository;
import com.ssafy.db.repository.guardHouseRepository;
import com.ssafy.db.repository.dongRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardHouseServiceImpl implements GuardHouseService {

    @Autowired
    guardHouseRepository guardHouseRepository;
    @Autowired
    dongRepository dongRepository;
    @Autowired
    arrestRateRepository arrestRateRepository;

    @Override
    public List<GuardHouseInfoMapping> getGuardHouseInfoByDongId(Long dongId) {
        return guardHouseRepository.findAllByDongId(dongId);
    }

    @Override
    public SpaceCorrelationGetRes getGuardHouseCorrelation() {
        SpaceCorrelationGetRes res = new SpaceCorrelationGetRes();
        res.setCountList(dongRepository.findGuardGroupBySiGunGu_Id());
        res.setArrestList(arrestRateRepository.findGenerationCntByGu());
        return res;
    }
}
