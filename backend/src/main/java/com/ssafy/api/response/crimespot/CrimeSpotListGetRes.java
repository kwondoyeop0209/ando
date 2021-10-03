package com.ssafy.api.response.crimespot;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.mapping.CrimeSpotMapping;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CrimeSpotListGetRes extends BaseResponseBody {
    List<CrimeSpotMapping> list;

    public static CrimeSpotListGetRes of(Integer statusCode, String message,List<CrimeSpotMapping> list){
        CrimeSpotListGetRes crimeSpotListGetRes = new CrimeSpotListGetRes();
        crimeSpotListGetRes.setStatusCode(statusCode);
        crimeSpotListGetRes.setMessage(message);
        crimeSpotListGetRes.setList(list);
        return crimeSpotListGetRes;
    }
}
