package com.ssafy.api.service.cctv;

import com.ssafy.api.response.cctv.SpaceCorrelationGetRes;
import com.ssafy.db.mapping.CctvInfoMapping;
import java.util.List;

public interface CCTVService {

    List<CctvInfoMapping> getCCTVInfoByDongId(Long dongId);
    SpaceCorrelationGetRes getCCTVCorrelation();
}
