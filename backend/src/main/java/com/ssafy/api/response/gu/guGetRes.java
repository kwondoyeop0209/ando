package com.ssafy.api.response.gu;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.dto.gu.GetGuDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class guGetRes extends BaseResponseBody {


    List<GetGuDto> guList;

    public static guGetRes of(Integer statusCode, String message, List<GetGuDto> list){
        guGetRes guGetRes = new guGetRes();
        guGetRes.setStatusCode(statusCode);
        guGetRes.setMessage(message);
        guGetRes.setGuList(list);
        return guGetRes;
    }

}
