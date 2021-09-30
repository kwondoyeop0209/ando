package com.ssafy.api.response.arrestrate;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.dto.arrestrate.GetTotalCrimeListDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TotalCrimeListGetRes extends BaseResponseBody {

    List<GetTotalCrimeListDto> list;

    public static TotalCrimeListGetRes of(Integer statusCode, String message, List<GetTotalCrimeListDto> list){
        TotalCrimeListGetRes totalCrimeListGetRes = new TotalCrimeListGetRes();
        totalCrimeListGetRes.setStatusCode(statusCode);
        totalCrimeListGetRes.setMessage(message);
        totalCrimeListGetRes.setList(list);
        return totalCrimeListGetRes;
    }
}
