package com.ssafy.api.service.bar;

import com.ssafy.api.response.cctv.SpaceCorrelationGetRes;
import com.ssafy.db.mapping.BarInfoMapping;
import com.ssafy.db.repository.arrestRateRepository;
import com.ssafy.db.repository.barRepository;
import com.ssafy.db.repository.dongRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {

    @Autowired
    barRepository barRepository;
    @Autowired
    dongRepository dongRepository;
    @Autowired
    arrestRateRepository arrestRateRepository;

    @Override
    public List<BarInfoMapping> getBarInfoByDongId(Long dongId) {
        return barRepository.findAllByDongId(dongId);
    }

    @Override
    public SpaceCorrelationGetRes getBarCorrelation() {
        SpaceCorrelationGetRes res = new SpaceCorrelationGetRes();
        res.setCountList(dongRepository.findBarGroupBySiGunGu_Id());
        res.setArrestList(arrestRateRepository.findGenerationCntByGu());
        return res;
    }
}
