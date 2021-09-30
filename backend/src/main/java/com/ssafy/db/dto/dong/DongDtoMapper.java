package com.ssafy.db.dto.dong;

import com.ssafy.db.entity.Dong;

public class DongDtoMapper {
    public static GetTopSafetyListDto toSafety(Dong dong) {
        return GetTopSafetyListDto.builder()
                .id(dong.getId())
                .dong(dong.getDong())
                .safetyIndex(dong.getSafetyIndex())
                .build();
    }
}
