package com.ssafy.api.service.guardhouse;

import com.ssafy.api.response.cctv.SpaceCorrelationGetRes;
import com.ssafy.db.mapping.GuardHouseInfoMapping;
import java.util.List;

public interface GuardHouseService {

    List<GuardHouseInfoMapping> getGuardHouseInfoByDongId(Long dongId);
    SpaceCorrelationGetRes getGuardHouseCorrelation();
}
