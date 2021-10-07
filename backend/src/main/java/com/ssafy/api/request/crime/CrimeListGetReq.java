package com.ssafy.api.request.crime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrimeListGetReq {
    Long type;
    int year;
}
