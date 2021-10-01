package com.ssafy.api.service.bar;

import com.ssafy.api.response.cctv.SpaceCorrelationGetRes;
import com.ssafy.db.mapping.BarInfoMapping;
import java.util.List;

public interface BarService {

    List<BarInfoMapping> getBarInfoByDongId(Long dongId);
    SpaceCorrelationGetRes getBarCorrelation();
}
