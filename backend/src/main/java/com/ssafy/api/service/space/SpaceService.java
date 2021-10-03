package com.ssafy.api.service.space;

import com.ssafy.api.response.space.CountByDongGetRes;

public interface SpaceService {

    public CountByDongGetRes getSpaceCount(String type);
}
