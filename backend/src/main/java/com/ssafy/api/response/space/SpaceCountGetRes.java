package com.ssafy.api.response.space;

import com.ssafy.api.response.BaseResponseBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpaceCountGetRes extends BaseResponseBody {
    int guCnt;
    int dongCnt;
}
