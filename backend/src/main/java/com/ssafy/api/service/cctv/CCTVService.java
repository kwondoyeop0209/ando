package com.ssafy.api.service.cctv;

import com.ssafy.api.response.cctv.CctvCorrelationGetRes;
import com.ssafy.db.mapping.CctvInfoMapping;
import java.util.List;

public interface CCTVService {

    List<CctvInfoMapping> getCCTVInfoByDongId(Long dongId);
    CctvCorrelationGetRes getCCTVCorrelation();
}
