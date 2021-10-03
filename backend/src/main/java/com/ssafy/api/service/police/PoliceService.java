package com.ssafy.api.service.police;

import com.ssafy.api.response.space.SpaceCorrelationGetRes;
import com.ssafy.db.mapping.PoliceInfoMapping;
import java.util.List;

public interface PoliceService {

    List<PoliceInfoMapping> getPoliceInfoByDongId(Long dongId);
    SpaceCorrelationGetRes getPoliceCorrelation();
}
