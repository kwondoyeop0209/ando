package com.ssafy.api.response.dong;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.gu.guGetRes;
import com.ssafy.db.dto.dong.GetDongListDto;
import com.ssafy.db.dto.gu.GetGuDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DongListGetRes extends BaseResponseBody {
    List<GetDongListDto> getDongListDtoList;

    public static DongListGetRes of(Integer statusCode, String message, List<GetDongListDto> list){
        DongListGetRes DongListGetRes = new DongListGetRes();
        DongListGetRes.setStatusCode(statusCode);
        DongListGetRes.setMessage(message);
        DongListGetRes.setGetDongListDtoList(list);
        return DongListGetRes;
    }
}
