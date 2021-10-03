package com.ssafy.api.service.space;

import com.ssafy.api.response.space.CountByDongGetRes;
import com.ssafy.api.response.space.Space5RankingGetRes;
import com.ssafy.api.response.space.SpaceCorrelationGetRes;
import com.ssafy.api.response.space.SpaceCountGetRes;
import javassist.NotFoundException;

public interface SpaceService {

    public CountByDongGetRes getSpaceCount(String type);
    SpaceCorrelationGetRes getSpaceCorrelation(String type) throws NotFoundException;

    SpaceCountGetRes getSpaceCount(String type, Long id) throws NotFoundException;

    Space5RankingGetRes getSpaceCount5List(String type, Long id);
}
