package com.ssafy.api.response.arrestrate;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.dto.arrestrate.GetGuCrimeListDto;
import com.ssafy.db.dto.arrestrate.GetTotalCrimeListDto;
import com.ssafy.db.dto.dong.GetDongListDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GuCrimeListRes extends BaseResponseBody {
    List<GetGuCrimeListDto> list;

    public static GuCrimeListRes of(Integer statusCode, String message, List<GetGuCrimeListDto> list){
        GuCrimeListRes GuCrimeListRes = new GuCrimeListRes();
        GuCrimeListRes.setStatusCode(statusCode);
        GuCrimeListRes.setMessage(message);
        GuCrimeListRes.setList(list);
        return GuCrimeListRes;
    }
}
