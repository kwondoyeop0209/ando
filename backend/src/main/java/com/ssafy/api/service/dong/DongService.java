package com.ssafy.api.service.dong;

import com.ssafy.api.response.dong.SafetyRankingGetRes;
import com.ssafy.api.response.dong.SpaceRankingGetRes;
import com.ssafy.api.response.space.SpaceCountGetRes;
import com.ssafy.db.dto.dong.GetDongListDto;
import com.ssafy.db.mapping.LatLngMapping;
import javassist.NotFoundException;

import java.util.List;

public interface DongService {

    String getGuNameByDong(Long id) throws NotFoundException;

    List<GetDongListDto> getDongList(Long id) throws NotFoundException;
    SafetyRankingGetRes getSafetyRanking(Long id) throws NotFoundException;
    SpaceRankingGetRes getSpaceRanking(Long id) throws NotFoundException;
    LatLngMapping getLatLng(Long id) throws NotFoundException;
}
