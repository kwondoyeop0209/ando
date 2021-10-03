package com.ssafy.db.dto.arrestrate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRateDto {
    int totalCrimeCount;
    int guCrimeCount;
    int totalArrestCount;
    int guArrestCount;
}
