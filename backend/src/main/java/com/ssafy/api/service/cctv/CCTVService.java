package com.ssafy.api.service.cctv;

import com.ssafy.api.response.space.Space5RankingGetRes;
import com.ssafy.api.response.space.SpaceCorrelationGetRes;
import com.ssafy.api.response.space.SpaceCountGetRes;
import com.ssafy.db.dto.CountByDong;
import com.ssafy.db.mapping.CctvInfoMapping;
import java.util.List;
import javassist.NotFoundException;

public interface CCTVService {

    List<CctvInfoMapping> getCCTVInfoByDongId(Long dongId);
    SpaceCorrelationGetRes getCCTVCorrelation();
    SpaceCountGetRes getCCTVCount(Long id) throws NotFoundException;
    Space5RankingGetRes getCCTVCount5List(Long id);
}
