package com.ssafy.api.response.dong;

import com.ssafy.api.response.BaseResponseBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpaceRankingGetRes extends BaseResponseBody {

    Long cctvRanking;
    Long barRanking;
    Long policeRanking;
    Long lightRanking;

    double cctvAvg;
    double barAvg;
    double policeAvg;
    double lightAvg;

    int cctv;
    int police;
    int bar;
    int light;

}
