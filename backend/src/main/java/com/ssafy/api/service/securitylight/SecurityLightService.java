package com.ssafy.api.service.securitylight;

import com.ssafy.api.response.space.SpaceCorrelationGetRes;
import com.ssafy.db.mapping.SecurityLightInfoMapping;
import java.util.List;

public interface SecurityLightService {

    List<SecurityLightInfoMapping> getSecurityLightInfoByDongId(Long dongId);
    SpaceCorrelationGetRes getSecurityLightCorrelation();
}
