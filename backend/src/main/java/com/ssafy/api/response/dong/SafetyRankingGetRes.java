package com.ssafy.api.response.dong;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.safety.TopSafetyGetRes;
import com.ssafy.db.dto.dong.GetTopSafetyListDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SafetyRankingGetRes extends BaseResponseBody{

    Long RankingOfSeoul;
    Long RankingOfGu;
    double SafetyIndex;
}
