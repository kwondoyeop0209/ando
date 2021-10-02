package com.ssafy.api.request.crimeday;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrimeDayListGetReq {
    Long type;
    int year;
}
