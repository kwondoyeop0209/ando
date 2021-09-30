package com.ssafy.api.service.police;

import com.ssafy.api.response.cctv.SpaceCorrelationGetRes;
import com.ssafy.db.mapping.PoliceInfoMapping;
import com.ssafy.db.repository.arrestRateRepository;
import com.ssafy.db.repository.dongRepository;
import com.ssafy.db.repository.police_officeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoliceServiceImpl implements PoliceService {

    @Autowired
    police_officeRepository policeRepository;
    @Autowired
    dongRepository dongRepository;
    @Autowired
    arrestRateRepository arrestRateRepository;

    @Override
    public List<PoliceInfoMapping> getPoliceInfoByDongId(Long dongId) {
        return policeRepository.findAllByDongId(dongId);
    }

    @Override
    public SpaceCorrelationGetRes getPoliceCorrelation() {
        SpaceCorrelationGetRes res = new SpaceCorrelationGetRes();
        res.setCountList(dongRepository.findGuardGroupBySiGunGu_Id());
        res.setArrestList(arrestRateRepository.findArrestCntByGu());
        return res;
    }
}
