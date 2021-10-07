package com.ssafy.api.response.arrestrate;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.dto.arrestrate.TopGuListDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TopArrestCrimeGuListRes extends BaseResponseBody {

    List<TopGuListDto> arrestList;
    List<TopGuListDto> crimeList;

    public static TopArrestCrimeGuListRes of(Integer statusCode, String message, List<TopGuListDto> arrestList, List<TopGuListDto> crimeList ){
        TopArrestCrimeGuListRes topArrestCrimeGuListRes = new TopArrestCrimeGuListRes();
        topArrestCrimeGuListRes.setStatusCode(statusCode);
        topArrestCrimeGuListRes.setMessage(message);
        topArrestCrimeGuListRes.setArrestList(arrestList);
        topArrestCrimeGuListRes.setCrimeList(crimeList);
        return topArrestCrimeGuListRes;
    }

}
