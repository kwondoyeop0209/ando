package com.ssafy.api.service.safety;

import com.ssafy.db.dto.dong.GetTopSafetyListDto;

import java.util.List;

public interface SafetyService {
    List<GetTopSafetyListDto> getTopSafetyListDtoList();
}
