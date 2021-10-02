package com.ssafy.api.response.space;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.db.dto.CountByDong;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Space5RankingGetRes extends BaseResponseBody {
    List<CountByDong> list;
    Long ranking;
}
