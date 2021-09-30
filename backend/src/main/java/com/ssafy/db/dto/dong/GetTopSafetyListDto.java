package com.ssafy.db.dto.dong;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTopSafetyListDto {
    Long id;
    String dong;
    double safetyIndex;
}
