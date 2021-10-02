package com.ssafy.api.service.dong;

import com.ssafy.api.response.dong.SafetyRankingGetRes;
import com.ssafy.api.response.dong.SpaceRankingGetRes;
import com.ssafy.db.dto.dong.GetDongListDto;
import javassist.NotFoundException;

import java.util.List;

public interface DongService {
    List<GetDongListDto> getDongList(Long id) throws NotFoundException;
    SafetyRankingGetRes getSafetyRanking(Long id) throws NotFoundException;
    SpaceRankingGetRes getSpaceRanking(Long id) throws NotFoundException;
}
