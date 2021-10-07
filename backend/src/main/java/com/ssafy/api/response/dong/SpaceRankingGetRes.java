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
    Long guardRanking;

    double cctvAvg;
    double barAvg;
    double policeAvg;
    double lightAvg;
    double guardAvg;

    int cctv;
    int police;
    int bar;
    int light;
    int guard;

}
