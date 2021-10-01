package com.ssafy.api.response.arrestrate;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.dto.arrestrate.GetRateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRateRes extends BaseResponseBody {

    GetRateDto getRateDto;

    public static GetRateRes of(Integer statusCode, String message, GetRateDto getRateDto){
        GetRateRes getRateRes = new GetRateRes();
        getRateRes.setStatusCode(statusCode);
        getRateRes.setMessage(message);
        getRateRes.setGetRateDto(getRateDto);
        return getRateRes;
    }
}
