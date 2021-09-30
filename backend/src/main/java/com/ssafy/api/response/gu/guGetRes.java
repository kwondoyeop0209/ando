package com.ssafy.api.response.gu;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.dto.GetGuResDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class guGetRes extends BaseResponseBody {


    List<GetGuResDto> guList;

    public static guGetRes of(Integer statusCode, String message, List<GetGuResDto> list){
        guGetRes guGetRes = new guGetRes();
        guGetRes.setStatusCode(statusCode);
        guGetRes.setMessage(message);
        return guGetRes;
    }

}
