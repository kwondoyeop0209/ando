package com.ssafy.api.response.safety;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.dto.dong.GetTopSafetyListDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TopSafetyGetRes extends BaseResponseBody {
    List<GetTopSafetyListDto> getTopSafetyListDtoList;

    public static TopSafetyGetRes of(Integer statusCode, String message, List<GetTopSafetyListDto> list) {
        TopSafetyGetRes topSafetyGetRes = new TopSafetyGetRes();
        topSafetyGetRes.setStatusCode(statusCode);
        topSafetyGetRes.setMessage(message);
        topSafetyGetRes.setGetTopSafetyListDtoList(list);
        return topSafetyGetRes;
    }
}
