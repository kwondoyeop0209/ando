package com.ssafy.api.response.crimetime;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.crimeday.CrimeDayListGetRes;
import com.ssafy.db.mapping.CrimeTimeMapping;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CrimeTimeListGetRes extends BaseResponseBody {

    List<CrimeTimeMapping> list;

    public static CrimeTimeListGetRes of(Integer statusCode, String message,List<CrimeTimeMapping> list){
        CrimeTimeListGetRes crimeDayListGetRes = new CrimeTimeListGetRes();
        crimeDayListGetRes.setStatusCode(statusCode);
        crimeDayListGetRes.setMessage(message);
        crimeDayListGetRes.setList(list);
        return crimeDayListGetRes;
    }

}
