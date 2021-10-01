package com.ssafy.api.service.securitylight;

import com.ssafy.api.response.cctv.SpaceCorrelationGetRes;
import com.ssafy.db.mapping.SecurityLightInfoMapping;
import com.ssafy.db.repository.arrestRateRepository;
import com.ssafy.db.repository.dongRepository;
import com.ssafy.db.repository.security_lightRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityLightServiceImpl implements SecurityLightService {

    @Autowired
    security_lightRepository securityLightRepository;
    @Autowired
    dongRepository dongRepository;
    @Autowired
    arrestRateRepository arrestRateRepository;

    @Override
    public List<SecurityLightInfoMapping> getSecurityLightInfoByDongId(Long dongId) {
        return securityLightRepository.findAllByDongId(dongId);
    }

    @Override
    public SpaceCorrelationGetRes getSecurityLightCorrelation() {
        SpaceCorrelationGetRes res = new SpaceCorrelationGetRes();
        res.setCountList(dongRepository.findGuardGroupBySiGunGu_Id());
        res.setArrestList(arrestRateRepository.findGenerationCntByGu());
        return res;
    }
}
