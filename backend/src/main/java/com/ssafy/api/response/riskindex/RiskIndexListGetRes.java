package com.ssafy.api.response.riskindex;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.dto.riskindex.RiskIndexDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RiskIndexListGetRes extends BaseResponseBody {
    List<RiskIndexDto> list;

    public static RiskIndexListGetRes of(Integer statusCode, String message, List<RiskIndexDto> list){
        RiskIndexListGetRes riskIndexListGetRes = new RiskIndexListGetRes();
        riskIndexListGetRes.setStatusCode(statusCode);
        riskIndexListGetRes.setMessage(message);
        riskIndexListGetRes.setList(list);
        return riskIndexListGetRes;
    }
}
