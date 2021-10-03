package com.ssafy.api.response.crimeday;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.mapping.CrimeDayMapping;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CrimeDayListGetRes extends BaseResponseBody {

    List<CrimeDayMapping> list;

    public static CrimeDayListGetRes of(Integer statusCode, String message,List<CrimeDayMapping> list ){
        CrimeDayListGetRes crimeDayListGetRes = new CrimeDayListGetRes();
        crimeDayListGetRes.setStatusCode(statusCode);
        crimeDayListGetRes.setMessage(message);
        crimeDayListGetRes.setList(list);
        return crimeDayListGetRes;
    }
}
