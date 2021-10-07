package com.ssafy.db.dto.riskindex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiskIndexDto {
    String crimeType;
    String day;
    String spot;
    String time;
    double prtds;
}
